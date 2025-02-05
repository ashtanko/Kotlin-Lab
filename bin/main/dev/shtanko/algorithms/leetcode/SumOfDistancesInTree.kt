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

/**
 * 834. Sum of Distances in Tree
 * @see <a href="https://leetcode.com/problems/sum-of-distances-in-tree">Source</a>
 */
class SumOfDistancesInTree {

    private lateinit var distanceSum: IntArray
    private lateinit var nodeCount: IntArray
    private var adjacencyList: MutableList<MutableSet<Int>> = ArrayList()
    private var totalNodes = 0

    operator fun invoke(nodeCount: Int, edges: Array<IntArray>): IntArray {
        this.totalNodes = nodeCount
        distanceSum = IntArray(nodeCount)
        this.nodeCount = IntArray(nodeCount) { 1 }

        for (i in 0 until nodeCount) {
            adjacencyList.add(HashSet())
        }
        for (edge in edges) {
            adjacencyList[edge[0]].add(edge[1])
            adjacencyList[edge[1]].add(edge[0])
        }
        calculateNodeCountAndDistanceSum(0, -1)
        calculateDistanceSumForAllNodes(0, -1)
        return distanceSum
    }

    private fun calculateNodeCountAndDistanceSum(node: Int, parent: Int) {
        for (child in adjacencyList[node]) if (child != parent) {
            calculateNodeCountAndDistanceSum(child, node)
            nodeCount[node] += nodeCount[child]
            distanceSum[node] += distanceSum[child] + nodeCount[child]
        }
    }

    private fun calculateDistanceSumForAllNodes(node: Int, parent: Int) {
        for (child in adjacencyList[node]) if (child != parent) {
            distanceSum[child] = distanceSum[node] - nodeCount[child] + totalNodes - nodeCount[child]
            calculateDistanceSumForAllNodes(child, node)
        }
    }
}
