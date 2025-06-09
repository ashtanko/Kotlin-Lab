package dev.shtanko.algorithms.codingbat.string2

sealed interface PlusOut {
    operator fun invoke(str: String, word: String): String

    object SlidingWindow : PlusOut {
        override fun invoke(str: String, word: String): String {
            val sb = StringBuilder()
            var i = 0
            while (i < str.length) {
                if (i <= str.length - word.length && str.substring(i, i + word.length) == word) {
                    sb.append(str.substring(i, i + word.length))
                    i += word.length
                } else {
                    sb.append("+")
                    i++
                }
            }
            return sb.toString()
        }
    }
}
