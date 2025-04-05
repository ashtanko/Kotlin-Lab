package dev.shtanko.algorithms.strings

internal fun interface SearchForSubstring {
    operator fun invoke(input: String, toFind: String): Int
}

class SearchForSubstringStd : SearchForSubstring {
    override fun invoke(input: String, toFind: String): Int = input.indexOf(toFind)
}

class SearchForSubstringBruteForce : SearchForSubstring {
    override fun invoke(input: String, toFind: String): Int {
        for (i in 0..input.length - toFind.length) {
            var match = true
            for (j in toFind.indices) {
                if (input[i + j] != toFind[j]) {
                    match = false
                    break
                }
            }
            if (match) return i
        }
        return -1
    }
}
