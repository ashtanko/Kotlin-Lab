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

import java.util.PriorityQueue

/**
 * 264. Ugly Number II
 * @see <a href="https://leetcode.com/problems/ugly-number-ii/">Ugly Number II</a>
 */
fun interface UglyNumber2 {
    operator fun invoke(num: Int): Int
}

class UglyNumber2Dp : UglyNumber2 {
    override fun invoke(num: Int): Int {
        val primeFactors = listOf(2, 3, 5) // Factors for generating new ugly numbers

        return PriorityQueue<Long>().apply { add(1L) }.let { minHeap ->
            mutableSetOf<Long>().also { it.add(1L) }.let { seenNumbers ->
                var currentUgly: Long = 1L

                repeat(num) {
                    currentUgly = minHeap.poll() // Get the smallest number

                    // Generate and push the next ugly numbers
                    primeFactors.forEach { prime ->
                        (currentUgly * prime).also { nextUgly ->
                            if (seenNumbers.add(nextUgly)) { // Avoid duplicates
                                minHeap.offer(nextUgly)
                            }
                        }
                    }
                }

                currentUgly.toInt() // Return the nth ugly number
            }
        }
    }
}
