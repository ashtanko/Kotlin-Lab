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

import dev.shtanko.algorithms.annotations.DFS
import dev.shtanko.algorithms.annotations.Sort
import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

/**
 * 2050. Parallel Courses III
 * @see <a href="https://leetcode.com/problems/parallel-courses-iii">Source</a>
 */
fun interface ParallelCourses3 {
    operator fun invoke(n: Int, relations: Array<IntArray>, time: IntArray): Int
}

@Sort
class ParallelCourses3Sort : ParallelCourses3 {
    override fun invoke(n: Int, relations: Array<IntArray>, time: IntArray): Int {
        val graph: MutableMap<Int, MutableList<Int>> = HashMap()
        for (i in 0 until n) {
            graph[i] = ArrayList()
        }

        val indegree = IntArray(n)
        for (edge in relations) {
            val x = edge[0] - 1
            val y = edge[1] - 1
            graph[x]!!.add(y)
            indegree[y]++
        }

        val queue: Queue<Int> = LinkedList()
        val maxTime = IntArray(n)

        for (node in 0 until n) {
            if (indegree[node] == 0) {
                queue.add(node)
                maxTime[node] = time[node]
            }
        }

        while (queue.isNotEmpty()) {
            val node: Int = queue.remove()
            for (neighbor in graph[node]!!) {
                maxTime[neighbor] = max(maxTime[neighbor], maxTime[node] + time[neighbor])
                indegree[neighbor]--
                if (indegree[neighbor] == 0) {
                    queue.add(neighbor)
                }
            }
        }

        var ans = 0
        for (node in 0 until n) {
            ans = max(ans, maxTime[node])
        }

        return ans
    }
}

@DFS
class ParallelCourses3DFS : ParallelCourses3 {

    private val graph: MutableMap<Int, MutableList<Int>> = HashMap()
    private val memo: MutableMap<Int, Int> = HashMap()

    override fun invoke(n: Int, relations: Array<IntArray>, time: IntArray): Int {
        for (i in 0 until n) {
            graph[i] = ArrayList()
        }

        for (edge in relations) {
            val x = edge[0] - 1
            val y = edge[1] - 1
            graph[x]?.add(y)
        }

        var ans = 0
        for (node in 0 until n) {
            ans = max(ans, dfs(node, time))
        }

        return ans
    }

    private fun dfs(node: Int, time: IntArray): Int {
        if (memo.containsKey(node)) {
            return memo[node] ?: 0
        }
        if (graph[node]?.size == 0) {
            return time[node]
        }
        var ans = 0
        for (neighbor in graph[node]!!) {
            ans = max(ans, dfs(neighbor, time))
        }
        memo[node] = time[node] + ans
        return time[node] + ans
    }
}
