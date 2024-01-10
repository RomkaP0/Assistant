package com.romka_po.assistent.domain.local.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.romka_po.assistent.model.local.LocalMake
import kotlinx.coroutines.flow.Flow

@Dao
interface CarMakeDAO {
    @Upsert
    fun upsertMakes(makesList:List<LocalMake>)

    @Query("Select * from LocalMake")
    fun getMakes():Flow<List<LocalMake>>

    @Query("Select * from LocalMake where name like '%' || :query || '%' or cyrillicName like '%' || :query || '%'")
    fun searchLike(query:String):List<LocalMake>

}