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

import java.util.Stack

/**
 * 1203. Sort Items by Groups Respecting Dependencies
 * @see <a href="https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies">Source</a>
 */
fun interface SortItems {
    operator fun invoke(n: Int, m: Int, group: IntArray, beforeItems: List<List<Int>>): IntArray
}

class TopologicalSorting : SortItems {
    override operator fun invoke(n: Int, m: Int, group: IntArray, beforeItems: List<List<Int>>): IntArray {
        // If an item belongs to zero group, assign it a unique group id.
        var groupId = m
        for (i in 0 until n) {
            if (group[i] == -1) {
                group[i] = groupId
                groupId++
            }
        }

        // Sort all item regardless of group dependencies.
        val itemGraph: MutableMap<Int, MutableList<Int>> = HashMap()
        val itemIndegree = IntArray(n)
        for (i in 0 until n) {
            itemGraph[i] = ArrayList()
        }

        // Sort all groups regardless of item dependencies.
        val groupGraph: MutableMap<Int, MutableList<Int>> = HashMap()
        val groupIndegree = IntArray(groupId)
        for (i in 0 until groupId) {
            groupGraph[i] = ArrayList()
        }

        for (curr in 0 until n) {
            for (prev in beforeItems[curr]) {
                // Each (prev -> curr) represents an edge in the item graph.
                itemGraph[prev]?.add(curr)
                itemIndegree[curr]++

                // If they belong to different groups, add an edge in the group graph.
                if (group[curr] != group[prev]) {
                    groupGraph[group[prev]]!!.add(group[curr])
                    groupIndegree[group[curr]]++
                }
            }
        }

        // Topological sort nodes in the graph, return an empty array if a cycle exists.
        val itemOrder = topologicalSort(itemGraph, itemIndegree)
        val groupOrder = topologicalSort(groupGraph, groupIndegree)

        if (itemOrder.isEmpty() || groupOrder.isEmpty()) {
            return IntArray(0)
        }

        // Items are sorted regardless of groups, we need to differentiate them by the groups they belong to.
        val orderedGroups: MutableMap<Int, MutableList<Int>> = HashMap()
        for (item in itemOrder) {
            orderedGroups.computeIfAbsent(
                group[item],
            ) { ArrayList() }.add(item)
        }

        // Concatenate sorted items in all sorted groups.
        // [group 1, group 2, ... ] -> [(item 1, item 2, ...), (item 1, item 2, ...), ...]
        val answerList: MutableList<Int> = ArrayList()
        for (groupIndex in groupOrder) {
            answerList.addAll(orderedGroups.getOrDefault(groupIndex, ArrayList()))
        }

        return answerList.stream().mapToInt { obj: Int -> obj }.toArray()
    }

    private fun topologicalSort(graph: Map<Int, List<Int>>, indegree: IntArray): List<Int> {
        val visited: MutableList<Int> = ArrayList()
        val stack: Stack<Int> = Stack()
        for (key in graph.keys) {
            if (indegree[key] == 0) {
                stack.add(key)
            }
        }
        while (stack.isNotEmpty()) {
            val curr: Int = stack.pop()
            visited.add(curr)
            for (prev in graph[curr]!!) {
                indegree[prev]--
                if (indegree[prev] == 0) {
                    stack.add(prev)
                }
            }
        }
        return if (visited.size == graph.size) visited else ArrayList()
    }
}
