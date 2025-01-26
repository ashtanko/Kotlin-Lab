/*
 * Copyright 2022 Oleksii Shtanko
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

import kotlin.math.ln
import kotlin.math.max

/**
 * 740. Delete and Earn
 * @see <a href="https://leetcode.com/problems/delete-and-earn/">Source</a>
 */
fun interface DeleteAndEarn {
    operator fun invoke(nums: IntArray): Int
}

/**
 * Approach 1: Top-Down Dynamic Programming
 */
class DeleteAndEarnTopDown : DeleteAndEarn {

    private val points = HashMap<Int, Int>()
    private val cache = HashMap<Int, Int>()

    override operator fun invoke(nums: IntArray): Int {
        var maxNumber = 0

        // Precompute how many points we gain from taking an element
        for (num in nums) {
            points[num] = points.getOrDefault(num, 0) + num
            maxNumber = max(maxNumber, num)
        }

        return maxPoints(maxNumber)
    }

    private fun maxPoints(num: Int): Int {
        // Check for base cases
        if (num == 0) {
            return 0
        }
        if (num == 1) {
            return points.getOrDefault(1, 0)
        }
        if (cache.containsKey(num)) {
            return cache[num] ?: -1
        }

        // Apply recurrence relation
        val gain = points.getOrDefault(num, 0)
        cache[num] = max(maxPoints(num - 1), maxPoints(num - 2) + gain)
        return cache[num] ?: -1
    }
}

/**
 * Approach 2: Bottom-Up Dynamic Programming
 */
class DeleteAndEarnBottomUp : DeleteAndEarn {
    override operator fun invoke(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        val points = HashMap<Int, Int>()
        var maxNumber = 0

        // Precompute how many points we gain from taking an element
        for (num in nums) {
            points[num] = points.getOrDefault(num, 0) + num
            maxNumber = max(maxNumber, num)
        }

        // Declare our array along with base cases
        val maxPoints = IntArray(maxNumber + 1)
        maxPoints[1] = points.getOrDefault(1, 0)

        for (num in 2 until maxPoints.size) {
            // Apply recurrence relation
            val gain = points.getOrDefault(num, 0)
            maxPoints[num] = max(maxPoints[num - 1], maxPoints[num - 2] + gain)
        }

        return maxPoints[maxNumber]
    }
}

/**
 * Approach 3: Space Optimized Bottom-Up Dynamic Programming
 */
class DeleteAndEarnBottomUpOpt : DeleteAndEarn {
    override operator fun invoke(nums: IntArray): Int {
        var maxNumber = 0
        val points = HashMap<Int, Int>()

        // Precompute how many points we gain from taking an element
        for (num in nums) {
            points[num] = points.getOrDefault(num, 0) + num
            maxNumber = max(maxNumber, num)
        }

        // Base cases
        var twoBack = 0
        var oneBack = points.getOrDefault(1, 0)

        for (num in 2..maxNumber) {
            val temp = oneBack
            oneBack = max(oneBack, twoBack + points.getOrDefault(num, 0))
            twoBack = temp
        }

        return oneBack
    }
}

/**
 * Approach 4: Iterate Over Elements
 */
class DeleteAndEarnIterative : DeleteAndEarn {
    override operator fun invoke(nums: IntArray): Int {
        val points = HashMap<Int, Int>()

        // Precompute how many points we gain from taking an element
        for (num in nums) {
            points[num] = points.getOrDefault(num, 0) + num
        }

        val elements: List<Int> = ArrayList(points.keys).sorted()

        // Base cases
        var twoBack = 0
        var oneBack = points[elements.firstOrNull()] ?: 0

        for (i in 1 until elements.size) {
            val currentElement = elements[i]
            val temp = oneBack
            if (currentElement == elements[i - 1] + 1) {
                // The 2 elements are adjacent, cannot take both - apply normal recurrence
                oneBack = max(oneBack, twoBack + points.getOrDefault(currentElement, 0))
            } else {
                // Otherwise, we don't need to worry about adjacent deletions
                oneBack += points[currentElement] ?: 0
            }
            twoBack = temp
        }

        return oneBack
    }
}

class DeleteAndEarnBest : DeleteAndEarn {
    override operator fun invoke(nums: IntArray): Int {
        var maxNumber = 0
        val points = HashMap<Int, Int>()

        for (num in nums) {
            points[num] = points.getOrDefault(num, 0) + num
            maxNumber = max(maxNumber, num)
        }

        var twoBack = 0
        var oneBack: Int
        val n = points.size

        if (maxNumber < n + n * ln(n.toDouble()) / ln(2.0)) {
            oneBack = points.getOrDefault(1, 0)
            for (num in 2..maxNumber) {
                val temp = oneBack
                oneBack = max(oneBack, twoBack + points.getOrDefault(num, 0))
                twoBack = temp
            }
        } else {
            val elements: List<Int> = ArrayList(points.keys).sorted()
            oneBack = points[elements.firstOrNull()] ?: 0
            for (i in 1 until elements.size) {
                val currentElement = elements[i]
                val temp = oneBack
                if (currentElement == elements[i - 1] + 1) {
                    oneBack = max(oneBack, twoBack + points.getOrDefault(currentElement, 0))
                } else {
                    oneBack += points.getOrDefault(currentElement, 0)
                }
                twoBack = temp
            }
        }

        return oneBack
    }
}
