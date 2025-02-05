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
 * 863. All Nodes Distance K in Binary Tree
 * @see <a href="https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/">Source</a>
 */
fun interface DistanceKTree {
    fun distanceK(root: TreeNode, target: TreeNode, k: Int): List<Int>
}

/**
 * Approach 1: Implementing Parent Pointers
 */
class DistanceKTreeParentPointers : DistanceKTree {

    override fun distanceK(root: TreeNode, target: TreeNode, k: Int): List<Int> {
        val res: MutableList<Int> = LinkedList()
        if (k == 0) {
            res.add(target.value)
        } else {
            dfs(res, root, target.value, k, 0)
        }
        return res
    }

    private fun dfs(res: MutableList<Int>, node: TreeNode?, target: Int, k: Int, depth: Int): Int {
        if (node == null) return 0
        if (depth == k) {
            res.add(node.value)
            return 0
        }
        val left: Int
        val right: Int
        if (node.value == target || depth > 0) {
            left = dfs(res, node.left, target, k, depth + 1)
            right = dfs(res, node.right, target, k, depth + 1)
        } else {
            left = dfs(res, node.left, target, k, depth)
            right = dfs(res, node.right, target, k, depth)
        }
        if (node.value == target) return 1
        if (left == k || right == k) {
            res.add(node.value)
            return 0
        }
        if (left > 0) {
            dfs(res, node.right, target, k, left + 1)
            return left + 1
        }
        if (right > 0) {
            dfs(res, node.left, target, k, right + 1)
            return right + 1
        }
        return 0
    }
}

/**
 * Approach 2: Depth-First Search on Equivalent Graph
 */
class DistanceKTreeDFS : DistanceKTree {
    private val graph: MutableMap<Int, MutableList<Int>> = HashMap()
    private val answer: MutableList<Int> = mutableListOf()
    private val visited: MutableSet<Int> = HashSet()

    override fun distanceK(root: TreeNode, target: TreeNode, k: Int): List<Int> {
        buildGraph(root, null)
        visited.add(target.value)
        dfs(target.value, 0, k)
        return answer
    }

    // Recursively build the undirected graph from the given binary tree.
    private fun buildGraph(cur: TreeNode?, parent: TreeNode?) {
        if (cur != null && parent != null) {
            graph.computeIfAbsent(cur.value) { ArrayList() }.add(parent.value)
            graph.computeIfAbsent(parent.value) { ArrayList() }.add(cur.value)
        }
        if (cur?.left != null) {
            buildGraph(cur.left, cur)
        }
        if (cur?.right != null) {
            buildGraph(cur.right, cur)
        }
    }

    private fun dfs(cur: Int, distance: Int, k: Int) {
        if (distance == k) {
            answer.add(cur)
            return
        }
        for (neighbor in graph.getOrDefault(cur, ArrayList())) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor)
                dfs(neighbor, distance + 1, k)
            }
        }
    }
}

/**
 * Approach 3: Breadth-First Search on Equivalent Graph
 */
class DistanceKTreeBFS : DistanceKTree {

    override fun distanceK(root: TreeNode, target: TreeNode, k: Int): List<Int> {
        val graph: MutableMap<Int, MutableList<Int>> = HashMap()
        dfsBuild(root, null, graph)
        val answer: MutableList<Int> = ArrayList()
        val visited: MutableSet<Int> = HashSet()
        val queue: Queue<IntArray> = LinkedList()

        // Add the target node to the queue with a distance of 0
        queue.add(intArrayOf(target.value, 0))
        visited.add(target.value)
        while (queue.isNotEmpty()) {
            val cur: IntArray = queue.poll()
            val node = cur[0]
            val distance = cur[1]

            // If the current node is at distance k from target,
            // add it to the answer list and continue to the next node.
            if (distance == k) {
                answer.add(node)
                continue
            }

            // Add all unvisited neighbors of the current node to the queue.
            for (neighbor in graph.getOrDefault(node, ArrayList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor)
                    queue.add(intArrayOf(neighbor, distance + 1))
                }
            }
        }
        return answer
    }

    // Recursively build the undirected graph from the given binary tree.
    private fun dfsBuild(cur: TreeNode?, parent: TreeNode?, graph: MutableMap<Int, MutableList<Int>>) {
        if (cur != null && parent != null) {
            val curVal: Int = cur.value
            val parentVal: Int = parent.value
            graph.putIfAbsent(curVal, ArrayList())
            graph.putIfAbsent(parentVal, ArrayList())
            graph[curVal]?.add(parentVal)
            graph[parentVal]?.add(curVal)
        }
        if (cur?.left != null) {
            dfsBuild(cur.left, cur, graph)
        }
        if (cur?.right != null) {
            dfsBuild(cur.right, cur, graph)
        }
    }
}
