@file:Suppress("PackageNaming")

package dev.shtanko.kotlinlang.whatsnew.v2_1_0.guard_conditions

sealed interface Screen {
    data class Home(val isLoggedIn: Boolean) : Screen
    data class Profile(val userId: String?) : Screen
    object Settings : Screen
}

fun navigateTo(screen: Screen): String = when (screen) {
    is Screen.Home if screen.isLoggedIn -> "Welcome to your dashboard"
    is Screen.Profile if screen.userId != null -> "Opening profile for ${screen.userId}"
    is Screen.Settings -> "Opening settings"
    else -> "Redirect to login"
}

fun main() {
    println(navigateTo(Screen.Home(true)))
    println(navigateTo(Screen.Profile("user42")))
    println(navigateTo(Screen.Settings))
    println(navigateTo(Screen.Home(false)))
}
