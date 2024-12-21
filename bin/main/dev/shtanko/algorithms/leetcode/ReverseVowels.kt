/*
 * Copyright 2022 Oleksii Shtanko
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
 * 345. Reverse Vowels of a String
 * https://leetcode.com/problems/reverse-vowels-of-a-string/
 */
fun interface ReverseVowels {
    operator fun invoke(s: String): String
}

class ReverseVowelsTwoPointers : ReverseVowels {

    companion object {
        private const val VOWELS = "aeiouAEIOU"
    }

    override operator fun invoke(s: String): String {
        if (s.isEmpty()) return s
        val chars: CharArray = s.toCharArray()
        var start = 0
        var end: Int = s.length - 1
        while (start < end) {
            while (start < end && !VOWELS.contains(chars[start].toString() + "")) {
                start++
            }
            while (start < end && !VOWELS.contains(chars[end].toString() + "")) {
                end--
            }
            val temp = chars[start]
            chars[start] = chars[end]
            chars[end] = temp
            start++
            end--
        }
        return String(chars)
    }
}

class ReverseVowelsSet : ReverseVowels {
    override operator fun invoke(s: String): String {
        val set: Set<Char> = HashSet(listOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'))
        val arr: CharArray = s.toCharArray()
        var left = 0
        var right = arr.size - 1
        while (left < right) {
            if (!set.contains(arr[left])) {
                left++
            } else if (!set.contains(arr[right])) {
                right--
            } else {
                val tmp = arr[left]
                arr[left] = arr[right]
                arr[right] = tmp
                left++
                right--
            }
        }
        return String(arr)
    }
}
