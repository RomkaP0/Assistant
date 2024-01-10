package com.romka_po.assistent.model.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.DeveloperMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.ui.graphics.vector.ImageVector

enum class TypeTheme(val label: String, val iconMode: ImageVector) {
    LIGHT("Светлая", Icons.Outlined.LightMode), DARK(
        "Темная",
        Icons.Outlined.DarkMode
    ),
    AUTO("Системная", Icons.Outlined.DeveloperMode)
}