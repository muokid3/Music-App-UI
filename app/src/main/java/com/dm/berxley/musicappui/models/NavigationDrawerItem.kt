package com.dm.berxley.musicappui.models

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationDrawerItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)
