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

import java.util.PriorityQueue
import kotlin.math.max

/**
 * 2542. Maximum Subsequence Score
 * @see <a href="https://leetcode.com/problems/maximum-subsequence-score/">Source</a>
 */
fun interface MaximumSubsequenceScore {
    fun maxScore(nums1: IntArray, nums2: IntArray, k: Int): Long
}

/**
 * Approach: Priority Queue
 */
class MaximumSubsequenceScorePQ : MaximumSubsequenceScore {
    override fun maxScore(nums1: IntArray, nums2: IntArray, k: Int): Long {
        // Sort pair (nums1[i], nums2[i]) by nums2[i] in decreasing order.
        val n: Int = nums1.size
        val pairs = Array(n) { IntArray(2) }
        for (i in 0 until n) {
            pairs[i] = intArrayOf(nums1[i], nums2[i])
        }
        pairs.sortWith { a: IntArray, b: IntArray -> b[1] - a[1] }

        // Use a min-heap to maintain the top k elements.
        val topKHeap: PriorityQueue<Int> = PriorityQueue(k) { a, b -> a - b }
        var topKSum: Long = 0
        for (i in 0 until k) {
            topKSum += pairs[i][0].toLong()
            topKHeap.add(pairs[i][0])
        }

        // The score of the first k pairs.
        var answer = topKSum * pairs[k - 1][1]

        // Iterate over every nums2[i] as minimum from nums2.
        for (i in k until n) {
            // Remove the smallest integer from the previous top k elements
            // then ddd nums1[i] to the top k elements.
            topKSum += pairs[i][0] - topKHeap.poll()
            topKHeap.add(pairs[i][0])

            // Update answer as the maximum score.
            answer = max(answer, topKSum * pairs[i][1])
        }

        return answer
    }
}
