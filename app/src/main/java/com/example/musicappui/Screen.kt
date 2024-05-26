package com.example.musicappui

import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String) {

    sealed class BottomScreen(val dtitle: String, val  dRoute: String, @DrawableRes val icon: Int): Screen(dtitle, dRoute){
        object Home: BottomScreen("Home","home",R.drawable.baseline_home_24)
        object Browser: BottomScreen("Browser","browser",R.drawable.baseline_browse_gallery_24)
        object Library: BottomScreen("Library","library",R.drawable.baseline_library_music_24)
    }

    sealed class DrawerScreen(val dtitle: String, val dRoute: String, @DrawableRes val icon: Int): Screen(dtitle, dRoute) {
         object Account: DrawerScreen(
             "Account", "account", R.drawable.baseline_account_box_24
         )
        object Subscription: DrawerScreen(
            "Subscription", "Subscribe", R.drawable.baseline_library_music_24
        )
        object AddAccount: DrawerScreen(
            "Add Account", "AddAccount", R.drawable.baseline_person_add_alt_1_24
        )
    }
}

val screensOnBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Browser,
    Screen.BottomScreen.Library
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount,
)