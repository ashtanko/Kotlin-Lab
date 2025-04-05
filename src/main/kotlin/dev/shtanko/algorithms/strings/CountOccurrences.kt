package dev.shtanko.algorithms.strings

internal fun interface CountOccurrences {
    operator fun invoke(input: String, char: Char, index: Int, count: Int): Int
}

class CountOccurrencesRecursive : CountOccurrences {
    override fun invoke(input: String, char: Char, index: Int, count: Int): Int {
        if (index == input.length) return count
        return invoke(input, char, index + 1, if (input[index] == char) count + 1 else count)
    }
}
