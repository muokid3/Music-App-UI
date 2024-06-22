package com.dm.berxley.musicappui.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.dm.berxley.musicappui.models.NavigationDrawerItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {

    val drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed)
    val scope: CoroutineScope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val navigationDrawerItems = listOf<NavigationDrawerItem>(
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

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                navigationDrawerItems.forEachIndexed { index, navigationDrawerItem ->
                    NavigationDrawerItem(
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        label = { Text(text = navigationDrawerItem.title) },
                        selected = index == selectedItemIndex,
                        onClick = {
                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (selectedItemIndex == index) navigationDrawerItem.selectedIcon else navigationDrawerItem.unselectedIcon,
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
            topBar = {
                TopAppBar(
                    title = { Text(text = "Home") },
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
            Text(text = "text", modifier = Modifier.padding(it))
        }
    }
}


@Composable
fun DrawerItem(
    selected: Boolean,
    onDrawerItemClicked: () -> Unit,
    item: Screen.DrawerScreen
) {
    val bg = if (selected) Color.DarkGray else Color.White
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 16.dp)
        .clickable { onDrawerItemClicked() }
        .background(bg)
    ) {

        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            modifier = Modifier.padding(end = 8.dp, top = 4.dp)
        )


        Text(
            text = item.dTitle,
            style = MaterialTheme.typography.headlineMedium
        )

    }

}