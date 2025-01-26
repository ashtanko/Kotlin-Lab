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

import kotlin.math.pow

/**
 * 78. Subsets
 * @see <a href="https://leetcode.com/problems/subsets/">Source</a>
 */
fun interface Subsets {
    operator fun invoke(nums: IntArray): List<List<Int>>
}

/**
 * Approach 1: Cascading
 */
class CascadingSubsets : Subsets {
    override operator fun invoke(nums: IntArray): List<List<Int>> {
        val output: MutableList<List<Int>> = ArrayList()
        output.add(ArrayList())

        for (num in nums) {
            val newSubsets: MutableList<List<Int>> = ArrayList()
            for (curr in output) {
                newSubsets.add(
                    object : ArrayList<Int>(curr) {
                        init {
                            add(num)
                        }
                    },
                )
            }
            for (curr in newSubsets) {
                output.add(curr)
            }
        }
        return output
    }
}

/**
 * Approach 2: Backtracking
 */
class BacktrackingSubsets : Subsets {

    override operator fun invoke(nums: IntArray): List<List<Int>> {
        val result: MutableList<List<Int>> = ArrayList()
        backtrack(result, ArrayList(), nums, 0)
        return result
    }

    private fun backtrack(result: MutableList<List<Int>>, tempList: MutableList<Int>, nums: IntArray, start: Int) {
        if (nums.size == start) {
            result.add(ArrayList(tempList))
            return
        }
        tempList.add(nums[start])
        backtrack(result, tempList, nums, start + 1)
        tempList.removeAt(tempList.size - 1)
        backtrack(result, tempList, nums, start + 1)
    }
}

/**
 * Approach 3: Lexicographic (Binary Sorted) Subsets
 */
class LexicographicSubsets : Subsets {
    override operator fun invoke(nums: IntArray): List<List<Int>> {
        val output: MutableList<List<Int>> = ArrayList()
        val n: Int = nums.size

        for (i in 2.0.pow(n.toDouble()).toInt() until 2.0.pow((n + 1).toDouble()).toInt()) {
            // generate bitmask, from 0..00 to 1..11
            val bitmask = Integer.toBinaryString(i).substring(1)

            // append subset corresponding to that bitmask
            val curr: MutableList<Int> = ArrayList()
            for (j in 0 until n) {
                if (bitmask[j] == '1') curr.add(nums[j])
            }
            output.add(curr)
        }
        return output
    }
}
