/*
 * Copyright 2020 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.Backtracking
import dev.shtanko.algorithms.annotations.DP
import dev.shtanko.algorithms.annotations.level.Medium
import dev.shtanko.algorithms.complexity.RuntimeComplexity
import dev.shtanko.algorithms.complexity.SpaceComplexity
import java.util.LinkedList

/**
 * All Paths From Source to Target.
 * @see <a href="https://leetcode.com/problems/all-paths-from-source-to-target/">Source</a>
 */
@Medium(link = "https://leetcode.com/problems/all-paths-from-source-to-target")
fun interface AllPathsSourceTarget {
    operator fun invoke(graph: Array<IntArray>): List<List<Int>>
}

/**
 * Approach 1: Backtracking.
 * Time Complexity: O(2^N*N)
 * Space Complexity: O(2^N*N)
 */
@SpaceComplexity(short = "O(2^N*N)")
@RuntimeComplexity("O(2^N*N)")
@Backtracking
class AllPathsSourceBacktracking : AllPathsSourceTarget {

    private var target = 0
    private lateinit var graph: Array<IntArray>
    private val results: MutableList<List<Int>> = ArrayList()

    override operator fun invoke(graph: Array<IntArray>): List<List<Int>> {
        this.graph = graph
        target = graph.size - 1
        this.graph = graph
        // adopt the LinkedList for fast access to the tail element.
        val path = LinkedList<Int>()
        path.addLast(0)
        // kick of the backtracking, starting from the source (node 0)
        this.backtrack(0, path)
        return results
    }

    private fun backtrack(currNode: Int, path: LinkedList<Int>) {
        if (currNode == target) {
            // Note: one should make a deep copy of the path
            results.add(ArrayList(path))
            return
        }
        if (graph.isEmpty()) return
        // explore the neighbor nodes one after another.
        for (nextNode in graph[currNode]) {
            // mark the choice, before backtracking.
            path.addLast(nextNode)
            this.backtrack(nextNode, path)
            // remove the previous choice, to try the next choice
            path.removeLast()
        }
    }
}

/**
 * Top-Down Dynamic Programming.
 */
@DP
class AllPathsSourceDP : AllPathsSourceTarget {

    private var target = 0
    private lateinit var graph: Array<IntArray>
    private val memo: HashMap<Int, List<List<Int>>> = HashMap()

    override operator fun invoke(graph: Array<IntArray>): List<List<Int>> {
        this.target = graph.size - 1
        this.graph = graph
        return allPathsToTarget(0)
    }

    private fun allPathsToTarget(currNode: Int): List<List<Int>> {
        // memoization. check the result in the cache first
        if (memo.containsKey(currNode)) return memo[currNode] ?: emptyList()
        val results: MutableList<List<Int>> = ArrayList()
        // base case
        if (currNode == target) {
            val path = ArrayList<Int>()
            path.add(target)
            results.add(path)
            return results
        }
        if (graph.isEmpty()) return emptyList()
        // iterate through the paths starting from each neighbor.
        for (nextNode in graph[currNode]) {
            for (path in allPathsToTarget(nextNode)) {
                val newPath = ArrayList<Int>()
                newPath.add(currNode)
                newPath.addAll(path)
                results.add(newPath)
            }
        }
        memo[currNode] = results
        return results
    }
}
