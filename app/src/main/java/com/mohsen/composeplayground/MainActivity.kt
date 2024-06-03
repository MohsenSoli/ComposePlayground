package com.mohsen.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohsen.composeplayground.collapsingsearchbox.SearchPage
import com.mohsen.composeplayground.home.HomeScreen
import com.mohsen.composeplayground.ui.theme.ComposePlaygroundTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                val navController = rememberNavController()
                CompositionLocalProvider(LocalNavController provides navController) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        AppNavHost(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Route = Route.Home,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable<Route.Home> {
            HomeScreen()
        }

        composable<Route.Search> {
            SearchPage()
        }
    }
}

@Serializable
sealed interface Route {

    @Serializable
    data object Home : Route

    @Serializable
    data object Search : Route
}

val LocalNavController = compositionLocalOf<NavHostController?> { null }