@file:Suppress("PackageNaming")

package dev.shtanko.kotlinlang.whatsnew.v2_2_0

// UserService defines the dependency required in the context
interface UserService {
    fun log(message: String)
    fun findUserById(id: Int): String
}

// Declares a function with a context parameter
context(users: UserService)
fun outputMessage(message: String) {
    // Uses log from the context
    users.log("Log: $message")
}

// Declares a property with a context parameter
context(users: UserService)
val firstUser: String
    // Uses findUserById from the context
    get() = users.findUserById(1)

// Uses "_" as context parameter name
context(_: UserService)
fun logWelcome() {
    // Finds the appropriate log function from UserService
    outputMessage("Welcome!")
}

fun main() {
    val userService = object : UserService {
        override fun log(message: String) {
            println("LOG: $message")
        }

        override fun findUserById(id: Int): String {
            return "User$id"
        }
    }

    with(userService) {
        outputMessage("Hello from Kotlin 2.2!")
        println("First user is: $firstUser")
        logWelcome()
    }
}
