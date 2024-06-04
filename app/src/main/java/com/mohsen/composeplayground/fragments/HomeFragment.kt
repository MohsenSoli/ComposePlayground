package com.mohsen.composeplayground.fragments

import androidx.compose.runtime.Composable
import com.mohsen.composeplayground.home.HomeScreen

class HomeFragment: ComposeFragment() {
    @Composable
    override fun Content() {
        HomeScreen()
    }
}