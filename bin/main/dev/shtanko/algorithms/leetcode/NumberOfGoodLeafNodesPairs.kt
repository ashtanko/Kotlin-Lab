/*
 * Copyright 2024 Oleksii Shtanko
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
 * 1530. Number of Good Leaf Nodes Pairs
 * @see <a href="https://leetcode.com/problems/number-of-good-leaf-nodes-pairs">Source</a>
 */
fun interface NumberOfGoodLeafNodesPairs {
    operator fun invoke(root: TreeNode?, distance: Int): Int
}

class NumberOfGoodLeafNodesPairsBFS : NumberOfGoodLeafNodesPairs {
    override fun invoke(root: TreeNode?, distance: Int): Int {
        val graph = mutableMapOf<TreeNode, MutableList<TreeNode>>()
        val leafNodes = mutableSetOf<TreeNode>()

        traverseTree(root, null, graph, leafNodes)

        var ans = 0

        for (leaf in leafNodes) {
            val bfsQueue: Queue<TreeNode> = LinkedList()
            val seen = mutableSetOf<TreeNode>()
            bfsQueue.add(leaf)
            seen.add(leaf)
            // Go through all nodes that are within the given distance of
            // the current leaf node
            for (i in 0..distance) {
                // Clear all nodes in the queue (distance i away from leaf node)
                // Add the nodes' neighbors (distance i+1 away from leaf node)
                val size = bfsQueue.size
                for (j in 0 until size) {
                    // If current node is a new leaf node, add the found pair to our count
                    val currNode = bfsQueue.remove()
                    if (leafNodes.contains(currNode) && currNode != leaf) {
                        ans++
                    }
                    graph[currNode]?.let {
                        for (neighbor in it) {
                            if (!seen.contains(neighbor)) {
                                bfsQueue.add(neighbor)
                                seen.add(neighbor)
                            }
                        }
                    }
                }
            }
        }
        return ans / 2
    }

    private fun traverseTree(
        currNode: TreeNode?,
        prevNode: TreeNode?,
        graph: MutableMap<TreeNode, MutableList<TreeNode>>,
        leafNodes: MutableSet<TreeNode>,
    ) {
        if (currNode == null) {
            return
        }
        if (currNode.left == null && currNode.right == null) {
            leafNodes.add(currNode)
        }
        if (prevNode != null) {
            graph.computeIfAbsent(prevNode) { mutableListOf() }
            graph[prevNode]?.add(currNode)
            graph.computeIfAbsent(currNode) { mutableListOf() }
            graph[currNode]?.add(prevNode)
        }
        traverseTree(currNode.left, currNode, graph, leafNodes)
        traverseTree(currNode.right, currNode, graph, leafNodes)
    }
}

class NumberOfGoodLeafNodesPairsPostOrder : NumberOfGoodLeafNodesPairs {
    companion object {
        private const val MAX_DISTANCE_ARRAY_SIZE = 12
        private const val INDEX_FOR_TOTAL_PAIRS = 11
        private const val INITIAL_LEAF_NODE_DISTANCE = 0
    }

    override fun invoke(root: TreeNode?, distance: Int): Int {
        return postOrder(root, distance)[INDEX_FOR_TOTAL_PAIRS]
    }

    private fun postOrder(currentNode: TreeNode?, distance: Int): IntArray {
        if (currentNode == null) {
            return IntArray(MAX_DISTANCE_ARRAY_SIZE)
        } else if (currentNode.left == null && currentNode.right == null) {
            val current = IntArray(MAX_DISTANCE_ARRAY_SIZE)
            current[INITIAL_LEAF_NODE_DISTANCE] = 1
            return current
        }

        val left = postOrder(currentNode.left, distance)
        val right = postOrder(currentNode.right, distance)

        val current = IntArray(MAX_DISTANCE_ARRAY_SIZE)

        for (i in 0 until MAX_DISTANCE_ARRAY_SIZE - 2) {
            current[i + 1] += left[i] + right[i]
        }

        current[INDEX_FOR_TOTAL_PAIRS] += left[INDEX_FOR_TOTAL_PAIRS] + right[INDEX_FOR_TOTAL_PAIRS]

        for (d1 in 0..distance) {
            for (d2 in 0..distance) {
                if (2 + d1 + d2 <= distance) {
                    current[INDEX_FOR_TOTAL_PAIRS] += left[d1] * right[d2]
                }
            }
        }

        return current
    }
}
