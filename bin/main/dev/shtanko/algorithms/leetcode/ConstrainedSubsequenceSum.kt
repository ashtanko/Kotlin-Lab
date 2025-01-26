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

import java.util.Deque
import java.util.LinkedList
import kotlin.math.max

/**
 * 1425. Constrained Subsequence Sum
 * @see <a href="https://leetcode.com/problems/constrained-subsequence-sum/">Source</a>
 */
fun interface ConstrainedSubsequenceSum {
    operator fun invoke(nums: IntArray, k: Int): Int
}

class ConstrainedSubsequenceSumDeque : ConstrainedSubsequenceSum {
    override operator fun invoke(nums: IntArray, k: Int): Int {
        var res = nums.firstOrNull() ?: Int.MIN_VALUE
        val q: Deque<Int> = LinkedList()
        for (i in nums.indices) {
            nums[i] += if (q.isNotEmpty()) q.peek() else 0
            res = res.coerceAtLeast(nums[i])
            while (q.isNotEmpty() && nums[i] > q.peekLast()) q.pollLast()
            if (nums[i] > 0) q.offer(nums[i])
            if (i >= k && q.isNotEmpty() && q.peek() == nums[i - k]) q.poll()
        }
        return res
    }
}

class ConstrainedSubsequenceSumDP : ConstrainedSubsequenceSum {
    override operator fun invoke(nums: IntArray, k: Int): Int {
        val n: Int = nums.size
        val dp = IntArray(n)
        var ans = Int.MIN_VALUE
        for (i in 0 until n) {
            var max = 0
            for (j in max(i - k, 0) until i) {
                max = max(max, dp[j])
            }
            dp[i] = nums[i] + max
            ans = max(ans, dp[i])
        }
        return ans
    }
}

class ConstrainedSubsequenceSumQueue : ConstrainedSubsequenceSum {
    override operator fun invoke(nums: IntArray, k: Int): Int {
        val n: Int = nums.size
        val dp = IntArray(n)
        val deque: Deque<Int> = LinkedList()
        var ans = Int.MIN_VALUE
        for (i in 0 until n) {
            val max = max(0, if (deque.isEmpty()) 0 else dp[deque.peekFirst()])
            dp[i] = nums[i] + max
            ans = max(ans, dp[i])
            while (deque.isNotEmpty() && dp[i] >= dp[deque.peekLast()]) {
                deque.pollLast()
            }
            deque.addLast(i)
            if (i - deque.peekFirst() + 1 > k) {
                deque.removeFirst()
            }
        }
        return ans
    }
}

/**
 * Solution 3: DP + Decreasing Monotonic Queue + Optimized Space
 */
class ConstrainedSubsequenceSumQueueOpt : ConstrainedSubsequenceSum {
    override operator fun invoke(nums: IntArray, k: Int): Int {
        val n: Int = nums.size
        val deque: Deque<Int> = LinkedList()
        var ans = Int.MIN_VALUE
        for (i in 0 until n) {
            val max: Int = max(0, if (deque.isEmpty()) 0 else nums[deque.peekFirst()])
            nums[i] += max
            ans = max(ans, nums[i])
            while (deque.isNotEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast()
            }
            deque.addLast(i)
            if (i - deque.peekFirst() + 1 > k) {
                deque.removeFirst()
            }
        }
        return ans
    }
}
