package com.dm.berxley.musicappui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dm.berxley.musicappui.ui.composables.Screen

class MainViewModel: ViewModel() {
    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.DrawerScreen.Home)
    private val _selectedDrawerItemIndex = mutableStateOf(100)
    private val _selectedBottomIndex = mutableStateOf(0)

    val currentScreen = _currentScreen
    val selectedDrawerItemIndex = _selectedDrawerItemIndex
    val selectedBottomIndex = _selectedBottomIndex

    fun setCurrentScreen(screen: Screen){
        _currentScreen.value = screen
    }
    fun setDrawerSelectedItemIndex(idx: Int){
        _selectedDrawerItemIndex.value = idx
    }

    fun setSelectedBottomIndex(idx: Int){
        _selectedBottomIndex.value = idx
    }
}