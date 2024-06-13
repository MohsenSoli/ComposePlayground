package navigation

import androidx.compose.runtime.Composable
import com.mohsen.composeplayground.fragments.ComposeFragment

class ComposeCompatFragment: ComposeFragment() {

    private val route: Route.ComposeRoute? by lazy {
        arguments?.getParcelable("route")
    }

    @Composable
    override fun Content() {
        route?.let { navigateComposable(dest = it) }
    }
}