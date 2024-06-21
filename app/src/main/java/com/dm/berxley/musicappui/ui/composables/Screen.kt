package com.dm.berxley.musicappui.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import com.dm.berxley.musicappui.R

sealed class Screen(val title: String, val route: String) {


    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int) :
        Screen(dTitle, dRoute) {
        object Account : DrawerScreen(
            "Account",
            "account",
            R.drawable.ic_account
        )
        object Subscription : DrawerScreen(
            "Subscription",
            "subscription",
            R.drawable.ic_subscription
        )

        object AddAccount : DrawerScreen(
            "Add Account",
            "add_account",
            R.drawable.ic_add_account
        )
    }
}