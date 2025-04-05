package dev.shtanko.algorithms.strings

import dev.shtanko.algorithms.learn.reversed

internal fun interface ReverseString {
    operator fun invoke(input: String): String
}

class ReverseStringStd : ReverseString {
    override fun invoke(input: String): String = input.reversed()
}

class ReverseStringTwoPointers : ReverseString {
    override fun invoke(input: String): String {
        val chars = input.toCharArray()
        var left = 0
        var right = input.length - 1

        while (left < right) {
            val temp = chars[left]
            chars[left] = chars[right]
            chars[right] = temp
            left++
            right--
        }
        return String(chars)
    }
}
