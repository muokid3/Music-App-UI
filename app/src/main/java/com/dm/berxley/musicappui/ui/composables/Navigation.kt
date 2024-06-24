package com.dm.berxley.musicappui.ui.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dm.berxley.musicappui.viewModels.MainViewModel

@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd: PaddingValues) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.DrawerScreen.Home.route,
        modifier = Modifier.padding(pd)
    ) {
        composable(Screen.DrawerScreen.Home.route) {
            HomeView()
        }

        composable(Screen.DrawerScreen.Browse.route) {
            BrowseView()
        }

        composable(Screen.DrawerScreen.Library.route) {

        }
        composable(Screen.DrawerScreen.Account.route) {
            AccountView()
        }
        composable(Screen.DrawerScreen.Subscription.route) {
            Subscription()
        }
    }
}