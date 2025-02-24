/*
 * Copyright 2020 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import kotlin.math.abs

/**
 * 564. Find the Closest Palindrome
 * @see <a href="https://leetcode.com/problems/find-the-closest-palindrome/">Find the Closest Palindrome</a>
 */
fun interface FindClosestPalindrome {
    operator fun invoke(num: String): String
}

class FindClosestPalindromeRange : FindClosestPalindrome {
    override fun invoke(num: String): String {
        return num.nearestPalindromic()
    }

    /**
     * This function finds the nearest palindromic number for a given number represented as a string.
     * It throws a NumberFormatException if the string cannot be converted to a Long.
     * @return The nearest palindromic number as a string.
     */
    @Throws(NumberFormatException::class)
    private fun String.nearestPalindromic(): String {
        if (this.isEmpty()) return ""
        if (this == "1") return "0"
        val mirrored = mirroring()
        val originalNumber = this.toLong()
        val mirroredNumber = mirrored.toLong()
        var difference1 = abs(originalNumber - mirroredNumber)
        if (difference1 == 0L) difference1 = Long.MAX_VALUE

        val smallerPalindromic = findSmallerPalindromic(this)
        val difference2 = abs(originalNumber - smallerPalindromic.toLong())

        val largerPalindromic = findLargerPalindromic(this)
        val difference3 = abs(originalNumber - largerPalindromic.toLong())

        return when {
            difference2 <= difference1 && difference2 <= difference3 -> smallerPalindromic
            difference1 <= difference3 -> mirrored
            else -> largerPalindromic
        }
    }

    /**
     * This function mirrors the first half of the string onto the second half.
     * @return The mirrored string.
     */
    private fun String.mirroring(): String {
        val len = this.length
        val sb = StringBuilder(this.substring(0, (len + 1) / 2))
        for (i in (len / 2 - 1) downTo 0) {
            sb.append(sb[i])
        }
        return sb.toString()
    }

    /**
     * This function finds the next smaller palindromic number for a given number represented as a string.
     * @return The next smaller palindromic number as a string.
     */
    private fun findSmallerPalindromic(s: String): String {
        val sb = StringBuilder(s)
        var index = (sb.length - 1) / 2
        while (index >= 0 && sb[index] == '0') {
            sb.replace(index, index + 1, "9")
            index--
        }
        if (index == 0 && sb[index] == '1') {
            sb.delete(0, 1)
            val mid = (sb.length - 1) / 2
            sb.replace(mid, mid + 1, "9")
        } else if (index >= 0) {
            sb.replace(index, index + 1, (sb[index] - 1).toString())
        }
        return sb.toString().mirroring()
    }

    /**
     * This function finds the next larger palindromic number for a given number represented as a string.
     * @return The next larger palindromic number as a string.
     */
    private fun findLargerPalindromic(s: String): String {
        val sb = StringBuilder(s)
        var index = (sb.length - 1) / 2
        while (index >= 0 && sb[index] == '9') {
            sb.replace(index, index + 1, "0")
            index--
        }
        if (index < 0) {
            sb.insert(0, "1")
        } else {
            sb.replace(index, index + 1, (sb[index] + 1).toString())
        }
        return sb.toString().mirroring()
    }
}

class FindClosestPalindromeBS : FindClosestPalindrome {
    override fun invoke(num: String): String {
        if (num.isEmpty()) return ""
        val originalNumber = num.toLong()
        val maxLimit = originalNumber * 2
        var lowerBound = 0L
        var upperBound = originalNumber

        // Binary search for the largest palindrome less than the original number
        while (lowerBound <= upperBound) {
            val mid = lowerBound + (upperBound - lowerBound) / 2
            if (transformIntoPalindrome(mid) < originalNumber) {
                lowerBound = mid + 1
            } else {
                upperBound = mid - 1
            }
        }
        val closestLowerPalindrome = transformIntoPalindrome(upperBound)

        lowerBound = originalNumber
        upperBound = maxLimit

        // Binary search for the smallest palindrome greater than the original number
        while (lowerBound <= upperBound) {
            val mid = lowerBound + (upperBound - lowerBound) / 2
            if (transformIntoPalindrome(mid) <= originalNumber) {
                lowerBound = mid + 1
            } else {
                upperBound = mid - 1
            }
        }
        val closestUpperPalindrome = transformIntoPalindrome(lowerBound)

        return if (closestUpperPalindrome - originalNumber < originalNumber - closestLowerPalindrome) {
            closestUpperPalindrome.toString()
        } else {
            closestLowerPalindrome.toString()
        }
    }

    private fun transformIntoPalindrome(number: Long): Long {
        val digits = number.toString().toCharArray()
        var left = 0
        var right = digits.size - 1
        while (left < right) {
            digits[right] = digits[left]
            left++
            right--
        }
        return String(digits).toLong()
    }
}
