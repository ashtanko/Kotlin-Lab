/*
 * Copyright 2021 Oleksii Shtanko
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

import dev.shtanko.algorithms.leetcode.MinimumKnightMoves.Companion.offsets
import java.util.Deque
import java.util.LinkedList
import kotlin.math.abs
import kotlin.math.min

/**
 * Minimum Knight Moves
 * @see <a href="https://leetcode.com/problems/minimum-knight-moves">Source</a>
 */
fun interface MinimumKnightMoves {
    operator fun invoke(x: Int, y: Int): Int

    companion object {
        val offsets = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 1),
            intArrayOf(2, -1),
            intArrayOf(1, -2),
            intArrayOf(-1, -2),
            intArrayOf(-2, -1),
            intArrayOf(-2, 1),
            intArrayOf(-1, 2),
        )
    }
}

/**
 * Approach 1: BFS (Breadth-First Search)
 */
class MinimumKnightMovesBFS : MinimumKnightMoves {
    override operator fun invoke(x: Int, y: Int): Int {
        // - Rather than using the inefficient HashSet, we use the bitmap
        //     otherwise we would run out of time for the test cases.
        // - We create a bitmap that is sufficient to cover all the possible
        //     inputs, according to the description of the problem.
        val visited = Array(BITMAP_SIZE) { BooleanArray(BITMAP_SIZE) }

        val queue: Deque<IntArray> = LinkedList()
        queue.addLast(intArrayOf(0, 0))
        var steps = 0

        while (queue.isNotEmpty()) {
            val currLevelSize = queue.size
            // iterate through the current level
            for (i in 0 until currLevelSize) {
                val curr = queue.removeFirst()
                if (curr[0] == x && curr[1] == y) {
                    return steps
                }
                for (offset in offsets) {
                    val next = intArrayOf(curr[0] + offset[0], curr[1] + offset[1])
                    // align the coordinate to the bitmap
                    if (!visited[next[0] + BITMAP_MID][next[1] + BITMAP_MID]) {
                        visited[next[0] + BITMAP_MID][next[1] + BITMAP_MID] = true
                        queue.addLast(next)
                    }
                }
            }
            steps++
        }
        // move on to the next level
        return steps
    }

    companion object {
        private const val BITMAP_SIZE = 605
        private const val BITMAP_MID = 302
    }
}

/**
 * Approach 2: Bidirectional BFS
 */
class MinimumKnightMovesBidirectional : MinimumKnightMoves {
    override operator fun invoke(x: Int, y: Int): Int {
        // data structures needed to move from the origin point
        val originQueue: Deque<IntArray> = LinkedList()
        originQueue.addLast(intArrayOf(0, 0, 0))
        val originDistance: MutableMap<String, Int> = HashMap()
        originDistance["0,0"] = 0

        // data structures needed to move from the target point
        val targetQueue: Deque<IntArray> = LinkedList()
        targetQueue.addLast(intArrayOf(x, y, 0))
        val targetDistance: MutableMap<String, Int> = HashMap()
        targetDistance["$x,$y"] = 0

        while (true) {
            // check if we reach the circle of target
            val origin = originQueue.removeFirst()
            val originXY = origin[0].toString() + "," + origin[1]
            if (targetDistance.containsKey(originXY)) {
                return origin[2] + targetDistance[originXY]!!
            }

            // check if we reach the circle of origin
            val target = targetQueue.removeFirst()
            val targetXY = target[0].toString() + "," + target[1]
            if (originDistance.containsKey(targetXY)) {
                return target[2] + originDistance[targetXY]!!
            }
            for (offset in offsets) {
                // expand the circle of origin
                val nextOrigin = intArrayOf(origin[0] + offset[0], origin[1] + offset[1])
                val nextOriginXY = nextOrigin[0].toString() + "," + nextOrigin[1]
                if (!originDistance.containsKey(nextOriginXY)) {
                    originQueue.addLast(intArrayOf(nextOrigin[0], nextOrigin[1], origin[2] + 1))
                    originDistance[nextOriginXY] = origin[2] + 1
                }

                // expand the circle of target
                val nextTarget = intArrayOf(target[0] + offset[0], target[1] + offset[1])
                val nextTargetXY = nextTarget[0].toString() + "," + nextTarget[1]
                if (!targetDistance.containsKey(nextTargetXY)) {
                    targetQueue.addLast(intArrayOf(nextTarget[0], nextTarget[1], target[2] + 1))
                    targetDistance[nextTargetXY] = target[2] + 1
                }
            }
        }
    }
}

/**
 * Approach 3: DFS (Depth-First Search) with Memoization
 */
class MinimumKnightMovesMemoization : MinimumKnightMoves {

    private val memo: MutableMap<String, Int> = HashMap()

    private fun dfs(x: Int, y: Int): Int {
        val key = "$x,$y"
        if (memo.containsKey(key)) {
            return memo[key]!!
        }
        return when {
            x + y == 0 -> {
                0
            }

            x + y == 2 -> {
                2
            }

            else -> {
                val ret = min(
                    dfs(abs(x - 1), abs(y - 2)),
                    dfs(abs(x - 2), abs(y - 1)),
                ) + 1
                memo[key] = ret
                ret
            }
        }
    }

    override operator fun invoke(x: Int, y: Int): Int {
        return dfs(abs(x), abs(y))
    }
}
