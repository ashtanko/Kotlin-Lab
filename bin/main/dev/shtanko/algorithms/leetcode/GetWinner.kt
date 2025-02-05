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

import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

/**
 * 1535. Find the Winner of an Array Game
 * @see <a href="https://leetcode.com/problems/find-the-winner-of-an-array-game">Source</a>
 */
fun interface GetWinner {
    operator fun invoke(arr: IntArray, k: Int): Int
}

sealed interface GetWinnerStrategy {
    data object QueueSolution : GetWinner, GetWinnerStrategy {
        override fun invoke(arr: IntArray, k: Int): Int {
            var maxElement = arr[0]
            val queue: Queue<Int> = LinkedList()
            for (i in 1 until arr.size) {
                maxElement = max(maxElement, arr[i])
                queue.offer(arr[i])
            }

            var curr = arr[0]
            var winstreak = 0

            while (queue.isNotEmpty()) {
                val opponent: Int = queue.poll()
                if (curr > opponent) {
                    queue.offer(opponent)
                    winstreak++
                } else {
                    queue.offer(curr)
                    curr = opponent
                    winstreak = 1
                }
                if (winstreak == k || curr == maxElement) {
                    return curr
                }
            }

            return -1
        }
    }

    data object NoQueueSolution : GetWinner, GetWinnerStrategy {
        override fun invoke(arr: IntArray, k: Int): Int {
            var maxElement = arr[0]
            for (i in 1 until arr.size) {
                maxElement = max(maxElement, arr[i])
            }

            var curr = arr[0]
            var winstreak = 0

            for (i in 1 until arr.size) {
                val opponent = arr[i]
                if (curr > opponent) {
                    winstreak++
                } else {
                    curr = opponent
                    winstreak = 1
                }
                if (winstreak == k || curr == maxElement) {
                    return curr
                }
            }

            return -1
        }
    }
}
