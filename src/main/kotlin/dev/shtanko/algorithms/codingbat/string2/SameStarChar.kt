package dev.shtanko.algorithms.codingbat.string2

sealed interface SameStarChar {
    operator fun invoke(str: String): Boolean

    object SlidingWindow : SameStarChar {
        override fun invoke(str: String): Boolean {
            return str.windowed(3).map {
                it
            }.filter {
                it[1] == '*'
            }.all {
                it[1] == '*' && it.first() == it.last()
            }
        }
    }

    object SlidingWindowSimple : SameStarChar {
        override fun invoke(str: String): Boolean {
            for (i in 1 until str.length - 1) {
                if (str[i] == '*') {
                    if (str[i - 1] != str[i + 1]) {
                        return false
                    }
                }
            }
            return true
        }
    }
}
