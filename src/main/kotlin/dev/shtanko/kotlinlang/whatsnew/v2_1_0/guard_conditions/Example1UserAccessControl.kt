@file:Suppress("PackageNaming")

package dev.shtanko.kotlinlang.whatsnew.v2_1_0.guard_conditions

sealed interface User {
    data class Admin(val level: Int) : User
    data class Guest(val isTemporary: Boolean) : User
    data class Member(val isPremium: Boolean) : User
}

fun accessSystem(user: User): String = when (user) {
    is User.Admin if user.level >= 5 -> "Access to admin panel"
    is User.Member if user.isPremium -> "Access to premium features"
    is User.Guest if user.isTemporary -> "Temporary guest access"
    else -> "Limited access"
}

fun main() {
    println(accessSystem(User.Admin(6)))
    println(accessSystem(User.Member(true)))
    println(accessSystem(User.Guest(true)))
    println(accessSystem(User.Admin(1)))
}
