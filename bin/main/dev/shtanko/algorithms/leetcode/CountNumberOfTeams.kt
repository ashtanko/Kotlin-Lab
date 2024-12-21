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

/**
 * 1395. Count Number of Teams
 * @see <a href="https://leetcode.com/problems/count-number-of-teams">Source</a>
 */
fun interface CountNumberOfTeams {
    operator fun invoke(rating: IntArray): Int
}

class CountNumberOfTeamsSolution : CountNumberOfTeams {
    override fun invoke(rating: IntArray): Int {
        var teamCount = 0
        for (i in 1 until rating.size - 1) {
            val lessCounts = countLess(rating, i)
            val greaterCounts = countGreater(rating, i)
            teamCount += lessCounts[0] * greaterCounts[1] + greaterCounts[0] * lessCounts[1]
        }
        return teamCount
    }

    private fun countLess(rating: IntArray, index: Int): IntArray {
        val lessCounts = intArrayOf(0, 0)
        for (j in rating.indices) {
            if (rating[index] < rating[j]) {
                ++lessCounts[if (j > index) 1 else 0]
            }
        }
        return lessCounts
    }

    private fun countGreater(rating: IntArray, index: Int): IntArray {
        val greaterCounts = intArrayOf(0, 0)
        for (j in rating.indices) {
            if (rating[index] > rating[j]) {
                ++greaterCounts[if (j > index) 1 else 0]
            }
        }
        return greaterCounts
    }
}
