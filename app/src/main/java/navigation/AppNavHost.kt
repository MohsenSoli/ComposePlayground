package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mohsen.composeplayground.collapsingsearchbox.SearchPage
import com.mohsen.composeplayground.home.HomeScreen

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

val LocalNavController = compositionLocalOf<NavHostController?> { null }