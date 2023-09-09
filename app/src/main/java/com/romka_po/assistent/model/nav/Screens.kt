package com.romka_po.assistent.model.nav

import androidx.annotation.StringRes
import androidx.annotation.DrawableRes
import com.romka_po.assistent.R

sealed class Screens(val route: String, @StringRes val stringId: Int, @DrawableRes val drawableId: Int) {
    data object DashBoard : Screens("dashboard", R.string.dashboard, R.drawable.dashboard_fill1_wght400_grad0_opsz48)
    data object Settings : Screens("settings", R.string.settings, R.drawable.settings_fill1_wght400_grad0_opsz48)
}