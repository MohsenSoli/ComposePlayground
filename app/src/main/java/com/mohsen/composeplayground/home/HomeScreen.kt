package com.mohsen.composeplayground.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.mohsen.composeplayground.rememberFragmentNavController
import com.mohsen.composeplayground.rememberGeneralOwnerFragment
import navigation.Route
import navigation.Search
import navigation.navigate

@Composable
fun HomeScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val fragment = rememberGeneralOwnerFragment()
        Button(
            onClick = { fragment?.navigate(Search) }
        ) {
            Text(text = "go to search")
        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "go to cards")
        }
    }
}