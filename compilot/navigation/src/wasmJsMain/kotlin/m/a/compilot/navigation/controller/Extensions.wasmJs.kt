package m.a.compilot.navigation.controller

import androidx.navigation.NavDestination

actual val NavDestination.destinationId: Int
    get() = this.id