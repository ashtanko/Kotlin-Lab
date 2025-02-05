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
 * 506. Relative Ranks
 * @see <a href="https://leetcode.com/problems/relative-ranks/">Source</a>
 */
fun interface RelativeRanks {
    operator fun invoke(score: IntArray): Array<String>
}

class RelativeRanksReverse : RelativeRanks {
    override fun invoke(score: IntArray): Array<String> {
        val numAthletes = score.size
        val scoresCopy = score.copyOf() // Creates a copy of the original array

        // Mapping each score to its original index
        val scoreToIndexMap = mutableMapOf<Int, Int>()
        for (i in 0 until numAthletes) {
            scoreToIndexMap[scoresCopy[i]] = i
        }

        // Sort the copied array in ascending order
        scoresCopy.sort()

        // Assigning ranks to athletes based on the sorted scores
        val ranks = arrayOfNulls<String>(numAthletes)
        for (i in 0 until numAthletes) {
            val originalIndex = scoreToIndexMap.getOrDefault(scoresCopy[numAthletes - i - 1], -1)
            val rank = when (i) {
                0 -> "Gold Medal"
                1 -> "Silver Medal"
                2 -> "Bronze Medal"
                else -> (i + 1).toString()
            }
            ranks[originalIndex] = rank
        }

        return ranks.filterNotNull().toTypedArray()
    }
}
