package dev.shtanko.algorithms.strings

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT

internal fun interface CheckAnagrams {
    operator fun invoke(s1: String, s2: String): Boolean
}

class CheckAnagramsSort : CheckAnagrams {
    override fun invoke(s1: String, s2: String): Boolean {
        return s1.lowercase().toCharArray().sorted() == s2.lowercase().toCharArray().sorted()
    }
}

class CheckAnagramsBruteForce : CheckAnagrams {
    override fun invoke(s1: String, s2: String): Boolean {
        if (s1.length != s2.length) return false

        val count = IntArray(ALPHABET_LETTERS_COUNT)

        for (i in s1.indices) {
            val c1 = s1[i].lowercaseChar()
            val c2 = s2[i].lowercaseChar()

            if (c1 !in 'a'..'z' || c2 !in 'a'..'z') return false

            count[c1 - 'a']++
            count[c2 - 'a']--
        }

        return count.all { it == 0 }
    }
}
