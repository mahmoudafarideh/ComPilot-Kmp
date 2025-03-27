package m.a.compilot.navigation.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import m.a.compilot.navigation.result.NavigationResultHandler

expect val NavDestination.destinationId: Int

val LocalNavController = compositionLocalOf<NavController> { error("No NavController found!") }

val ProvidableCompositionLocal<NavController>.comPilotNavController: ComPilotNavController
    @Composable
    get() {
        val navigation = this.current
        val currentDestinationId = navigation.currentDestination?.let {
            rememberSaveable { it.destinationId }
        }
        val safeNavController = remember {
            ComPilotNavControllerImp(navigation, currentDestinationId)
        }
        return safeNavController
    }

@Composable
fun NavBackStackEntry.NavigationResultHandler(
    action: NavigationResultHandler.() -> Unit
) {
    LaunchedEffect(Unit) {
        repeatOnLifecycle(Lifecycle.State.RESUMED) {
            action(NavigationResultHandler(this@NavigationResultHandler.savedStateHandle))
        }
    }
}
