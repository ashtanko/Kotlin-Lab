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

import dev.shtanko.algorithms.annotations.Sort
import java.util.Collections
import java.util.PriorityQueue
import kotlin.math.min

/**
 * 1509. Minimum Difference Between Largest and Smallest Value in Three Moves
 * @see <a href="https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves">
 *     Source</a>
 */
fun interface MinDiffInThreeMoves {
    operator fun invoke(nums: IntArray): Int
}

@Sort
class MinDiffInThreeMovesSort : MinDiffInThreeMoves {
    override fun invoke(nums: IntArray): Int {
        val numsSize: Int = nums.size
        if (numsSize <= 4) return 0
        nums.sort()
        var minDiff = Int.MAX_VALUE
        var left = 0
        var right = numsSize - 4
        while (left < 4) {
            minDiff = Math.min(minDiff, nums[right] - nums[left])
            left++
            right++
        }

        return minDiff
    }
}

class MinDiffInThreeMovesPartialSort : MinDiffInThreeMoves {
    override fun invoke(nums: IntArray): Int {
        val numsSize: Int = nums.size
        if (numsSize <= 4) {
            return 0
        }
        // Find the four smallest elements using a fixed-size max heap
        val maxHeap: PriorityQueue<Int> = PriorityQueue(
            Collections.reverseOrder(),
        )
        for (num in nums) {
            maxHeap.offer(num)
            if (maxHeap.size > 4) {
                maxHeap.poll()
            }
        }
        val smallestFour: MutableList<Int> = ArrayList(maxHeap)
        smallestFour.sort()

        // Find the four largest elements using a fixed-size min heap
        val minHeap: PriorityQueue<Int> = PriorityQueue()
        for (num in nums) {
            minHeap.offer(num)
            if (minHeap.size > 4) {
                minHeap.poll()
            }
        }
        val largestFour: MutableList<Int> = ArrayList<Int>(minHeap)
        largestFour.sort()

        var minDiff = Int.MAX_VALUE

        // Four scenarios to compute the minimum difference
        for (i in 0..3) {
            minDiff = min(
                minDiff.toDouble(),
                (largestFour[i] - smallestFour[i]).toDouble(),
            ).toInt()
        }

        return minDiff
    }
}
