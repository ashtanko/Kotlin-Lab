/*
 * Copyright 2023 Oleksii Shtanko
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
import java.util.Queue

/**
 * 1424. Diagonal Traverse II
 * @see <a href="https://leetcode.com/problems/diagonal-traverse-ii">Source</a>
 */
fun interface DiagonalTraverse2 {
    operator fun invoke(nums: List<List<Int>>): IntArray
}

/**
 * Approach 1: Group Elements by the Sum of Row and Column Indices
 */
class DiagonalTraverse2Group : DiagonalTraverse2 {
    override fun invoke(nums: List<List<Int>>): IntArray {
        val groups: MutableMap<Int, MutableList<Int>> = HashMap()
        var n = 0
        for (row in nums.size - 1 downTo 0) {
            for (col in 0 until nums[row].size) {
                val diagonal: Int = row + col
                if (!groups.containsKey(diagonal)) {
                    groups[diagonal] = ArrayList()
                }
                groups[diagonal]?.add(nums[row][col])
                n++
            }
        }

        val ans = IntArray(n)
        var i = 0
        var curr = 0

        while (groups.containsKey(curr)) {
            for (num in groups.getOrDefault(curr, mutableListOf())) {
                ans[i] = num
                i++
            }
            curr++
        }

        return ans
    }
}

/**
 * Approach 2: Breadth First Search
 */
class DiagonalTraverse2BFS : DiagonalTraverse2 {
    override fun invoke(nums: List<List<Int>>): IntArray {
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        queue.offer(Pair(0, 0))
        val ans: MutableList<Int?> = ArrayList()

        while (queue.isNotEmpty()) {
            val p: Pair<Int, Int> = queue.poll()
            val row: Int = p.first
            val col: Int = p.second
            ans.add(nums[row][col])
            if (col == 0 && row + 1 < nums.size) {
                queue.offer(Pair(row + 1, col))
            }
            if (col + 1 < nums[row].size) {
                queue.offer(Pair(row, col + 1))
            }
        }

        val result = IntArray(ans.size)
        for ((i, num) in ans.withIndex()) {
            result[i] = num ?: 0
        }

        return result
    }
}
