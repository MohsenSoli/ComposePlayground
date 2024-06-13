package com.mohsen.composeplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.createGraph
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.fragment
import navigation.ComposeCompatFragment
import com.mohsen.composeplayground.fragments.HomeFragment
import com.mohsen.composeplayground.fragments.SearchFragment
import navigation.ComposeCompat
import navigation.ComposeRouteType
import navigation.LegacyHome
import navigation.LegacySearch
import navigation.Route
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHosFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHosFragment!!.findNavController()
        navController.graph = navController.createGraph(
            startDestination = LegacyHome
        ) {
            fragment<HomeFragment, LegacyHome>()
            fragment<SearchFragment, LegacySearch>()
            Route.ComposeRoute::class.sealedSubclasses.forEach {

            }
            fragment<ComposeCompatFragment, ComposeCompat>(
                typeMap = mapOf(typeOf<Route.ComposeRoute>() to ComposeRouteType)
            )
        }
    }
}


@Composable
fun rememberFragmentNavController() = rememberGeneralOwnerFragment()?.findNavController()

@Composable
fun rememberGeneralOwnerFragment(): Fragment? {
    val view = LocalView.current
    return remember(key1 = view) {
        runCatching { view.findFragment<Fragment>() }.getOrNull()
    }
}
