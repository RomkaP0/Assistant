package com.romka_po.assistent.domain

import com.romka_po.assistent.model.theme.TypeTheme
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val datastoreManager: DatastoreManager
) : MainRepository {
    override fun getTheme() = datastoreManager.themeSettingsFlow

    override suspend fun changeTheme(typeTheme: TypeTheme) =
        datastoreManager.changeTheme(typeTheme = typeTheme)
}