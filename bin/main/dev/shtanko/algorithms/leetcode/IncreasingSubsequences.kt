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

import java.util.LinkedList

/**
 * 491. Increasing Subsequences
 * @see <a href="https://leetcode.com/problems/increasing-subsequences/">Source</a>
 */
fun interface IncreasingSubsequences {
    fun findSubsequences(nums: IntArray): List<List<Int>>
}

class IncreasingSubsequencesBacktracking : IncreasingSubsequences {
    override fun findSubsequences(nums: IntArray): List<List<Int>> {
        val res: MutableList<List<Int>> = LinkedList()
        helper(LinkedList(), 0, nums, res)
        return res
    }

    private fun helper(list: LinkedList<Int>, index: Int, nums: IntArray, res: MutableList<List<Int>>) {
        if (list.size > 1) res.add(LinkedList(list))
        val used: MutableSet<Int> = HashSet()
        for (i in index until nums.size) {
            if (used.contains(nums[i])) continue
            if (list.isEmpty() || nums[i] >= list.peekLast()) {
                used.add(nums[i])
                list.add(nums[i])
                helper(list, i + 1, nums, res)
                list.removeAt(list.size - 1)
            }
        }
    }
}
