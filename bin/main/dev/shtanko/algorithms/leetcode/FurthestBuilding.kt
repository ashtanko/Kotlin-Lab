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

import java.util.PriorityQueue
import java.util.Queue
import kotlin.math.max
import kotlin.math.min

/**
 * Furthest Building You Can Reach
 * @see <a href="https://leetcode.com/problems/furthest-building-you-can-reach/">Source</a>
 */
fun interface FurthestBuilding {
    operator fun invoke(heights: IntArray, bricks: Int, ladders: Int): Int
}

/**
 * Approach 1: Min-Heap
 * Time complexity : O(N log N) or O(N log L).
 * Space complexity : O(N) or O(L).
 */
class FurthestBuildingMinHeap : FurthestBuilding {
    override operator fun invoke(heights: IntArray, bricks: Int, ladders: Int): Int {
        // Create a priority queue with a comparator that makes it behave as a min-heap.
        val ladderAllocations: Queue<Int> = PriorityQueue { a, b -> a - b }
        var remainingBricks = bricks
        for (i in 0 until heights.size - 1) {
            val climbHeight = heights[i + 1] - heights[i]
            // If this is actually a "jump down", skip it.
            if (climbHeight <= 0) {
                continue
            }
            // Otherwise, allocate a ladder for this climb.
            ladderAllocations.add(climbHeight)
            // If we haven't gone over the number of ladders, nothing else to do.
            if (ladderAllocations.size <= ladders) {
                continue
            }
            // Otherwise, we will need to take a climb out of ladderAllocations.
            remainingBricks -= ladderAllocations.remove()
            // If this caused bricks to go negative, we can't get to i + 1.
            if (remainingBricks < 0) {
                return i
            }
        }
        // If we got to here, this means we had enough materials to cover every climb.
        return heights.size - 1
    }
}

/**
 * Approach 2: Max-Heap
 * Time complexity : O(N log N).
 * Space complexity : O(N).
 */
class FurthestBuildingMaxHeap : FurthestBuilding {
    override operator fun invoke(heights: IntArray, bricks: Int, ladders: Int): Int {
        // Create a priority queue with a comparator that makes it behave as a max-heap.
        val brickAllocations: Queue<Int> = PriorityQueue { a: Int, b: Int -> b - a }
        var remainingBricks = bricks
        var remainingLadders = ladders
        for (i in 0 until heights.size - 1) {
            val climbHeight = heights[i + 1] - heights[i]
            // If this is actually a "jump down", skip it.
            if (climbHeight <= 0) {
                continue
            }
            // Otherwise, allocate a brick for this climb.
            brickAllocations.add(climbHeight)
            remainingBricks -= climbHeight
            // If we've used all the bricks, and have no ladders remaining, then
            // we can't go any further.
            if (remainingBricks < 0 && remainingLadders == 0) {
                return i
            }
            // Otherwise, if we've run out of bricks, we should replace the largest
            // brick allocation with a ladder.
            if (remainingBricks < 0) {
                remainingBricks += brickAllocations.remove()
                remainingLadders--
            }
        }
        // If we got to here, this means we had enough materials to cover every climb.
        return heights.size - 1
    }
}

/**
 * Approach 3: Binary Search for Final Reachable Building
 * Time complexity : O(N log² N).
 * Space complexity : O(N).
 */
class FinalReachableBuilding : FurthestBuilding {
    override operator fun invoke(heights: IntArray, bricks: Int, ladders: Int): Int {
        var lo = 0
        var hi = heights.size - 1
        while (lo < hi) {
            val mid = lo + (hi - lo + 1) / 2
            if (isReachable(mid, heights, bricks, ladders)) {
                lo = mid
            } else {
                hi = mid - 1
            }
        }
        // Note: both lo and hi are valid, but hi is preferred here to handle the case when lo = hi.
        return hi
    }

    private fun isReachable(buildingIndex: Int, heights: IntArray, bricks: Int, ladders: Int): Boolean {
        var remainingBricks = bricks
        var remainingLadders = ladders
        val climbs: MutableList<Int> = ArrayList()

        // Record climbs needed to reach buildingIndex.
        for (i in 0 until buildingIndex) {
            val h1 = heights[i]
            val h2 = heights[i + 1]
            if (h2 > h1) {
                climbs.add(h2 - h1)
            }
        }
        climbs.sort()

        // Check if climbs can be covered with available resources.
        for (climb in climbs) {
            when {
                climb <= remainingBricks -> {
                    remainingBricks -= climb
                }

                remainingLadders >= 1 -> {
                    remainingLadders -= 1
                }

                else -> {
                    return false
                }
            }
        }
        return true
    }
}

/**
 * Approach 4: Improved Binary Search for Final Reachable Building
 * Time complexity : O(N log N).
 * Space complexity : O(N).
 */
class ImprovedFinalReachableBuilding : FurthestBuilding {
    override operator fun invoke(heights: IntArray, bricks: Int, ladders: Int): Int {
        val sortedClimbs: MutableList<IntArray> = ArrayList()
        // Create a sorted list of climbs with their corresponding indices.
        for (i in 0 until heights.size - 1) {
            val climb = heights[i + 1] - heights[i]
            if (climb > 0) { // Only consider positive climbs
                sortedClimbs.add(intArrayOf(climb, i + 1))
            }
        }
        sortedClimbs.sortBy { it[0] } // Sort the climbs based on their height

        var lo = 0
        var hi = heights.size - 1
        // Perform binary search to find the farthest reachable building
        while (lo < hi) {
            val mid = lo + (hi - lo + 1) / 2
            if (isReachable(mid, sortedClimbs, bricks, ladders)) {
                lo = mid
            } else {
                hi = mid - 1
            }
        }
        // Note that both lo and hi are valid, but hi is preferred here to handle the case when lo = hi.
        return hi
    }

    private fun isReachable(buildingIndex: Int, climbs: List<IntArray>, bricks: Int, ladders: Int): Boolean {
        var remainingBricks = bricks
        var remainingLadders = ladders
        for (climbEntry in climbs) {
            val climb = climbEntry[0]
            val index = climbEntry[1]
            if (index > buildingIndex) {
                continue // Skip climbs beyond the buildingIndex
            }
            // Allocate bricks if enough remain; otherwise, allocate a ladder if at least one remains.
            when {
                climb <= remainingBricks -> remainingBricks -= climb
                remainingLadders >= 1 -> remainingLadders -= 1
                else -> return false
            }
        }
        return true
    }
}

/**
 * Approach 5: Binary Search on Threshold (Advanced)
 * Time complexity : O(N log(maxClimb)).
 * Space complexity : O(1).
 */
class BSThreshold : FurthestBuilding {
    override operator fun invoke(heights: IntArray, bricks: Int, ladders: Int): Int {
        var lo = Int.MAX_VALUE
        var hi = Int.MIN_VALUE

        // Find the minimum and maximum climbs
        for (i in 0 until heights.size - 1) {
            val climb = heights[i + 1] - heights[i]
            if (climb <= 0) {
                continue
            }
            lo = min(lo, climb)
            hi = max(hi, climb)
        }

        // If there are no positive climbs, return the last index
        if (lo == Int.MAX_VALUE) {
            return heights.size - 1
        }

        // Binary search for the threshold
        while (lo <= hi) {
            val mid = lo + (hi - lo) / 2
            val result = solveWithGivenThreshold(heights, bricks, ladders, mid)
            val indexReached = result[0]
            val laddersRemaining = result[1]
            val bricksRemaining = result[2]

            // If the top is reached, return the last index
            if (indexReached == heights.size - 1) {
                return heights.size - 1
            }

            // If there are ladders remaining, adjust the upper bound
            if (laddersRemaining > 0) {
                hi = mid - 1
                continue
            }

            // Check if the threshold is too low or just right
            val nextClimb = heights[indexReached + 1] - heights[indexReached]
            lo = if (nextClimb > bricksRemaining && mid > bricksRemaining) {
                return indexReached
            } else {
                mid + 1
            }
        }

        return -1
    }

    private fun solveWithGivenThreshold(heights: IntArray, bricks: Int, ladders: Int, k: Int): IntArray {
        var bricksCount = bricks
        var laddersCount = ladders
        var laddersUsedOnThreshold = 0

        // Iterate through climbs and allocate resources
        for (i in 0 until heights.size - 1) {
            val climb = heights[i + 1] - heights[i]
            if (climb <= 0) {
                continue
            }

            when {
                climb == k -> {
                    laddersUsedOnThreshold++
                    laddersCount--
                }

                climb > k -> {
                    laddersCount--
                }

                else -> {
                    bricksCount -= climb
                }
            }

            // Handle negative resources
            if (laddersCount < 0) {
                bricksCount -= if (laddersUsedOnThreshold >= 1) {
                    laddersUsedOnThreshold--
                    laddersCount++
                    k
                } else {
                    return intArrayOf(i, laddersCount, bricksCount)
                }
            }
            if (bricksCount < 0) {
                return intArrayOf(i, laddersCount, bricksCount)
            }
        }

        // Return the index reached, ladders remaining, and bricks remaining
        return intArrayOf(heights.size - 1, laddersCount, bricksCount)
    }
}
