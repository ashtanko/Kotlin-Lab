package dev.shtanko.algorithms.codingbat.string2

sealed interface OneTwo {
    operator fun invoke(str: String): String

    object Chunked : OneTwo {
        override fun invoke(str: String): String {
            return str.chunked(3).filter { it.length == 3 }.joinToString("") {
                it.drop(1) + it.first()
            }
        }
    }

    object Iterative : OneTwo {
        override fun invoke(str: String): String {
            val sb = StringBuilder()
            for (i in 0..str.length - 3 step 3) {
                val chunk = str.substring(i, i + 3)
                sb.append(chunk.drop(1)).append(chunk.first())
            }
            return sb.toString()
        }
    }
}
