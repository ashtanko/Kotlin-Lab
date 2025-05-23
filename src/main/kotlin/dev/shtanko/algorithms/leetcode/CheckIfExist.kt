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

import dev.shtanko.algorithms.annotations.level.Easy

/**
 * 1346. Check If N and Its Double Exist
 * @see <a href="https://leetcode.com/problems/check-if-n-and-its-double-exist/">Check If N and Its Double Exist</a>
 */
@Easy("https://leetcode.com/problems/check-if-n-and-its-double-exist/")
fun interface CheckIfExist {
    operator fun invoke(arr: IntArray): Boolean
}

class CheckIfExistHashMap : CheckIfExist {
    override fun invoke(arr: IntArray): Boolean {
        val map = mutableMapOf<Int, Int>()

        for (num in arr) {
            // Count occurrences of each number
            map[num] = map.getOrDefault(num, 0) + 1
        }

        for (num in arr) {
            // Check for double
            if (num != 0 && map.containsKey(2 * num)) {
                return true
            }
            // Handle zero case (ensure there are at least two zeros)
            if (num == 0 && map.getOrDefault(num, 0) > 1) {
                return true
            }
        }
        return false
    }
}
