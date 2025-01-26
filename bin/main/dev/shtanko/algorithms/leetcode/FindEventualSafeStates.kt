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
 * 802. Find Eventual Safe States
 * @see <a href="https://leetcode.com/problems/find-eventual-safe-states/">Source</a>
 */
fun interface FindEventualSafeStates {
    fun eventualSafeNodes(graph: Array<IntArray>): List<Int>
}

/**
 * Approach 1: Topological Sort Using Kahn's Algorithm
 */
class FindEventualSafeStatesKahn : FindEventualSafeStates {
    override fun eventualSafeNodes(graph: Array<IntArray>): List<Int> {
        val n = graph.size
        val indegree = IntArray(n)
        val adj: MutableList<MutableList<Int>> = ArrayList()
        for (i in 0 until n) {
            adj.add(ArrayList())
        }
        for (i in 0 until n) {
            for (node in graph[i]) {
                adj[node].add(i)
                indegree[i]++
            }
        }
        val q: Queue<Int> = LinkedList()
        // Push all the nodes with indegree zero in the queue.
        for (i in 0 until n) {
            if (indegree[i] == 0) {
                q.add(i)
            }
        }
        val safe = BooleanArray(n)
        while (q.isNotEmpty()) {
            val node: Int = q.poll()
            safe[node] = true
            for (neighbor in adj[node]) {
                // Delete the edge "node -> neighbor".
                indegree[neighbor]--
                if (indegree[neighbor] == 0) {
                    q.add(neighbor)
                }
            }
        }
        val safeNodes: MutableList<Int> = ArrayList()
        for (i in 0 until n) {
            if (safe[i]) {
                safeNodes.add(i)
            }
        }
        return safeNodes
    }
}

/**
 * Approach 2: Depth First Search
 */
class FindEventualSafeStatesDFS : FindEventualSafeStates {
    override fun eventualSafeNodes(graph: Array<IntArray>): List<Int> {
        val n = graph.size
        val adj: MutableList<MutableList<Int>> = ArrayList()
        for (i in 0 until n) {
            adj.add(ArrayList())
            for (node in graph[i]) {
                adj[i].add(node)
            }
        }
        val visit = BooleanArray(n)
        val inStack = BooleanArray(n)
        for (i in 0 until n) {
            safeStatesDFS(i, adj, visit, inStack)
        }
        val safeNodes: MutableList<Int> = ArrayList()
        for (i in 0 until n) {
            if (!inStack[i]) {
                safeNodes.add(i)
            }
        }
        return safeNodes
    }
}

fun safeStatesDFS(node: Int, adj: List<MutableList<Int>>, visit: BooleanArray, inStack: BooleanArray): Boolean {
    // If the node is already in the stack, we have a cycle.
    if (inStack[node]) {
        return true
    }
    if (visit[node]) {
        return false
    }
    // Mark the current node as visited and part of current recursion stack.
    visit[node] = true
    inStack[node] = true
    for (neighbor in adj[node]) {
        if (safeStatesDFS(neighbor, adj, visit, inStack)) {
            return true
        }
    }
    // Remove the node from the stack.
    inStack[node] = false
    return false
}
