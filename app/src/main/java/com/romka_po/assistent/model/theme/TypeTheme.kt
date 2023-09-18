package com.romka_po.assistent.model.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.ui.graphics.vector.ImageVector

enum class TypeTheme(val autoMode: ImageVector) {
    LIGHT(Icons.Default.LightMode), DARK(Icons.Default.DarkMode), AUTO(Icons.Default.DeveloperMode)
}