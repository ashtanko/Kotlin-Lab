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

import java.util.LinkedList
import java.util.Stack

fun interface MergeIntervals {
    operator fun invoke(intervals: Array<IntArray>): Array<IntArray>
}

class MergeIntervalsConnectedComponents : MergeIntervals {

    private var graph: MutableMap<IntArray, MutableList<IntArray>> = HashMap()
    private var nodesInComp: MutableMap<Int, MutableList<IntArray>> = HashMap()
    private var visited: MutableSet<IntArray> = HashSet()

    override operator fun invoke(intervals: Array<IntArray>): Array<IntArray> {
        return merge(intervals)
    }

    // return whether two intervals overlap (inclusive)
    private fun overlap(a: IntArray, b: IntArray): Boolean {
        return a[0] <= b[1] && b[0] <= a[1]
    }

    // build a graph where an undirected edge between intervals u and v exists
    // iff u and v overlap.
    private fun buildGraph(intervals: Array<IntArray>) {
        for (interval in intervals) {
            graph[interval] = LinkedList<IntArray>()
        }
        for (interval1 in intervals) {
            for (interval2 in intervals) {
                if (overlap(interval1, interval2)) {
                    graph[interval1]?.add(interval2)
                    graph[interval2]?.add(interval1)
                }
            }
        }
    }

    // merges all of the nodes in this connected component into one interval.
    private fun mergeNodes(nodes: List<IntArray>): IntArray {
        var minStart = nodes[0][0]
        for (node in nodes) {
            minStart = minStart.coerceAtMost(node[0])
        }
        var maxEnd = nodes[0][1]
        for (node in nodes) {
            maxEnd = maxEnd.coerceAtLeast(node[1])
        }
        return intArrayOf(minStart, maxEnd)
    }

    // use depth-first search to mark all nodes in the same connected component
    // with the same integer.
    private fun markComponentDFS(start: IntArray, compNumber: Int) {
        val stack: Stack<IntArray> = Stack()
        stack.add(start)
        while (stack.isNotEmpty()) {
            val node: IntArray = stack.pop()
            if (!visited.contains(node)) {
                visited.add(node)
                if (nodesInComp[compNumber] == null) {
                    nodesInComp[compNumber] = LinkedList()
                }
                nodesInComp[compNumber]?.add(node)
                graph[node]?.let {
                    for (child in it) {
                        stack.add(child)
                    }
                }
            }
        }
    }

    // gets the connected components of the interval overlap graph.
    private fun buildComponents(intervals: Array<IntArray>) {
        var compNumber = 0
        for (interval in intervals) {
            if (!visited.contains(interval)) {
                markComponentDFS(interval, compNumber)
                compNumber++
            }
        }
    }

    private fun merge(intervals: Array<IntArray>): Array<IntArray> {
        buildGraph(intervals)
        buildComponents(intervals)

        // for each component, merge all intervals into one interval.
        val merged: MutableList<IntArray> = LinkedList()
        for (element in nodesInComp) {
            merged.add(mergeNodes(element.value))
        }
        return merged.toTypedArray()
    }
}

class MergeIntervalsSorting : MergeIntervals {

    override operator fun invoke(intervals: Array<IntArray>): Array<IntArray> {
        val i = intervals.toList().sortedWith(IntervalComparator())
        val merged = LinkedList<IntArray>()
        for (interval in i) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.last[1] < interval[0]) {
                merged.add(interval)
            } else {
                merged.last[1] = merged.last[1].coerceAtLeast(interval[1])
            }
        }

        return merged.toTypedArray()
    }

    private class IntervalComparator : Comparator<IntArray?> {
        override fun compare(a: IntArray?, b: IntArray?): Int {
            if (a == null || b == null) return -1
            return if (a[0] < b[0]) -1 else if (a[0] == b[0]) 0 else 1
        }
    }
}
