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

import dev.shtanko.extensions.swap

fun interface WiggleSort {
    operator fun invoke(nums: IntArray)
}

/**
 * Approach #1 (Sorting)
 */
class WiggleSortBruteForce : WiggleSort {
    override operator fun invoke(nums: IntArray) {
        for (i in 1 until nums.size) {
            val a = nums[i - 1]
            if (i % 2 == 1 == a > nums[i]) {
                nums[i - 1] = nums[i]
                nums[i] = a
            }
        }
    }
}

/**
 * Approach #2 (One-pass Swap)
 */
class WiggleSortOnePassSwap : WiggleSort {
    override operator fun invoke(nums: IntArray) {
        var less = true
        for (i in 0 until nums.size - 1) {
            if (less) {
                if (nums[i] > nums[i + 1]) {
                    nums.swap(i, i + 1)
                }
            } else {
                if (nums[i] < nums[i + 1]) {
                    nums.swap(i, i + 1)
                }
            }
            less = !less
        }
    }
}
