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

/**
 * 2022. Find Missing Observations
 * @see <a href="https://leetcode.com/problems/find-missing-observations/">Find Missing Observations</a>
 */
fun interface FindMissingObservations {
    operator fun invoke(rolls: IntArray, mean: Int, num: Int): IntArray
}

data object FindMissingObservationsMath : FindMissingObservations {
    override fun invoke(rolls: IntArray, mean: Int, num: Int): IntArray {
        val existingSum = rolls.sum()

        // Calculate the total sum required to achieve the target mean.
        val totalSum = mean * (rolls.size + num)

        // Calculate the missing sum.
        val missingSum = totalSum - existingSum

        // Check if the missing sum is valid (within the possible range of dice rolls).
        if (missingSum > 6 * num || missingSum < num) {
            return intArrayOf()
        }

        // Calculate the average value of each missing roll.
        val averageMissingRoll = missingSum / num

        // Distribute the remaining sum (if any) among the missing rolls.
        val remainingSum = missingSum % num
        val missingRolls = IntArray(num) { averageMissingRoll }
        for (i in 0 until remainingSum) {
            missingRolls[i]++
        }

        return missingRolls
    }
}
