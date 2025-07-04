@file:Suppress("PackageNaming")

package dev.shtanko.kotlinlang.whatsnew.v2_2_0

enum class Problem {
    CONNECTION, AUTHENTICATION, DATABASE, UNKNOWN
}

object OldWay {
    fun message(problem: Problem): String = when (problem) {
        Problem.CONNECTION -> "connection"
        Problem.AUTHENTICATION -> "authentication"
        Problem.DATABASE -> "database"
        Problem.UNKNOWN -> "unknown"
    }
}
