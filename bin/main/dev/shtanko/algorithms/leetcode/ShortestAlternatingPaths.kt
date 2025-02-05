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
import kotlin.math.min

/**
 * 1129. Shortest Path with Alternating Colors
 * @see <a href="https://leetcode.com/problems/shortest-path-with-alternating-colors/">Source</a>
 */
fun interface ShortestAlternatingPaths {
    operator fun invoke(n: Int, redEdges: Array<IntArray>, blueEdges: Array<IntArray>): IntArray
}

class ShortestAlternatingPathsBFS : ShortestAlternatingPaths {
    override operator fun invoke(n: Int, redEdges: Array<IntArray>, blueEdges: Array<IntArray>): IntArray {
        val graph: Array<Array<MutableSet<Int>>> = Array<Array<MutableSet<Int>>>(n) {
            Array(2) { HashSet() }
        }

        for (i in 0 until n) {
            graph[i][0] = HashSet()
            graph[i][1] = HashSet()
        }

        for (re in redEdges) {
            graph[re[0]][0].add(re[1])
        }

        for (be in blueEdges) {
            graph[be[0]][1].add(be[1])
        }

        val res = Array(n) { IntArray(2) }
        for (i in 1 until n) {
            res[i][1] = n * 2
            res[i][0] = res[i][1]
        }

        val q: Queue<IntArray> = LinkedList()
        q.offer(intArrayOf(0, 0))
        q.offer(intArrayOf(0, 1))

        while (q.isNotEmpty()) {
            val cur: IntArray = q.poll()
            val ind = cur[0]
            val col = cur[1]
            for (next in graph[ind][1 - col]) {
                if (res[next][1 - col] == n * 2) {
                    res[next][1 - col] = res[ind][col] + 1
                    q.offer(intArrayOf(next, 1 - col))
                }
            }
        }

        val ans = IntArray(n)
        for (i in 0 until n) {
            val min = min(res[i][0], res[i][1])
            ans[i] = if (min == n * 2) -1 else min
        }

        return ans
    }
}
