/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2516. Take K of Each Character From Left and Right
 * @see <a href="https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/">Source</a>
 */
@Medium("https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/")
fun interface TakeCharacters {
    operator fun invoke(str: String, k: Int): Int
}

data object TakeCharactersSlidingWindow : TakeCharacters {
    override fun invoke(str: String, k: Int): Int {
        val count = IntArray(3)
        val n = str.length

        // Count total occurrences
        for (c in str) {
            count[c - 'a']++
        }

        // Check if we have enough characters
        for (i in 0..2) {
            if (count[i] < k) return -1
        }

        val window = IntArray(3)
        var left = 0
        var maxWindow = 0

        // Helper function to check if we have enough characters left
        fun hasEnoughOutside(): Boolean {
            return (0..2).any { count[it] - window[it] < k }
        }

        // Find the longest window that leaves k of each character outside
        for (right in str.indices) {
            window[str[right] - 'a']++

            // Shrink window if we take too many characters
            while (left <= right && hasEnoughOutside()) {
                window[str[left] - 'a']--
                left++
            }

            maxWindow = maxOf(maxWindow, right - left + 1)
        }

        return n - maxWindow
    }
}
