package com.romka_po.assistent.model.nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.romka_po.assistent.R

sealed class Screens(
    val route: String,
    @StringRes val stringId: Int,
    @DrawableRes val drawableId: Int?
) {
    data object DashBoard :
        Screens("dashboard", R.string.dashboard, R.drawable.dashboard_fill1_wght400_grad0_opsz48)

    data object Catalog : Screens(
        "catalog",
        R.string.catalog, R.drawable.format_list_bulleted_fill0_wght400_grad0_opsz48
    )

    data object Settings :
        Screens("settings", R.string.settings, R.drawable.settings_fill1_wght400_grad0_opsz48)

    data object Chart :
        Screens("chart", R.string.stats, R.drawable.bar_chart_fill0_wght400_grad0_opsz48)

    data object Story :
        Screens("story", R.string.story, null)

    data object Auth :
        Screens("auth", R.string.auth, null)
    data object Register :
        Screens("register", R.string.register, null)

    data object Detail :
            Screens("detail", R.string.detail, null)

    data object Account:
            Screens("account", R.string.account, null)
}