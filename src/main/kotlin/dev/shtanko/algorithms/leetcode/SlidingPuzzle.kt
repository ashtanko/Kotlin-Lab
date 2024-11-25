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

import dev.shtanko.algorithms.annotations.level.Hard
import java.util.LinkedList
import java.util.Queue

/**
 * 773. Sliding Puzzle
 * @see <a href="https://leetcode.com/problems/sliding-puzzle/">Sliding Puzzle</a>
 */
@Hard("https://leetcode.com/problems/sliding-puzzle/")
fun interface SlidingPuzzle {
    operator fun invoke(board: Array<IntArray>): Int
}

class SlidingPuzzleDFS : SlidingPuzzle {

    private val directions = listOf(
        listOf(1, 3),
        listOf(0, 2, 4),
        listOf(1, 5),
        listOf(0, 4),
        listOf(3, 5, 1),
        listOf(4, 2),
    )

    override fun invoke(board: Array<IntArray>): Int {
        fun swap(s: String, i: Int, j: Int): String {
            val chars = s.toCharArray()
            val temp = chars[i]
            chars[i] = chars[j]
            chars[j] = temp
            return String(chars)
        }

        // Convert the 2D board into a string representation to use as state
        val startState = board.toList().map(IntArray::toList).flatten().joinToString("")

        // Map to store the minimum moves for each visited state
        val visited = mutableMapOf<String, Int>()

        fun dfs(state: String, zeroPos: Int, moves: Int) {
            // Skip if this state has been visited with fewer or equal moves
            if (visited[state]?.let { it <= moves } == true) return
            visited[state] = moves

            // Try moving zero to each possible adjacent position
            for (nextPos in directions[zeroPos]) {
                val newState = swap(state, zeroPos, nextPos) // Swap to generate new state
                dfs(newState, nextPos, moves + 1) // Recursive DFS with updated state and move count
            }
        }

        // Start DFS traversal from the initial board state
        dfs(startState, startState.indexOf('0'), 0)

        // Return the minimum moves required to reach the target state, or -1 if unreachable
        return visited["123450"] ?: -1
    }
}

class SlidingPuzzleBFS : SlidingPuzzle {
    override fun invoke(board: Array<IntArray>): Int {
        val directions = listOf(
            listOf(1, 3),
            listOf(0, 2, 4),
            listOf(1, 5),
            listOf(0, 4),
            listOf(1, 3, 5),
            listOf(2, 4),
        )

        // Helper method to swap characters at indices i and j in the string
        fun swap(state: String, i: Int, j: Int): String {
            val chars = state.toCharArray()
            val temp = chars[i]
            chars[i] = chars[j]
            chars[j] = temp
            return String(chars)
        }

        val target = "123450"
        val startState = board.toList().map(IntArray::toList).flatten().joinToString("")

        val visited = mutableSetOf<String>() // To store visited states
        val queue: Queue<String> = LinkedList()
        queue.add(startState)
        visited.add(startState)

        var moves = 0

        // BFS to find the minimum number of moves
        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val currentState = queue.poll()

                // Check if we reached the target solved state
                if (currentState == target) {
                    return moves
                }

                val zeroPos = currentState.indexOf('0')
                for (newPos in directions[zeroPos]) {
                    val nextState = swap(currentState, zeroPos, newPos)

                    // Skip if this state has been visited
                    if (nextState in visited) continue

                    // Mark the new state as visited and add it to the queue
                    visited.add(nextState)
                    queue.add(nextState)
                }
            }
            moves++
        }

        return -1
    }
}
