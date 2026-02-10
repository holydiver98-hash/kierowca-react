package com.example.kierowca2.data.entity

import androidx.room.*
import com.example.kierowca2.data.entity.AgencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AgencyDao {
    @Query("SELECT * FROM agency ORDER BY agency_name")
    fun getAllAgencies(): Flow<List<AgencyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<AgencyEntity>)

    @Query("DELETE FROM agency")
    suspend fun clear()

    @Query("UPDATE agency SET agency_name = :name WHERE agency_id = :id")
    suspend fun updateName(id: String, name: String)

    @Query("SELECT * FROM agency WHERE agency_id = :id") // fallback
    suspend fun getById(id: String): AgencyEntity?

    @Query("UPDATE agency SET isEnabled = :selected WHERE agency_id = :id")
    suspend fun updateAgencySelection(id: String, selected: Boolean)
}
