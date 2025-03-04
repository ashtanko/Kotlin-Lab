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
 * 1963. Minimum Number of Swaps to Make the String Balanced
 * @see <a href="https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/">Source</a>
 */
@Medium("https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced")
fun interface MinSwapsToMakeStringBalanced {
    operator fun invoke(str: String): Int
}

class MinSwapsToMakeStringBalancedStack : MinSwapsToMakeStringBalanced {
    override fun invoke(str: String): Int {
        var stackSize = 0
        for (ch in str) {
            // If the character is an opening bracket, increment the stack size.
            if (ch == '[') {
                stackSize++
            } else {
                // If the character is a closing bracket, and we have an opening bracket, decrease
                // the stack size.
                if (stackSize > 0) stackSize--
            }
        }
        return (stackSize + 1) / 2
    }
}
