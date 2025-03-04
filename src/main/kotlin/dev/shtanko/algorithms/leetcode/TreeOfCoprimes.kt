/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.DFS

/**
 * 1766. Tree of Coprimes
 * @see <a href="https://leetcode.com/problems/tree-of-coprimes/">Source</a>
 */
fun interface TreeOfCoprimes {
    operator fun invoke(nums: IntArray, edges: Array<IntArray>): IntArray
}

@DFS
class TreeOfCoprimesDFS : TreeOfCoprimes {

    lateinit var ans: IntArray

    override fun invoke(nums: IntArray, edges: Array<IntArray>): IntArray {
        val path: Array<MutableList<Pair>> = Array(LIMIT) { ArrayList() }
        for (i in 0 until LIMIT) path[i] = ArrayList()
        val n = nums.size
        ans = IntArray(n)
        val graph: Array<MutableList<Int>> = Array(n) { ArrayList() }
        for (i in 0 until n) graph[i] = ArrayList()
        for (edge in edges) {
            graph[edge[0]].add(edge[1])
            graph[edge[1]].add(edge[0])
        }
        solve(graph, path, nums, 0, -1, 0)
        return ans
    }

    fun solve(
        graph: Array<MutableList<Int>>,
        path: Array<MutableList<Pair>>,
        nums: IntArray,
        src: Int,
        pre: Int,
        curDepth: Int,
    ) {
        // pre-order traversal
        var closestIdx = -1
        var maxDepth = -1
        for (i in 1 until LIMIT) {
            if (gcd(nums[src], i) == 1 && path[i].isNotEmpty() && path[i][path[i].size - 1].depth > maxDepth) {
                closestIdx = path[i][path[i].size - 1].idx
                maxDepth = path[i][path[i].size - 1].depth
            }
        }
        ans[src] = closestIdx
        path[nums[src]].add(Pair(src, curDepth)) // add node to the path
        for (nei in graph[src]) {
            if (nei == pre) continue
            solve(graph, path, nums, nei, src, curDepth + 1)
        }
        path[nums[src]].removeAt(path[nums[src]].size - 1) // remove node from the path
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }

    class Pair(var idx: Int, var depth: Int)

    companion object {
        private const val LIMIT = 51
    }
}
