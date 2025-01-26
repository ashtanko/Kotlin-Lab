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

fun interface JumpGame5Strategy {
    operator fun invoke(arr: IntArray): Int

    fun checkNeighbors(visited: MutableSet<Int>, node: Int, n: Int, nex: MutableList<Int>) {
        if (node + 1 < n && !visited.contains(node + 1)) {
            val value = node + 1
            visited.add(value)
            nex.add(value)
        }

        if (node - 1 >= 0 && !visited.contains(node - 1)) {
            val value = node - 1
            visited.add(value)
            nex.add(value)
        }
    }
}

class JP5BreadthFirstSearch : JumpGame5Strategy {
    override operator fun invoke(arr: IntArray): Int {
        val n: Int = arr.size
        if (n <= 1) {
            return 0
        }

        val graph: MutableMap<Int, MutableList<Int>> = HashMap()
        for (i in 0 until n) {
            graph.computeIfAbsent(arr[i]) {
                LinkedList()
            }.add(i)
        }

        var curs: MutableList<Int> = LinkedList() // store current layer
        curs.add(0)
        val visited: MutableSet<Int> = HashSet()
        var step = 0

        // when current layer exists
        while (curs.isNotEmpty()) {
            val nex: MutableList<Int> = LinkedList()

            // iterate the layer
            for (node in curs) {
                // check if reached end
                if (node == n - 1) {
                    return step
                }

                for (child in graph[arr[node]]!!) {
                    checkSameValue(visited, child, nex)
                }
                // clear the list to prevent redundant search
                graph[arr[node]]?.clear()

                checkNeighbors(visited, node, n, nex)
            }
            curs = nex
            step++
        }

        return -1
    }

    private fun checkSameValue(visited: MutableSet<Int>, child: Int, nex: MutableList<Int>) {
        if (!visited.contains(child)) {
            visited.add(child)
            nex.add(child)
        }
    }
}

class JP5BidirectionalBFS : JumpGame5Strategy {

    private var curs: MutableList<Int> = LinkedList() // store layers from start
    private val graph: MutableMap<Int, MutableList<Int>> = HashMap()
    private val visited: MutableSet<Int> = HashSet()
    var other: MutableList<Int> = LinkedList() // store layers from end

    init {
        curs.add(0)
        visited.add(0)
    }

    override operator fun invoke(arr: IntArray): Int {
        val n: Int = arr.size
        if (n <= 1) {
            return 0
        }
        for (i in 0 until n) {
            graph.computeIfAbsent(arr[i]) {
                LinkedList()
            }.add(i)
        }
        visited.add(n - 1)
        var step = 0
        other.add(n - 1)
        // when current layer exists
        while (curs.isNotEmpty()) {
            searchFewerNodes()
            val nex: MutableList<Int> = LinkedList()

            // iterate the layer
            for (node in curs) {
                // check same value
                for (child in graph[arr[node]]!!) {
                    if (other.contains(child)) {
                        return step + 1
                    }
                    checkNotVisited(child, nex)
                }

                // clear the list to prevent redundant search
                graph[arr[node]]?.clear()

                if (isOtherContains(other, node)) {
                    return step + 1
                }
                checkNeighbors(visited, node, n, nex)
            }
            curs = nex
            step++
        }

        return -1
    }

    // search from the side with fewer nodes
    private fun searchFewerNodes() {
        if (curs.size > other.size) {
            val tmp = curs
            curs = other
            other = tmp
        }
    }

    private fun checkNotVisited(child: Int, nex: MutableList<Int>) {
        if (!visited.contains(child)) {
            visited.add(child)
            nex.add(child)
        }
    }

    private fun isOtherContains(other: List<Int>, node: Int): Boolean {
        return other.contains(node + 1) || other.contains(node - 1)
    }
}

@Suppress("ComplexMethod")
class JP5BidirectionalBFS2 : JumpGame5Strategy {

    private var head: MutableSet<Int> = HashSet()
    private var tail: MutableSet<Int> = HashSet()
    private val idxMap: MutableMap<Int, MutableList<Int>> = HashMap()

    init {
        head.add(0)
    }

    override operator fun invoke(arr: IntArray): Int {
        val totalNums = arr.size
        if (totalNums <= 1) return 0

        for (idx in totalNums - 1 downTo 0) {
            idxMap.getOrPut(arr[idx]) { arrayListOf() }.add(idx)
        }

        val visited = getVisitedArray(totalNums)
        tail.add(totalNums - 1)
        if (totalNums > 1) {
            visited[totalNums - 1] = true
        }
        var steps = 0

        while (head.isNotEmpty() && tail.isNotEmpty()) {
            swapTwoSets()

            val next = HashSet<Int>()
            for (idx in head) {
                val hiNext = idx + 1
                if (tail.contains(hiNext)) return steps + 1
                if (hiNext in 0 until totalNums && !visited[hiNext]) {
                    next.add(hiNext)
                }

                val loNext = idx - 1
                if (tail.contains(loNext)) return steps + 1
                if (loNext in 0 until totalNums && !visited[loNext]) {
                    next.add(loNext)
                }

                idxMap[arr[idx]]?.let {
                    for (equalValueNext in it) {
                        if (tail.contains(equalValueNext)) return steps + 1

                        if (visited[equalValueNext]) continue
                        next.add(equalValueNext)
                        visited[equalValueNext] = true
                    }

                    // to save time
                    // there is no need to check visited/seen at all later on
                    it.clear()
                }
            }

            head = next
            ++steps
        }

        return -1
    }

    private fun swapTwoSets() {
        if (head.size > tail.size) {
            head = tail.also { tail = head }
        }
    }

    private fun getVisitedArray(totalNums: Int): BooleanArray {
        val visited = BooleanArray(totalNums) { false }
        if (totalNums != 0) {
            visited[0] = true
        }
        return visited
    }
}
