package dev.shtanko.algorithms.strings

internal fun interface CompressString {
    operator fun invoke(input: String): String
}

class CompressStringBruteForce : CompressString {
    override fun invoke(input: String): String {
        if (input.isEmpty()) return ""

        val result = StringBuilder()
        var count = 1

        for (i in 1 until input.length) {
            if (input[i] == input[i - 1]) {
                count++
            } else {
                result.append(input[i - 1]).append(count)
                count = 1
            }
        }
        result.append(input.last()).append(count)

        return result.toString()
    }
}
