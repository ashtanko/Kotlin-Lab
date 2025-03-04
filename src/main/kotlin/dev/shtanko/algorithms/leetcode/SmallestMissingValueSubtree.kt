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
import dev.shtanko.algorithms.annotations.level.Hard

/**
 * 2003. Smallest Missing Genetic Value in Each Subtree
 * @see <a href="https://leetcode.com/problems/smallest-missing-genetic-value-in-each-subtree/">Source</a>
 */
@Hard
fun interface SmallestMissingValueSubtree {
    operator fun invoke(parents: IntArray, nums: IntArray): IntArray
}

@DFS
class SmallestMissingValueSubtreeStrict : SmallestMissingValueSubtree {
    var m: HashMap<Int, List<Int>> = HashMap()
    lateinit var arr: IntArray
    var miss = 1
    var set: HashSet<Int> = HashSet()

    override operator fun invoke(parents: IntArray, nums: IntArray): IntArray {
        val n: Int = parents.size
        val res = IntArray(n)
        for (i in 0 until n) {
            res[i] = 1
        }

        var oneIndex = -1
        for (i in 0 until n) {
            if (nums[i] == 1) {
                oneIndex = i
                break
            }
        }

        // 1 not found
        if (oneIndex == -1) {
            return res
        }

        val graph: MutableMap<Int, MutableSet<Int>> = HashMap()
        for (i in 1 until n) {
            val children = graph.getOrDefault(parents[i], HashSet())
            children.add(i)
            graph[parents[i]] = children
        }

        val visited: MutableSet<Int> = HashSet()

        var parentIter = oneIndex
        var miss = 1
        while (parentIter >= 0) {
            dfs(parentIter, graph, visited, nums)
            while (visited.contains(miss)) {
                miss++
            }
            res[parentIter] = miss
            parentIter = parents[parentIter]
        }
        return res
    }

    private fun dfs(ind: Int, graph: Map<Int, Set<Int>>, visited: MutableSet<Int>, nums: IntArray) {
        if (!visited.contains(nums[ind])) {
            val children = graph[ind] ?: HashSet()
            for (p in children) {
                dfs(p, graph, visited, nums)
            }
            visited.add(nums[ind])
        }
    }
}
