package com.romka_po.assistent.domain.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.romka_po.assistent.model.LatLonListConverter
import com.romka_po.assistent.model.Track
import com.romka_po.assistent.model.local.LocalMake
import com.romka_po.assistent.model.local.LocalModel

@Database(entities = [LocalMake::class, LocalModel::class, Track::class], version = 1)
@TypeConverters(LatLonListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMakeDAO(): CarMakeDAO
    abstract fun getModelDAO(): CarModelDAO

    abstract fun getLocationDAO(): LocationDAO
}
