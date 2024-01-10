package com.romka_po.assistent.domain.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.romka_po.assistent.model.Track
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertLocation(track: Track)

    @Query("Select sum(distance) from Track where endTimestamp >= :since ")
    fun getDistanceAfter(since:Long): Flow<Double>
}