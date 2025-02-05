/*
 * Copyright 2021 Oleksii Shtanko
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

/**
 * Find Median from Data Stream
 * @see <a href="https://leetcode.com/problems/find-median-from-data-stream/">Source</a>
 */
interface MedianFinder {
    fun addNum(num: Int)

    fun findMedian(): Double
}

sealed class MedianFinderStrategy {

    object SimpleSorting : MedianFinder, MedianFinderStrategy() {

        private val small: PriorityQueue<Int> = PriorityQueue(Collections.reverseOrder())
        private val large: PriorityQueue<Int> = PriorityQueue()
        private var even = true

        override fun addNum(num: Int) {
            if (even) {
                large.offer(num)
                small.offer(large.poll())
            } else {
                small.offer(num)
                large.offer(small.poll())
            }
            even = !even
        }

        override fun findMedian(): Double {
            return if (even) {
                (small.peek() + large.peek()) / 2.0
            } else {
                small.peek().toDouble()
            }
        }
    }
}
