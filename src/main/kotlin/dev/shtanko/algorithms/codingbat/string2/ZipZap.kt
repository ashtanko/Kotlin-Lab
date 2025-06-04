package dev.shtanko.algorithms.codingbat.string2

sealed interface ZipZap {
    operator fun invoke(str: String): String

    object Iterative : ZipZap {
        override fun invoke(str: String): String {
            val sb = StringBuilder()
            var i = 0
            while (i < str.length) {
                if (i <= str.length - 3 && str[i] == 'z' && str[i + 2] == 'p') {
                    sb.append("zp")
                    i += 3
                } else {
                    sb.append(str[i])
                    i++
                }
            }
            return sb.toString()
        }
    }
}
