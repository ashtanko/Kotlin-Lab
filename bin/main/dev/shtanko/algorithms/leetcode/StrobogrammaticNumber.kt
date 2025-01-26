/*
 * Copyright 2021 Oleksii Shtanko
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

/**
 * 246. Strobogrammatic Number
 */
fun interface StrobogrammaticNumber {
    operator fun invoke(num: String): Boolean
}

/**
 * Approach 1: Make a Rotated Copy
 * Time complexity : O(N).
 * Space complexity : O(N).
 */
class StrobogrammaticRotated : StrobogrammaticNumber {
    override operator fun invoke(num: String): Boolean {
        val rotatedDigits = charArrayOf('0', '1', '\u0000', '\u0000', '\u0000', '\u0000', '9', '\u0000', '8', '6')
        val rotatedStringBuilder = StringBuilder()
        for (i in num.length - 1 downTo 0) {
            val c: Char = num[i]
            val charIndex = Character.getNumericValue(c)
            rotatedStringBuilder.append(rotatedDigits[charIndex])
        }

        val rotatedString = rotatedStringBuilder.toString()
        return num == rotatedString
    }
}

/**
 * Approach 2: Two Pointers
 * Time complexity : O(N).
 * Space complexity : O(1).
 */
class StrobogrammaticTwoPointers : StrobogrammaticNumber {
    override operator fun invoke(num: String): Boolean {
        val rotatedDigits: Map<Char, Char> = hashMapOf('0' to '0', '1' to '1', '6' to '9', '8' to '8', '9' to '6')

        var left = 0
        var right: Int = num.length - 1
        while (left <= right) {
            val leftChar: Char = num[left]
            val rightChar: Char = num[right]
            if (!rotatedDigits.containsKey(leftChar) || rotatedDigits[leftChar] != rightChar) {
                return false
            }
            left++
            right--
        }

        return true
    }
}
