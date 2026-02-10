package com.example.kierowca2.data.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kierowca2.data.entity.ShapePointEntity

@Dao
interface ShapeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShapePoints(list: List<ShapePointEntity>)

    @Query("DELETE FROM shapes")
    suspend fun clear()

    @Query("SELECT * FROM shapes WHERE shape_id = :shapeId ORDER BY shape_pt_sequence ASC")
    suspend fun getShapePoints(shapeId: String): List<ShapePointEntity>
}
