/*
 * Copyright 2023 Oleksii Shtanko
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
 * 2244. Minimum Rounds to Complete All Tasks
 * @see <a href="https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/">Source</a>
 */
fun interface MinimumRounds {
    operator fun invoke(tasks: IntArray): Int
}

class MinimumRoundsSumUp : MinimumRounds {
    override operator fun invoke(tasks: IntArray): Int {
        val count = HashMap<Int, Int>()
        for (a in tasks) {
            count[a] = count.getOrDefault(a, 0) + 1
        }
        return count.values.takeUnless {
            it.contains(1)
        }?.sumOf {
            (it + 2) / 3
        } ?: -1
    }
}

class MinimumRoundsGreedy : MinimumRounds {
    override operator fun invoke(tasks: IntArray): Int {
        return tasks.groupBy {
            it
        }.map {
            if (it.value.size < 2) {
                return -1
            } else if (it.value.size == 2) {
                1
            } else {
                var totalNumberOfThrees = it.value.size / 3
                var numberLeft = it.value.size % 3
                while (numberLeft % 2 != 0 && totalNumberOfThrees > 0) {
                    totalNumberOfThrees--
                    numberLeft += 3
                }
                if (totalNumberOfThrees == 0 && numberLeft % 2 != 0) {
                    return -1
                } else {
                    totalNumberOfThrees + numberLeft / 2
                }
            }
        }.sum()
    }
}
