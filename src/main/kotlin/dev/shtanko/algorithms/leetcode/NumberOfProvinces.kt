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

import dev.shtanko.algorithms.annotations.BFS
import dev.shtanko.algorithms.annotations.DFS
import java.util.LinkedList
import java.util.Queue

/**
 * 547. Number of Provinces
 * @see <a href="https://leetcode.com/problems/number-of-provinces/">Source</a>
 */
fun interface NumberOfProvinces {
    fun findCircleNum(isConnected: Array<IntArray>): Int
}

/**
 * Approach 1: Depth First Search
 */
@DFS
class NumberOfProvincesDFS : NumberOfProvinces {
    override fun findCircleNum(isConnected: Array<IntArray>): Int {
        val n: Int = isConnected.size
        var numberOfComponents = 0
        val visit = BooleanArray(n)

        for (i in 0 until n) {
            if (!visit[i]) {
                numberOfComponents++
                dfs(i, isConnected, visit)
            }
        }

        return numberOfComponents
    }

    private fun dfs(node: Int, isConnected: Array<IntArray>, visit: BooleanArray) {
        visit[node] = true
        for (i in isConnected.indices) {
            if (isConnected[node][i] == 1 && !visit[i]) {
                dfs(i, isConnected, visit)
            }
        }
    }
}

/**
 * Approach 2: Breadth First Search
 */
@BFS
class NumberOfProvincesBFS : NumberOfProvinces {
    override fun findCircleNum(isConnected: Array<IntArray>): Int {
        val n: Int = isConnected.size
        var numberOfComponents = 0
        val visit = BooleanArray(n)

        for (i in 0 until n) {
            if (!visit[i]) {
                numberOfComponents++
                bfs(i, isConnected, visit)
            }
        }

        return numberOfComponents
    }

    private fun bfs(node: Int, isConnected: Array<IntArray>, visit: BooleanArray) {
        var n = node
        val q: Queue<Int> = LinkedList()
        q.offer(n)
        visit[n] = true
        while (q.isNotEmpty()) {
            n = q.poll()
            for (i in isConnected.indices) {
                if (isConnected[n][i] == 1 && !visit[i]) {
                    q.offer(i)
                    visit[i] = true
                }
            }
        }
    }
}

/**
 * Approach 3: Union-find
 */
@dev.shtanko.algorithms.annotations.UnionFind
class NumberOfProvincesUnionFind : NumberOfProvinces {
    override fun findCircleNum(isConnected: Array<IntArray>): Int {
        val n: Int = isConnected.size
        val dsu = UnionFind(n)
        var numberOfComponents = n

        for (i in 0 until n) {
            for (j in i + 1 until n) {
                if (isConnected[i][j] == 1 && dsu.find(i) != dsu.find(j)) {
                    numberOfComponents--
                    dsu.unionSet(i, j)
                }
            }
        }

        return numberOfComponents
    }
}
