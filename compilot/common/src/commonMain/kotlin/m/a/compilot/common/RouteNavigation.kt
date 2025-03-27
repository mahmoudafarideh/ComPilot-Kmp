package m.a.compilot.common

annotation class RouteNavigation(val type: RouteType)

enum class RouteType {
    Screen,
    Dialog,
//    BottomSheet
}

interface RouteNavigator {
    fun navigator(): String
    fun route(): String
}