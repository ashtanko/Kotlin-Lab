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
 * 1655. Distribute Repeating Integers
 * @see <a href="https://leetcode.com/problems/distribute-repeating-integers/">Source</a>
 */
fun interface DistributeRepeatingIntegers {
    operator fun invoke(nums: IntArray, quantity: IntArray): Boolean
}

class DistributeRepeatingIntegersBottomUp : DistributeRepeatingIntegers {
    override fun invoke(nums: IntArray, quantity: IntArray): Boolean {
        val counts: MutableMap<Int, Int> = HashMap()
        for (num in nums) {
            counts[num] = counts.getOrDefault(num, 0) + 1
        }

        var idx = 0
        val arrCounts = IntArray(counts.size)
        for (key in counts.keys) {
            arrCounts[idx++] = counts[key] ?: 0
        }
        return solve(arrCounts, quantity)
    }

    private fun solve(counts: IntArray, quantity: IntArray): Boolean {
        counts.sort()
        quantity.sort()
        reverse(quantity)
        return solve(counts, quantity, 0)
    }

    private fun reverse(arr: IntArray) {
        var i = 0
        while (i + i < arr.size) {
            val tmp = arr[i]
            arr[i] = arr[arr.size - i - 1]
            arr[arr.size - i - 1] = tmp
            i++
        }
    }

    private fun solve(counts: IntArray, quantity: IntArray, idx: Int): Boolean {
        if (idx >= quantity.size) {
            return true
        }
        for (i in counts.indices) {
            if (counts[i] >= quantity[idx]) {
                counts[i] -= quantity[idx]
                if (solve(counts, quantity, idx + 1)) {
                    return true
                }
                counts[i] += quantity[idx]
            }
        }
        return false
    }
}
