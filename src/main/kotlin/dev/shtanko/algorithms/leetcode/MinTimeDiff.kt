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
 * 539. Minimum Time Difference
 * @see <a href="https://leetcode.com/problems/minimum-time-difference/">Source</a>
 */
fun interface MinTimeDiff {
    operator fun invoke(timePoints: List<String>): Int
}

object MinTimeDiffSort : MinTimeDiff {

    private const val MINUTES_IN_DAY = 24 * 60
    private const val MINUTES_IN_HOUR = 60

    override fun invoke(timePoints: List<String>): Int {
        val minutesList = timePoints
            .map { time -> time.substring(0, 2).toInt() * MINUTES_IN_HOUR + time.substring(3).toInt() }
            .sorted()

        val extendedList = minutesList.toMutableList().apply {
            add(MINUTES_IN_DAY + minutesList[0])
        }

        return (1 until extendedList.size).minOf { i -> extendedList[i] - extendedList[i - 1] }
    }
}
