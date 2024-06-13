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
fun ComposeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Route.ComposeRoute = Home,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Home> { HomeScreen() }
        composable<Search> { SearchPage() }
    }
}

val LocalNavController = compositionLocalOf<NavHostController?> { null }