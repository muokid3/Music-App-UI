package com.dm.berxley.musicappui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dm.berxley.musicappui.ui.composables.Screen

class MainViewModel: ViewModel() {
    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.DrawerScreen.Account)
    private val _selectedItemIndex = mutableStateOf(0)

    val currentScreen = _currentScreen
    val selectedItemIndex = _selectedItemIndex

    fun setCurrentScreen(screen: Screen){
        _currentScreen.value = screen
    }
    fun setSelectedItemIndex(idx: Int){
        _selectedItemIndex.value = idx
    }
}