/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Hard

/**
 * 2463. Minimum Total Distance Traveled
 * @see <a href="https://leetcode.com/problems/minimum-total-distance-traveled/">Source</a>
 */
@Hard("https://leetcode.com/problems/minimum-total-distance-traveled")
fun interface MinTotalDistanceTraveled {
    operator fun invoke(robots: List<Int>, factories: Array<IntArray>): Long
}

class MinTotalDistanceTraveledTabulation : MinTotalDistanceTraveled {
    override fun invoke(
        robots: List<Int>,
        factories: Array<IntArray>,
    ): Long {
        val sortedRobots = robots.sorted()
        factories.sortBy { it[0] }

        // Flatten factory positions according to their capacities
        val factoryPositions = mutableListOf<Int>()
        for (factory in factories) {
            repeat(factory[1]) {
                factoryPositions.add(factory[0])
            }
        }

        val robotCount = sortedRobots.size
        val factoryCount = factoryPositions.size
        var next = LongArray(factoryCount + 1)
        var current = LongArray(factoryCount + 1)

        // Fill DP table using two rows for optimization
        for (i in robotCount - 1 downTo 0) {
            // No factories left case
            if (i != robotCount - 1) next[factoryCount] = 1e12.toLong()
            // Initialize current row
            current[factoryCount] = 1e12.toLong()

            for (j in factoryCount - 1 downTo 0) {
                // Assign current robot to current factory
                val assign = kotlin.math.abs(sortedRobots[i].toLong() - factoryPositions[j]) + next[j + 1]
                // Skip current factory for this robot
                val skip = current[j + 1]
                // Take the minimum option
                current[j] = kotlin.math.min(assign, skip)
            }
            // Move to next robot
            next = current.copyOf()
        }

        // Return minimum distance starting from the first robot
        return current[0]
    }
}
