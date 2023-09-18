package com.romka_po.assistent.domain.repository

import com.romka_po.assistent.domain.DatastoreManager
import com.romka_po.assistent.domain.api.CarNetworkSource
import com.romka_po.assistent.model.theme.TypeTheme
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val datastoreManager: DatastoreManager,
    private val carNetworkSource: CarNetworkSource
) : MainRepository {
    override fun getTheme() = datastoreManager.themeSettingsFlow

    override suspend fun changeTheme(typeTheme: TypeTheme) =
        datastoreManager.changeTheme(typeTheme = typeTheme)

}