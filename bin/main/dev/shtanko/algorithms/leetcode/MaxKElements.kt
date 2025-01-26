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

import dev.shtanko.algorithms.annotations.level.Medium
import java.util.PriorityQueue
import kotlin.math.ceil

/**
 * 2530. Maximal Score After Applying K Operations
 * @see <a href="https://leetcode.com/problems/maximal-score-after-applying-k-operations/">Source</a>
 */
@Medium("https://leetcode.com/problems/maximal-score-after-applying-k-operations")
fun interface MaxKElements {
    operator fun invoke(nums: IntArray, k: Int): Long
}

class MaxKElementsPriorityQueue : MaxKElements {
    override fun invoke(nums: IntArray, k: Int): Long {
        var ans: Long = 0
        // Create a max-heap to store the elements
        val pq = PriorityQueue<Int>(compareByDescending { it })

        for (i in nums) {
            pq.add(i)
        }

        var remaining = k
        while (remaining-- > 0) {
            // Add the maxElement in ans and push its one-third value in the
            // priority queue
            val maxElement = pq.poll()
            ans += maxElement
            pq.add(ceil(maxElement / 3.0).toInt())
        }
        return ans
    }
}
