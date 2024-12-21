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

/**
 * 373. Find K Pairs with Smallest Sums
 * @see <a href="https://leetcode.com/problems/find-k-pairs-with-smallest-sums/">Source</a>
 */
fun interface KSmallestPairs {
    operator fun invoke(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>>
}

/**
 * Approach: Using Heap
 */
class KSmallestPairsHeap : KSmallestPairs {
    override operator fun invoke(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
        val m: Int = nums1.size
        val n: Int = nums2.size

        val ans: MutableList<List<Int>> = ArrayList()
        val visited: MutableSet<Pair<Int, Int>> = HashSet()

        val minHeap: PriorityQueue<IntArray> = PriorityQueue { a, b -> a[0] - b[0] }
        minHeap.offer(intArrayOf(nums1[0] + nums2[0], 0, 0))
        visited.add(Pair(0, 0))
        var k0 = k
        while (k0-- > 0 && minHeap.isNotEmpty()) {
            val top: IntArray = minHeap.poll()
            val i = top[1]
            val j = top[2]
            ans.add(listOf(nums1[i], nums2[j]))
            if (i + 1 < m && !visited.contains(Pair(i + 1, j))) {
                minHeap.offer(intArrayOf(nums1[i + 1] + nums2[j], i + 1, j))
                visited.add(Pair(i + 1, j))
            }
            if (j + 1 < n && !visited.contains(Pair(i, j + 1))) {
                minHeap.offer(intArrayOf(nums1[i] + nums2[j + 1], i, j + 1))
                visited.add(Pair(i, j + 1))
            }
        }

        return ans
    }
}
