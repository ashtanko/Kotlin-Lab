/*
 * Copyright 2022 Oleksii Shtanko
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
import kotlin.math.min

/**
 * 2163. Minimum Difference in Sums After Removal of Elements
 * @see <a href="https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements/">Source</a>
 */
fun interface MinimumDifference {
    operator fun invoke(nums: IntArray): Long
}

class MinimumDifferencePriorityQueue : MinimumDifference {
    override operator fun invoke(nums: IntArray): Long {
        var s1: Long = 0
        var s2: Long = 0
        val dp = LongArray(nums.size)
        var r = Long.MAX_VALUE
        val n = nums.size / 3
        val q1: PriorityQueue<Int> = PriorityQueue(Comparator.reverseOrder())
        val q2: PriorityQueue<Int> = PriorityQueue()
        for (i in 0 until 2 * n) {
            q1.offer(nums[i])
            s1 += nums[i]
            if (q1.size > n) {
                s1 -= q1.poll()
            }
            dp[i] = s1
        }
        for (i in nums.size - 1 downTo n) {
            q2.offer(nums[i])
            s2 += nums[i]
            if (q2.size > n) {
                s2 -= q2.poll()
            }
            if (q2.size == n) {
                r = min(r, dp[i - 1] - s2)
            }
        }
        return r
    }
}
