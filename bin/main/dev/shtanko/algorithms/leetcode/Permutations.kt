/*
 * Copyright 2021 Oleksii Shtanko
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

import java.util.Collections
import java.util.LinkedList

fun interface Permutations {
    fun permute(nums: IntArray): List<List<Int>>
}

class PermutationsBacktracking : Permutations {
    override fun permute(nums: IntArray): List<List<Int>> {
        // init output list
        val output: MutableList<List<Int>> = LinkedList()

        // convert nums into list since the output is a list of lists
        val numsList: MutableList<Int> = ArrayList()
        for (num in nums) numsList.add(num)
        val n = nums.size
        backtrack(n, numsList, output, 0)
        return output
    }

    private fun backtrack(
        n: Int,
        nums: List<Int>,
        output: MutableList<List<Int>>,
        first: Int,
    ) {
        // if all integers are used up
        if (first == n) output.add(ArrayList(nums))
        for (i in first until n) {
            // place i-th integer first
            // in the current permutation
            Collections.swap(nums, first, i)
            // use next integers to complete the permutations
            backtrack(n, nums, output, first + 1)
            // backtrack
            Collections.swap(nums, first, i)
        }
    }
}
