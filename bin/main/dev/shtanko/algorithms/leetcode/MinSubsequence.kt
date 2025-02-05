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

import java.util.Collections
import java.util.PriorityQueue

fun interface MinSubsequenceStrategy {
    operator fun invoke(arr: IntArray): List<Int>
}

/**
 * Counting sort since the values are in [1, 100]
 * O(n) time
 * O(n) space
 */
class MinSubsequenceCountingSort : MinSubsequenceStrategy {

    override operator fun invoke(arr: IntArray): List<Int> {
        val count = IntArray(ARR_SIZE)
        var totalSum = 0
        for (current in arr) {
            totalSum += current
            count[current]++
        }
        val currentSubsequence = mutableListOf<Int>()
        var currSum = 0
        var i = count.size - 1
        while (i >= 0) {
            while (count[i] > 0) {
                currentSubsequence.add(i)
                currSum += i
                count[i]--
                if (currSum > totalSum - currSum) {
                    i = -1
                    break
                }
            }
            --i
        }
        return currentSubsequence
    }

    companion object {
        private const val ARR_SIZE = 101
    }
}

class MinSubsequencePriorityQueue : MinSubsequenceStrategy {
    override operator fun invoke(arr: IntArray): List<Int> {
        return if (arr.isEmpty()) return listOf() else arr.solve()
    }

    private fun IntArray.solve(): List<Int> {
        val res = mutableListOf<Int>()

        val pq = PriorityQueue<Int>(Collections.reverseOrder())
        var subSum = 0
        var totalSum = 0
        for (num in this) {
            pq.offer(num)
            totalSum += num
        }
        while (subSum <= totalSum / 2) {
            res.add(pq.peek())
            subSum += pq.poll()
        }
        return res
    }
}
