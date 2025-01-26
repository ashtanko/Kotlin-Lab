/*
 * Copyright 2023 Oleksii Shtanko
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

/**
 * Reverse Vowels of a String:
 * Write a function that takes a string as input and reverse only the vowels of the string.
 */
fun reverseVowels(s: String): String {
    val vowels = "aeiouAEIOU"
    val charArray = s.toCharArray()
    var left = 0
    var right = charArray.size - 1

    while (left < right) {
        while (left < right && charArray[left] !in vowels) left++
        while (left < right && charArray[right] !in vowels) right--

        val temp = charArray[left]
        charArray[left] = charArray[right]
        charArray[right] = temp

        left++
        right--
    }

    return String(charArray)
}
