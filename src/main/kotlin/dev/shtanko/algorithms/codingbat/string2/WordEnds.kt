package dev.shtanko.algorithms.codingbat.string2

sealed interface WordEnds {
    operator fun invoke(str: String, word: String): String

    object SlidingWindow : WordEnds {
        override fun invoke(str: String, word: String): String {
            if (str.length < word.length || str == word) return ""
            val sb = StringBuilder()
            var i = 0
            while (i < str.length) {
                if (i <= str.length - word.length && str.substring(i, i + word.length) == word) {
                    when (i) {
                        0 -> {
                            sb.append(str[word.length])
                        }

                        str.length - word.length -> {
                            sb.append(str[(str.length - word.length) - 1])
                        }

                        else -> {
                            sb.append(str[i - 1]).append(str[i + word.length])
                        }
                    }
                    i += word.length
                } else {
                    i++
                }
            }
            return sb.toString()
        }
    }

    object SubstringScanning : WordEnds {
        override fun invoke(str: String, word: String): String {
            val sb = StringBuilder()
            val chunk = word.length

            for (i in 0..str.length - chunk) {
                if (str.substring(i, i + chunk) == word) {
                    if (i > 0) {
                        sb.append(str[i - 1])
                    }
                    if (i + chunk < str.length) {
                        sb.append(str[i + chunk])
                    }
                }
            }
            return sb.toString()
        }
    }

    object SlidingWindowOptimized : WordEnds {
        override fun invoke(str: String, word: String): String {
            if (word.isEmpty() || str.length < word.length) return ""

            val sb = StringBuilder()
            var i = 0
            val wLen = word.length

            while (i <= str.length - wLen) {
                if (str.regionMatches(i, word, 0, wLen)) {
                    if (i > 0) sb.append(str[i - 1])
                    if (i + wLen < str.length) sb.append(str[i + wLen])
                    i += wLen
                } else {
                    i++
                }
            }

            return sb.toString()
        }
    }
}
