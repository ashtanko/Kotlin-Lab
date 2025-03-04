/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.annotations.level.Easy

/**
 * 1221. Split a String in Balanced Strings
 * @see <a href="https://leetcode.com/problems/split-a-string-in-balanced-strings">Source</a>
 */
@Easy("https://leetcode.com/problems/split-a-string-in-balanced-strings")
fun String.balancedStringSplit(): Int {
    var balancedCount = 0
    var balance = 0
    for (char in this) {
        balance += if (char == 'L') 1 else -1
        if (balance == 0) {
            balancedCount++
        }
    }
    return balancedCount
}
