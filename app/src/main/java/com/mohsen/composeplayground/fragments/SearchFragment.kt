package com.mohsen.composeplayground.fragments

import androidx.compose.runtime.Composable
import com.mohsen.composeplayground.collapsingsearchbox.SearchPage

class SearchFragment: ComposeFragment() {
    @Composable
    override fun Content() {
        SearchPage()
    }
}