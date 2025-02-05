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
 * 2440. Create Components With Same Value
 * @see <a href="https://leetcode.com/problems/create-components-with-same-value/">Source</a>
 */
fun interface ComponentValue {
    operator fun invoke(nums: IntArray, edges: Array<IntArray>): Int
}

class ComponentValueImpl : ComponentValue {
    override operator fun invoke(nums: IntArray, edges: Array<IntArray>): Int {
        val n = nums.size
        val graph: Array<MutableList<Int>> = Array(n) { ArrayList() }
        for (i in 0 until n) {
            graph[i] = ArrayList()
        }
        for (e in edges) {
            graph[e[0]].add(e[1])
            graph[e[1]].add(e[0])
        }
        var sum = 0
        for (i in nums) {
            sum += i
        }
        for (k in n downTo 1) {
            if (sum % k != 0) continue
            val ans = helper(nums, graph, 0, -1, sum / k)
            if (ans == 0) return k - 1
        }
        return 0
    }

    private fun helper(nums: IntArray, graph: Array<MutableList<Int>>, i: Int, prev: Int, target: Int): Int {
        if (graph[i].size == 1 && graph[i][0] == prev) {
            if (nums[i] > target) return -1
            return if (nums[i] == target) 0 else nums[i]
        }
        var sum = nums[i]
        for (k in graph[i]) {
            if (k == prev) continue
            val ans = helper(nums, graph, k, i, target)
            if (ans == -1) return -1
            sum += ans
        }
        if (sum > target) return -1
        return if (sum == target) 0 else sum
    }
}
