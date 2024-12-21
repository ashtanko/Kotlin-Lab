/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.algorithms.twopointers

fun interface IsPalindrome {
    operator fun invoke(str: String): Boolean
}

/**
 * Implementation of the IsPalindrome interface using a two-pointers approach.
 * This method checks for palindrome by comparing characters from both ends of the string moving towards the center.
 */
val isPalindromeTwoPointers = IsPalindrome { str ->
    var left = 0
    var right = str.length - 1

    while (left < right) {
        while (left < right && !str[left].isLetterOrDigit()) {
            left++
        }
        while (left < right && !str[right].isLetterOrDigit()) {
            right--
        }

        if (str[left].lowercaseChar() != str[right].lowercaseChar()) {
            return@IsPalindrome false
        }
        left++
        right--
    }
    return@IsPalindrome true
}

/**
 * Simplified implementation of the IsPalindrome interface.
 * This method cleans the string by removing non-alphanumeric characters and converting it to lowercase,
 * then checks if the cleaned string is equal to its reverse.
 */
val isPalindromeSimplified = IsPalindrome { str ->
    val cleaned = str.filter { it.isLetterOrDigit() }.lowercase()
    return@IsPalindrome cleaned == cleaned.reversed()
}
