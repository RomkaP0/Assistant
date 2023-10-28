package com.romka_po.assistent.domain.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.romka_po.assistent.model.local.LocalMake
import com.romka_po.assistent.model.local.LocalModel

@Database(entities = [LocalMake::class, LocalModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMakeDAO(): CarMakeDAO
    abstract fun getModelDAO(): CarModelDAO
}
