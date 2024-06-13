package navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import androidx.navigation.NavType
import androidx.navigation.fragment.findNavController
import com.mohsen.composeplayground.rememberFragmentNavController
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Composable
inline fun <reified T : Route> navigateComposable(dest: T) {
    when (dest) {
        is Route.ComposeRoute -> LocalNavController.current?.navigate(dest)
        is Route.LegacyRoute -> rememberFragmentNavController()?.navigate(dest)
    }
}

inline fun <reified T : Route> Fragment.navigate(dest: T) {
    when (dest) {
        is Route.ComposeRoute -> findNavController().navigate(ComposeCompat(dest))
        is Route.LegacyRoute -> findNavController().navigate(dest)
    }
}

@Serializable
sealed interface Route {

    @Serializable
    @Parcelize
    sealed interface ComposeRoute : Route, Parcelable

    @Serializable
    sealed interface LegacyRoute : Route
}

@Serializable
@Parcelize
data object Home : Route.ComposeRoute

@Serializable
@Parcelize
data object Search : Route.ComposeRoute

@Serializable
data object LegacyHome : Route.LegacyRoute

@Serializable
data object LegacySearch : Route.LegacyRoute

@Serializable
data class ComposeCompat(
    val composeRoute: Route.ComposeRoute
) : Route.LegacyRoute

val ComposeRouteType = object : NavType<Route.ComposeRoute>(
    isNullableAllowed = false
) {
    override fun put(bundle: Bundle, key: String, value: Route.ComposeRoute) {
        bundle.putParcelable(key, value)
    }

    override fun get(bundle: Bundle, key: String): Route.ComposeRoute {
        return bundle.getParcelable(key)!!
    }

    override fun parseValue(value: String): Route.ComposeRoute {
        return Json.decodeFromString<Route.ComposeRoute>(value)
    }
}