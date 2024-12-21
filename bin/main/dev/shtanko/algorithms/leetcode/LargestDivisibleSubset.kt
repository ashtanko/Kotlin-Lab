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
 * 368. Largest Divisible Subset
 * @see <a href="https://leetcode.com/problems/largest-divisible-subset/">Source</a>
 */
fun interface LargestDivisibleSubset {
    operator fun invoke(nums: IntArray): List<Int>
}

class LargestDivisibleSubsetDFS : LargestDivisibleSubset {

    private val mem: MutableMap<Int, List<Int>> = HashMap()

    override operator fun invoke(nums: IntArray): List<Int> {
        nums.sort()
        return helper(nums, 0)
    }

    private fun helper(nums: IntArray, i: Int): List<Int> {
        if (mem.containsKey(i)) {
            return mem[i] ?: emptyList()
        }
        var maxLenLst: List<Int> = ArrayList()
        val div = if (i == 0) 1 else nums[i - 1]
        for (k in i until nums.size) {
            if (nums[k] % div == 0) {
                // make a copy is crucial here, so that we won't modify the returned List reference
                val lst: MutableList<Int> = ArrayList(helper(nums, k + 1))
                lst.add(nums[k])
                if (lst.size > maxLenLst.size) maxLenLst = lst
            }
        }
        mem[i] = maxLenLst
        return maxLenLst
    }
}
