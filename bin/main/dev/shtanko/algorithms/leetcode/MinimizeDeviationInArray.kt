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

import java.util.Collections
import java.util.PriorityQueue
import kotlin.math.min

/**
 * 1675. Minimize Deviation in Array
 * @see <a href="https://leetcode.com/problems/minimize-deviation-in-array/">Source</a>
 */
fun interface MinimizeDeviationInArray {
    fun minimumDeviation(nums: IntArray): Int
}

class MinimizeDeviationInArrayQueue : MinimizeDeviationInArray {
    override fun minimumDeviation(nums: IntArray): Int {
        val pq: PriorityQueue<Int> = PriorityQueue(Collections.reverseOrder())
        var minVal = Int.MAX_VALUE
        for (num in nums) {
            var n = num
            if (n % 2 == 1) {
                n *= 2
            }
            pq.offer(n)
            minVal = min(minVal, n)
        }
        var minDeviation = Int.MAX_VALUE
        while (true) {
            var maxVal: Int = pq.poll()
            minDeviation = min(minDeviation, maxVal - minVal)
            if (maxVal % 2 == 1) break
            maxVal /= 2
            minVal = min(minVal, maxVal)
            pq.offer(maxVal)
        }
        return minDeviation
    }
}
