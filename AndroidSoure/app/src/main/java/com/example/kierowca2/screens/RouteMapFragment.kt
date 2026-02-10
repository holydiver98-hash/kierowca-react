package com.example.kierowca2.screens

import android.Manifest
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import com.example.kierowca2.R
import com.example.kierowca2.data.GtfsDatabase
import com.example.kierowca2.data.StopWithTime
import com.example.kierowca2.data.entity.ShapePointEntity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.maps.android.SphericalUtil
import kotlinx.coroutines.launch
import kotlin.math.abs

class RouteMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var viewModel: RouteMapViewModel
    private lateinit var tripId: String
    private lateinit var fabNavigateChunk: ExtendedFloatingActionButton
    private lateinit var fabFollowMode: FloatingActionButton
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var polyline: Polyline? = null
    private val markers = mutableListOf<Marker>()
    private val circles = mutableListOf<Circle>()
    private val arrowMarkers = mutableListOf<Marker>()
    private var stops: List<StopWithTime> = emptyList()
    private var currentStopChunk: List<StopWithTime>? = null

    private var isFollowModeEnabled = false
    private var locationCallback: LocationCallback? = null
    private var myLocationMarker: Marker? = null
    
    // Animation for the target stop
    private var pulseAnimator: ValueAnimator? = null
    private var closestStopIndex = -1

    // Settings
    private var showRedArrow = true
    private var smartHighlight = true
    private var highlightEnabled = true

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        ) {
            enableMyLocation()
        } else {
            Toast.makeText(requireContext(), "Permission denied to show location", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tripId = requireArguments().getString("trip_id")!!
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_route_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val db = GtfsDatabase.getInstance(requireContext())
        viewModel = ViewModelProvider(
            this,
            RouteMapViewModelFactory(db.tripDao(), db.shapeDao(), db.gtfsDao())
        )[RouteMapViewModel::class.java]

        view.findViewById<FloatingActionButton>(R.id.fab_navigate_to_start).setOnClickListener {
            navigateToFirstPoint()
        }

        fabNavigateChunk = view.findViewById(R.id.fab_navigate_chunk)
        fabNavigateChunk.setOnClickListener {
            currentStopChunk?.let { launchNavigationForRange(it) }
        }

        view.findViewById<FloatingActionButton>(R.id.fab_my_location).setOnClickListener {
            centerOnUserLocation()
        }

        fabFollowMode = view.findViewById(R.id.fab_follow_mode)
        fabFollowMode.setOnClickListener {
            toggleFollowMode()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        loadSettings() // Load settings first
        enableMyLocation()

        map.setOnCameraMoveStartedListener { reason ->
            if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
                if (isFollowModeEnabled) {
                    isFollowModeEnabled = false
                    updateFollowModeButton()
                }
            }
        }

        // Marker click listener for cutting route
        map.setOnMarkerClickListener { marker ->
            val stop = marker.tag as? StopWithTime
            if (stop != null) {
                showCutRouteDialog(stop)
                true // Consume event
            } else {
                false
            }
        }

        lifecycleScope.launch {
            viewModel.loadTrip(tripId)
        }

        viewModel.shape.observe(viewLifecycleOwner) { shape ->
            drawShape(shape)
        }

        viewModel.stops.observe(viewLifecycleOwner) { stopsWithTime ->
            this.stops = stopsWithTime
            showAllStopsAndRoute()
            createPagingButtons()
        }
    }

    override fun onResume() {
        super.onResume()
        if (::map.isInitialized) {
            loadSettings()
            enableMyLocation() // Refresh my location layer visibility
            drawStops(stops) // Redraw to apply new highlight settings immediately
        }
    }

    private fun loadSettings() {
        if (!isAdded) return // Safe check
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        showRedArrow = prefs.getBoolean("pref_show_red_arrow", true)
        
        // Load highlight mode: 0=Simple, 1=Smart, 2=None
        val highlightMode = prefs.getInt("pref_highlight_mode", 1)
        smartHighlight = (highlightMode == 1)
        highlightEnabled = (highlightMode != 2)
        
        // Clean up red arrow if disabled
        if (!showRedArrow) {
            myLocationMarker?.remove()
            myLocationMarker = null
        }
    }

    private fun showCutRouteDialog(stop: StopWithTime) {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.start_route_here))
            .setMessage(getString(R.string.cut_route_question, "${stop.stop.stopName}?"))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                cutRouteFromStop(stop)
            }
            .setNegativeButton(getString(R.string.no), null)
            .show()
    }

    private fun cutRouteFromStop(startStop: StopWithTime) {
        val index = stops.indexOfFirst { it.stop.stopId == startStop.stop.stopId }
        if (index != -1) {
            // Slice the list of stops
            this.stops = stops.subList(index, stops.size)
            
            // Redraw stops and buttons
            showAllStopsAndRoute()
            createPagingButtons()
            
            Toast.makeText(requireContext(), getString(R.string.route_updated), Toast.LENGTH_SHORT).show()
        }
    }

    private fun toggleFollowMode() {
        isFollowModeEnabled = !isFollowModeEnabled
        updateFollowModeButton()
        if (isFollowModeEnabled) {
            startLocationUpdates()
        } else {
            val current = map.cameraPosition
            val newPos = CameraPosition.Builder(current)
                .tilt(0f)
                .bearing(0f)
                .build()
            map.animateCamera(CameraUpdateFactory.newCameraPosition(newPos))
        }
    }

    private fun updateFollowModeButton() {
        if (isFollowModeEnabled) {
            fabFollowMode.setColorFilter(Color.BLUE)
            fabFollowMode.background.setTint(Color.CYAN)
        } else {
            fabFollowMode.clearColorFilter()
            fabFollowMode.background.setTint(Color.WHITE)
        }
    }

    private fun centerOnUserLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    val latLng = LatLng(it.latitude, it.longitude)
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                }
            }
        }
    }

    private fun startLocationUpdates() {
        val hasFineLocation = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        val hasCoarseLocation = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

        if (!hasFineLocation && !hasCoarseLocation) return

        val priority = if (hasFineLocation) Priority.PRIORITY_HIGH_ACCURACY else Priority.PRIORITY_BALANCED_POWER_ACCURACY

        val locationRequest = LocationRequest.Builder(priority, 2000)
            .setMinUpdateDistanceMeters(5f)
            .build()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                if (!isAdded) return

                val location = result.lastLocation ?: return
                
                // Update custom marker if enabled
                if (showRedArrow) {
                    updateMyLocationMarker(location)
                }

                // Find closest stop if highlight enabled
                if (highlightEnabled && stops.isNotEmpty()) {
                    var minDistance = Float.MAX_VALUE
                    var newClosestIndex = -1

                    // 1. Находим самую близкую по расстоянию остановку
                    stops.forEachIndexed { index, stop ->
                        if (stop.stop.stopLat != null && stop.stop.stopLon != null) {
                            val results = FloatArray(1)
                            Location.distanceBetween(
                                location.latitude, location.longitude,
                                stop.stop.stopLat, stop.stop.stopLon,
                                results
                            )
                            if (results[0] < minDistance) {
                                minDistance = results[0]
                                newClosestIndex = index
                            }
                        }
                    }

                    // 2. Проверяем направление (Smart Highlight), если включено
                    if (smartHighlight && newClosestIndex != -1) {
                        val closestStop = stops[newClosestIndex].stop
                        if (closestStop.stopLat != null && closestStop.stopLon != null) {
                            val stopPos = LatLng(closestStop.stopLat, closestStop.stopLon)
                            val userPos = LatLng(location.latitude, location.longitude)
                            
                            val headingToStop = SphericalUtil.computeHeading(userPos, stopPos)
                            val userBearing = location.bearing
                            
                            var angleDiff = abs(headingToStop - userBearing)
                            if (angleDiff > 180) angleDiff = 360 - angleDiff

                            if (angleDiff > 90 && newClosestIndex < stops.size - 1) {
                                newClosestIndex++
                            }
                        }
                    }

                    if (newClosestIndex != -1 && newClosestIndex != closestStopIndex) {
                        closestStopIndex = newClosestIndex
                        drawStops(stops) // Redraw to move pulse animation
                    }
                }

                if (isFollowModeEnabled) {
                    val cameraPosition = CameraPosition.Builder()
                        .target(LatLng(location.latitude, location.longitude))
                        .zoom(18f)
                        .bearing(location.bearing)
                        .tilt(45f)
                        .build()

                    map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback!!, Looper.getMainLooper())
    }

    private fun updateMyLocationMarker(location: Location) {
        val latLng = LatLng(location.latitude, location.longitude)
        if (myLocationMarker == null) {
            val markerOptions = MarkerOptions()
                .position(latLng)
                .icon(getMyLocationBitmapDescriptor())
                .anchor(0.5f, 0.5f)
                .flat(true)
                .rotation(location.bearing)
                .zIndex(10f) // Ensure it's on top
            myLocationMarker = map.addMarker(markerOptions)
        } else {
            myLocationMarker?.position = latLng
            myLocationMarker?.rotation = location.bearing
        }
    }

    private fun getMyLocationBitmapDescriptor(): BitmapDescriptor {
        val width = 80
        val height = 80
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint().apply {
            color = Color.RED
            style = Paint.Style.FILL
            isAntiAlias = true
            setShadowLayer(10f, 0f, 0f, Color.BLACK)
        }
        
        val path = Path()
        path.moveTo(width / 2f, 10f) 
        path.lineTo(10f, height - 10f) 
        path.lineTo(width / 2f, height - 25f)
        path.lineTo(width - 10f, height - 10f)
        path.close()

        canvas.drawPath(path, paint)
        
        val strokePaint = Paint().apply {
            color = Color.WHITE
            style = Paint.Style.STROKE
            strokeWidth = 3f
            isAntiAlias = true
        }
        canvas.drawPath(path, strokePaint)

        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        locationCallback?.let { fusedLocationClient.removeLocationUpdates(it) }
        pulseAnimator?.cancel()
    }

    private fun createPagingButtons() {
        val container = view?.findViewById<LinearLayout>(R.id.paging_buttons_container) ?: return
        container.removeAllViews()

        val chunkSize = 10
        val chunks = stops.chunked(chunkSize)

        val allButton = Button(requireContext()).apply {
            text = "All"
            setOnClickListener { showAllStopsAndRoute() }
        }
        container.addView(allButton)

        chunks.forEachIndexed { index, chunk ->
            val start = index * chunkSize + 1
            val end = start + chunk.size - 1
            val button = Button(requireContext()).apply {
                text = "$start - $end"
                setOnClickListener {
                    showStopsChunk(chunk)
                    fabNavigateChunk.text = getString( R.string.navigation_template, "$start", " $end")
                    fabNavigateChunk.visibility = View.VISIBLE
                    currentStopChunk = chunk
                }
            }
            container.addView(button)
        }
    }

    private fun showAllStopsAndRoute() {
        drawStops(stops)
        polyline?.points?.let { points ->
            if (points.isNotEmpty()) {
                val bounds = LatLngBounds.builder()
                points.forEach { bounds.include(it) }
                map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
            }
        }
        fabNavigateChunk.visibility = View.GONE
        currentStopChunk = null
    }

    private fun showStopsChunk(chunk: List<StopWithTime>) {
        drawStops(chunk)
        if (chunk.isNotEmpty()) {
            val bounds = LatLngBounds.builder()
            chunk.forEach { stopWithTime ->
                stopWithTime.stop.stopLat?.let { lat ->
                    stopWithTime.stop.stopLon?.let { lon ->
                        bounds.include(LatLng(lat, lon))
                    }
                }
            }
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 150))
        }
    }

    private fun launchNavigationForRange(stopsInRange: List<StopWithTime>) {
        if (stopsInRange.size < 2) {
            Toast.makeText(requireContext(), getString( R.string.not_enough_stops), Toast.LENGTH_SHORT).show()
            return
        }

        val origin = stopsInRange.first().stop
        val destination = stopsInRange.last().stop
        val waypoints = stopsInRange.subList(1, stopsInRange.size - 1).joinToString("|") { 
            "${it.stop.stopLat},${it.stop.stopLon}"
        }

        val gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=${origin.stopLat},${origin.stopLon}&destination=${destination.stopLat},${destination.stopLon}&waypoints=$waypoints")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(mapIntent)
        } else {
            Toast.makeText(requireContext(), getString( R.string.google_maps_not_installed), Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToFirstPoint() {
        val firstStop = stops.firstOrNull()?.stop
        if (firstStop == null || firstStop.stopLat == null || firstStop.stopLon == null) {
            Toast.makeText(requireContext(), getString( R.string.route_not_available), Toast.LENGTH_SHORT).show()
            return
        }
        val gmmIntentUri = Uri.parse("google.navigation:q=${firstStop.stopLat},${firstStop.stopLon}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(mapIntent)
        } else {
            Toast.makeText(requireContext(), getString( R.string.google_maps_not_installed), Toast.LENGTH_SHORT).show()
        }
    }

    private fun enableMyLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            try {
                map.isMyLocationEnabled = !showRedArrow
            } catch (e: SecurityException) {
                // Handle or log
            }
            startLocationUpdates()
        } else {
            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun getArrowBitmapDescriptor(): BitmapDescriptor {
        val width = 40
        val height = 60
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint().apply {
            color = Color.BLACK
            style = Paint.Style.FILL
            isAntiAlias = true
            strokeWidth = 4f
        }
        
        val path = Path()
        path.moveTo(width / 2f, 0f) 
        path.lineTo(0f, height * 0.4f) 
        path.lineTo(width / 2f, height * 0.25f)
        path.lineTo(width.toFloat(), height * 0.4f)
        path.close()

        val tailPath = Path()
        tailPath.moveTo(width / 2f, height * 0.25f)
        tailPath.lineTo(width / 2f, height.toFloat())

        paint.style = Paint.Style.FILL
        canvas.drawPath(path, paint)
        
        paint.style = Paint.Style.STROKE
        canvas.drawPath(tailPath, paint)

        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun drawShape(shape: List<ShapePointEntity>) {
        if (shape.isEmpty()) return

        val polylineOptions = PolylineOptions()
            .color(Color.BLUE)
            .width(10f)
            .jointType(JointType.ROUND)

        val points = shape.map { LatLng(it.shapePtLat, it.shapePtLon) }
        polylineOptions.addAll(points)
        polyline = map.addPolyline(polylineOptions)

        addArrowsToPolyline(points)

        val bounds = LatLngBounds.builder()
        points.forEach { bounds.include(it) }
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
    }

    private fun addArrowsToPolyline(points: List<LatLng>) {
        arrowMarkers.forEach { it.remove() }
        arrowMarkers.clear()

        val arrowIcon = getArrowBitmapDescriptor()
        val interval = 500.0 
        val offsetDistance = 15.0 
        var accumulatedDistance = 0.0

        for (i in 0 until points.size - 1) {
            val segmentStart = points[i]
            val segmentEnd = points[i + 1]
            val segmentLength = SphericalUtil.computeDistanceBetween(segmentStart, segmentEnd)

            while (accumulatedDistance + segmentLength >= interval) {
                val distanceToNextArrow = interval - accumulatedDistance
                val fraction = distanceToNextArrow / segmentLength
                val arrowPositionOnLine = SphericalUtil.interpolate(segmentStart, segmentEnd, fraction)
                val heading = SphericalUtil.computeHeading(segmentStart, segmentEnd)

                val offsetPosition = SphericalUtil.computeOffset(arrowPositionOnLine, offsetDistance, heading + 90)

                val arrow = map.addMarker(
                    MarkerOptions()
                        .position(offsetPosition)
                        .icon(arrowIcon)
                        .anchor(0.5f, 0.5f)
                        .rotation(heading.toFloat())
                        .flat(true)
                )
                if (arrow != null) {
                    arrowMarkers.add(arrow)
                }
                accumulatedDistance -= interval
            }
            accumulatedDistance += segmentLength
        }
    }

    private fun drawStops(stopsToDraw: List<StopWithTime>) {
        if (!isAdded || context == null) return // Safe check

        // Stop existing animation
        pulseAnimator?.cancel()
        
        markers.forEach { it.remove() }
        markers.clear()
        circles.forEach { it.remove() }
        circles.clear()

        val circleColor = polyline?.color ?: Color.RED
        
        // Light Orange color for the nearest stop
        val nearestStopColor = Color.rgb(255, 171, 64) 

        stopsToDraw.forEachIndexed { index, stopWithTime ->
            val stop = stopWithTime.stop
            val lat = stop.stopLat
            val lon = stop.stopLon

            if (lat != null && lon != null) {
                val pos = LatLng(lat, lon)

                // Highlight closest stop if enabled
                if (highlightEnabled && index == closestStopIndex) {
                    val circle = map.addCircle(
                        CircleOptions()
                            .center(pos)
                            .radius(7.0) // Start size (reduced)
                            .strokeWidth(0f)
                            .fillColor(nearestStopColor)
                    )
                    circles.add(circle)
                    startPulseAnimation(circle)
                } else {
                    val circle = map.addCircle(
                        CircleOptions()
                            .center(pos)
                            .radius(10.0)
                            .strokeWidth(0f)
                            .fillColor(circleColor)
                    )
                    circles.add(circle)
                }

                val markerView = createCustomMarkerView(stop.stopName, stopWithTime.arrivalTime, stopWithTime.pickupType)
                val markerIcon = BitmapDescriptorFactory.fromBitmap(getBitmapFromView(markerView))

                val marker = map.addMarker(
                    MarkerOptions()
                        .position(pos)
                        .icon(markerIcon)
                        .anchor(0f, 1f)
                )
                if (marker != null) {
                    marker.tag = stopWithTime // Save stop object in tag
                    markers.add(marker)
                }
            }
        }
    }
    
    private fun startPulseAnimation(circle: Circle) {
        // Reduced size by ~2x (was 15f-35f, now 7f-17f)
        pulseAnimator = ValueAnimator.ofFloat(7f, 17f).apply {
            duration = 1000
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { animator ->
                val radius = animator.animatedValue as Float
                circle.radius = radius.toDouble()
            }
            start()
        }
    }

    private fun createCustomMarkerView(stopName: String?, arrivalTime: String?, pickupType: Int?): View {
        val context = context ?: return View(requireContext()) // Fail safe
        val markerView = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null)
        val txtStopName = markerView.findViewById<TextView>(R.id.txtStopName)
        val txtArrivalTime = markerView.findViewById<TextView>(R.id.txtArrivalTime)

        val prefix = if (pickupType == 2 || pickupType == 3) "[Ż] " else ""
        txtStopName.text = "$prefix${stopName ?: getString(R.string.start_route_here)}"
        txtArrivalTime.text = arrivalTime ?: "--:--"

        return markerView
    }

    private fun getBitmapFromView(view: View): Bitmap {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.draw(canvas)
        return bitmap
    }
}
