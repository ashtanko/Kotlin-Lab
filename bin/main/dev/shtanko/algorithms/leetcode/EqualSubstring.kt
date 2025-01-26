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
 * 1208. Get Equal Substrings Within Budget
 * @see <a href="https://leetcode.com/problems/get-equal-substrings-within-budget/">Source</a>
 */
fun interface EqualSubstring {
    operator fun invoke(str: String, target: String, maxCost: Int): Int
}

/**
 * # Approach
 *
 * - The problem can be solved using the sliding window technique.
 * - The idea is to keep track of the cost of converting the current substring in `s` to `t`.
 * - We will keep track of the starting index of the current substring.
 * - We will add the current index to the substring and calculate the cost.
 * - If the cost exceeds the allowed cost, we will remove the indices from the left end till the cost becomes less
 * than allowed.
 * - We will keep track of the maximum length of the substring.
 * - The final answer will be the maximum length of the substring.
 *
 * # Complexity
 *
 * - The time complexity is `O(n)` where `n` is the length of the given string.
 * - The space complexity is `O(1)`.
 */
class EqualSubstringSlidingWindow : EqualSubstring {
    override fun invoke(str: String, target: String, maxCost: Int): Int {
        val n: Int = str.length

        var maxLen = 0

        // Starting index of the current substring
        var start = 0

        // Cost of converting the current substring in s to t
        var currCost = 0

        for (i in 0 until n) {
            // Add the current index to the substring
            currCost += kotlin.math.abs(str[i] - target[i])

            // Remove the indices from the left end till the cost becomes less than allowed
            while (currCost > maxCost) {
                currCost -= kotlin.math.abs(str[start] - target[start])
                start++
            }

            maxLen = kotlin.math.max(maxLen, (i - start + 1))
        }

        return maxLen
    }
}
