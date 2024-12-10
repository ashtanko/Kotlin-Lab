/*
 * Copyright 2020 Oleksii Shtanko
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

import dev.shtanko.extensions.isEven
import dev.shtanko.extensions.swap
import java.util.Arrays

/**
 * 905. Sort Array By Parity
 * @see <a href="https://leetcode.com/problems/sort-array-by-parity">Source</a>
 */
fun interface SortArrayByParity {
    operator fun invoke(nums: IntArray): IntArray
}

/**
 * Approach 1: Stream
 */
class SortArrayByParityStream : SortArrayByParity {
    override operator fun invoke(nums: IntArray): IntArray {
        return Arrays.stream(nums)
            .boxed()
            .sorted { a, b -> (a % 2).compareTo(b % 2) }
            .mapToInt { i -> i }
            .toArray()
    }
}

/**
 * Approach 1: Kotlin
 */
class SortArrayByParityKotlin : SortArrayByParity {
    override operator fun invoke(nums: IntArray): IntArray = nums
        .sortedWith { a, b -> (a % 2).compareTo(b % 2) }
        .toIntArray()
}

class SortArrayByParityTwoPass : SortArrayByParity {
    override operator fun invoke(nums: IntArray): IntArray {
        val ans = IntArray(nums.size)
        var t = 0

        for (i in nums.indices) {
            if (nums[i] % 2 == 0) ans[t++] = nums[i]
        }

        for (i in nums.indices) {
            if (nums[i] % 2 == 1) ans[t++] = nums[i]
        }

        return ans
    }
}

class SortArrayByParityInPlace : SortArrayByParity {
    override operator fun invoke(nums: IntArray): IntArray = nums.sortArrayByParity()

    private fun IntArray.sortArrayByParity(): IntArray {
        var i = 0
        var j = size - 1
        while (i < j) {
            if (this[i].isEven) {
                i++
            } else {
                if (!this[j].isEven) {
                    j--
                }
                if (this[j].isEven) {
                    swap(i, j)
                    i++
                    j--
                }
            }
        }
        return this
    }
}
