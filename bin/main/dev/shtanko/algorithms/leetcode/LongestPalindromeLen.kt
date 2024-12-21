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

package dev.shtanko.algorithms.leetcode

/**
 * 409. Longest Palindrome
 * @see <a href="https://leetcode.com/problems/longest-palindrome">Source</a>
 */
fun interface LongestPalindromeLen {
    operator fun invoke(str: String): Int
}

class LongestPalindromeLenHashSet : LongestPalindromeLen {
    override fun invoke(str: String): Int {
        val characterSet: MutableSet<Char> = HashSet()
        var res = 0

        for (c in str.toCharArray()) {
            if (characterSet.contains(c)) {
                characterSet.remove(c)
                res += 2
            } else {
                characterSet.add(c)
            }
        }

        if (characterSet.isNotEmpty()) {
            res++
        }

        return res
    }
}
