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

import dev.shtanko.extensions.isEven

/**
 * 1550. Three Consecutive Odds
 * @see <a href="https://leetcode.com/problems/three-consecutive-odds">Source</a>
 */
fun interface ThreeConsecutiveOdds {
    operator fun invoke(arr: IntArray): Boolean
}

class ThreeConsecutiveOddsBruteForce : ThreeConsecutiveOdds {
    override fun invoke(arr: IntArray): Boolean {
        for (i in 0 until arr.size - 2) {
            if (arr[i] % 2 != 0 && arr[i + 1] % 2 != 0 && arr[i + 2] % 2 != 0) {
                return true
            }
        }
        return false
    }
}

class ThreeConsecutiveOddsCounting : ThreeConsecutiveOdds {
    override fun invoke(arr: IntArray): Boolean {
        var count = 0
        for (num in arr) {
            if (!num.isEven) {
                count++
            } else {
                count = 0
            }
            if (count == 3) {
                return true
            }
        }
        return false
    }
}

class ThreeNumbers : ThreeConsecutiveOdds {
    override fun invoke(arr: IntArray): Boolean {
        // Loop through the array up to the third-to-last element
        for (i in 0 until arr.size - 2) {
            val product = arr[i] * arr[i + 1] * arr[i + 2]
            // Check if the product is odd
            if (product % 2 == 1) return true
        }
        return false
    }
}
