package dev.shtanko.algorithms.strings

internal fun interface RemoveAllWhitespace {
    operator fun invoke(input: String): String
}

class RemoveAllWhitespaceBruteForce : RemoveAllWhitespace {
    override fun invoke(input: String): String {
        val result = StringBuilder()
        for (c in input) {
            if (c != ' ' && c != '\t') result.append(c)
        }
        return result.toString()
    }
}
