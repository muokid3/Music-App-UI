package com.dm.berxley.musicappui.ui.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.FindInPage
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.FindInPage
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.dm.berxley.musicappui.models.NavigationDrawerItem
import com.dm.berxley.musicappui.viewModels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {

    val drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed)
    val scope: CoroutineScope = rememberCoroutineScope()

    val mainViewModel: MainViewModel = viewModel()
    val navController = rememberNavController()
    val dialogOpen = remember {
        mutableStateOf(false)
    }

    val currentScreen = mainViewModel.currentScreen.value
    val title = remember {
        mutableStateOf(currentScreen.title)
    }


    val navigationDrawerItems = listOf(
        NavigationDrawerItem(
            title = "Account",
            selectedIcon = Icons.Filled.AccountBox,
            unselectedIcon = Icons.Outlined.AccountBox
        ),
        NavigationDrawerItem(
            title = "Subscription",
            selectedIcon = Icons.Filled.LibraryMusic,
            unselectedIcon = Icons.Outlined.LibraryMusic,
            badgeCount = 31
        ),
        NavigationDrawerItem(
            title = "Add Account",
            selectedIcon = Icons.Filled.AddCircle,
            unselectedIcon = Icons.Outlined.AddCircle
        ),
    )

    val bottomBarItems = listOf(
        NavigationDrawerItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        NavigationDrawerItem(
            title = "Browse",
            selectedIcon = Icons.Filled.FindInPage,
            unselectedIcon = Icons.Outlined.FindInPage,
            badgeCount = 31
        ),
        NavigationDrawerItem(
            title = "Library",
            selectedIcon = Icons.Filled.LibraryMusic,
            unselectedIcon = Icons.Outlined.LibraryMusic
        ),
    )

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                navigationDrawerItems.forEachIndexed { index, navigationDrawerItem ->
                    NavigationDrawerItem(
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        label = { Text(text = navigationDrawerItem.title) },
                        selected = index == mainViewModel.selectedDrawerItemIndex.value,
                        onClick = {
                            mainViewModel.setDrawerSelectedItemIndex(index)
                            title.value = navigationDrawerItem.title
                            when (index) {
                                0 -> {
                                    navController.navigate(Screen.DrawerScreen.Account.route)
                                    mainViewModel.setSelectedBottomIndex(100)
                                }

                                1 -> {
                                    navController.navigate(Screen.DrawerScreen.Subscription.route)
                                    mainViewModel.setSelectedBottomIndex(100)
                                }

                                2 -> dialogOpen.value = true
                            }
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (mainViewModel.selectedDrawerItemIndex.value == index) navigationDrawerItem.selectedIcon else navigationDrawerItem.unselectedIcon,
                                contentDescription = navigationDrawerItem.title
                            )
                        },
                        badge = {
                            navigationDrawerItem.badgeCount?.let {
                                Text(text = navigationDrawerItem.badgeCount.toString())
                            }
                        })
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    bottomBarItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = mainViewModel.selectedBottomIndex.value == index,
                            onClick = {
                                title.value = item.title
                                mainViewModel.setSelectedBottomIndex(index)
                                mainViewModel.setDrawerSelectedItemIndex(100)
                                //navigate here using navcontroller
                                when (index) {
                                    0 -> {
                                        navController.navigate(Screen.DrawerScreen.Home.route)
                                        mainViewModel.setDrawerSelectedItemIndex(100)
                                    }

                                    1 -> {
                                        navController.navigate(Screen.DrawerScreen.Browse.route)
                                        mainViewModel.setDrawerSelectedItemIndex(100)
                                    }

                                    2 -> {
                                        navController.navigate(Screen.DrawerScreen.Library.route)
                                        mainViewModel.setDrawerSelectedItemIndex(100)
                                    }
                                }
                            },
                            label = {
                                Text(text = item.title)
                            },
                            icon = {
                                BadgedBox(badge = {
                                    if (item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    }
                                }) {
                                    Icon(
                                        imageVector = if (mainViewModel.selectedBottomIndex.value == index) item.selectedIcon else item.unselectedIcon,
                                        contentDescription = item.title
                                    )
                                }
                            })
                    }
                }
            },
            topBar = {
                TopAppBar(
                    title = { Text(text = title.value) },
                    navigationIcon = {
                        IconButton(onClick = {
                            //open drawer
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                    })
            },
        ) {
            Navigation(navController = navController, viewModel = mainViewModel, pd = it)
            AccountDialog(dialogOpen = dialogOpen)
        }
    }
}