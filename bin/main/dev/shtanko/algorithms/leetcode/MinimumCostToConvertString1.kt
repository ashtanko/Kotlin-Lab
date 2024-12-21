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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import dev.shtanko.algorithms.annotations.Dijkstra
import dev.shtanko.algorithms.annotations.FloydWarshall
import java.util.PriorityQueue

/**
 * 2976. Minimum Cost to Convert String I
 * @see <a href="https://leetcode.com/problems/minimum-cost-to-convert-string-i">Source</a>
 */
fun interface MinimumCostToConvertString1 {
    operator fun invoke(source: String, target: String, original: CharArray, changed: CharArray, cost: IntArray): Long
}

/**
 * Approach 1: Dijkstra's Algorithm
 */
@Dijkstra
class MinimumCostDijkstra : MinimumCostToConvertString1 {
    override fun invoke(
        source: String,
        target: String,
        original: CharArray,
        changed: CharArray,
        cost: IntArray,
    ): Long {
        // Create a graph representation of character conversions
        val adjacencyList = Array(ALPHABET_LETTERS_COUNT) { mutableListOf<Pair<Int, Int>>() }

        // Populate the adjacency list with character conversions
        val conversionCount = original.size
        for (i in 0 until conversionCount) {
            adjacencyList[original[i] - 'a'].add(Pair(changed[i] - 'a', cost[i]))
        }

        // Calculate the shortest paths for all possible character conversions
        val minConversionCosts = Array(ALPHABET_LETTERS_COUNT) { LongArray(ALPHABET_LETTERS_COUNT) }
        for (i in 0 until ALPHABET_LETTERS_COUNT) {
            minConversionCosts[i] = dijkstra(i, adjacencyList)
        }

        // Calculate the total cost of converting source to target
        var totalCost: Long = 0
        val stringLength = source.length
        for (i in 0 until stringLength) {
            if (source[i] != target[i]) {
                val charConversionCost = minConversionCosts[source[i] - 'a'][target[i] - 'a']
                if (charConversionCost == -1L) {
                    return -1 // Conversion not possible
                }
                totalCost += charConversionCost
            }
        }
        return totalCost
    }

    // Find minimum conversion costs from a starting character to all other characters
    private fun dijkstra(startChar: Int, adjacencyList: Array<MutableList<Pair<Int, Int>>>): LongArray {
        // Priority queue to store characters with their conversion cost, sorted by cost
        val priorityQueue = PriorityQueue<Pair<Long, Int>>(compareBy { it.first })

        // Initialize the starting character with cost 0
        priorityQueue.add(Pair(0L, startChar))

        // Array to store the minimum conversion cost to each character
        val minCosts = LongArray(ALPHABET_LETTERS_COUNT) { -1L }

        while (priorityQueue.isNotEmpty()) {
            val (currentCost, currentChar) = priorityQueue.poll()

            if (minCosts[currentChar] != -1L && minCosts[currentChar] < currentCost) continue

            // Explore all possible conversions from the current character
            for ((targetChar, conversionCost) in adjacencyList[currentChar]) {
                val newTotalCost = currentCost + conversionCost

                // If we found a cheaper conversion, update its cost
                if (minCosts[targetChar] == -1L || newTotalCost < minCosts[targetChar]) {
                    minCosts[targetChar] = newTotalCost
                    // Add the updated conversion to the queue for further exploration
                    priorityQueue.add(Pair(newTotalCost, targetChar))
                }
            }
        }
        // Return the array of minimum conversion costs from the starting character to all others
        return minCosts
    }
}

/**
 * Approach 2: Floyd-Warshall Algorithm
 */
@FloydWarshall
class MinimumCostFloydWarshall : MinimumCostToConvertString1 {
    override fun invoke(
        source: String,
        target: String,
        original: CharArray,
        changed: CharArray,
        cost: IntArray,
    ): Long {
        // Initialize result to store the total minimum cost
        var totalCost: Long = 0

        // Initialize a 2D array to store the minimum conversion cost
        // between any two characters
        val minCost = Array(ALPHABET_LETTERS_COUNT) { LongArray(ALPHABET_LETTERS_COUNT) { Int.MAX_VALUE.toLong() } }

        // Fill the initial conversion costs from the given original,
        // changed, and cost arrays
        for (i in original.indices) {
            val startChar = original[i] - 'a'
            val endChar = changed[i] - 'a'
            minCost[startChar][endChar] = minOf(minCost[startChar][endChar], cost[i].toLong())
        }

        // Use Floyd-Warshall algorithm to find the shortest path between any
        // two characters
        for (k in 0 until ALPHABET_LETTERS_COUNT) {
            for (i in 0 until ALPHABET_LETTERS_COUNT) {
                for (j in 0 until ALPHABET_LETTERS_COUNT) {
                    if (minCost[i][k] < Int.MAX_VALUE && minCost[k][j] < Int.MAX_VALUE) {
                        minCost[i][j] = minOf(minCost[i][j], minCost[i][k] + minCost[k][j])
                    }
                }
            }
        }

        // Calculate the total minimum cost to transform the source string to
        // the target string
        for (i in source.indices) {
            if (source[i] == target[i]) continue

            val sourceChar = source[i] - 'a'
            val targetChar = target[i] - 'a'

            // If the transformation is not possible, return -1
            if (minCost[sourceChar][targetChar] >= Int.MAX_VALUE) {
                return -1
            }
            totalCost += minCost[sourceChar][targetChar]
        }

        return totalCost
    }
}
