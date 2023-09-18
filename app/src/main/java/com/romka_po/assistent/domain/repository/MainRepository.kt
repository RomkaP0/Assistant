package com.romka_po.assistent.domain.repository

import com.romka_po.assistent.model.theme.TypeTheme
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getTheme(): Flow<TypeTheme>

    suspend fun changeTheme(typeTheme: TypeTheme)
}