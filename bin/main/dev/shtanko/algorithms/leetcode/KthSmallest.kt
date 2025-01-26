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

import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min

/**
 * Kth Smallest Element in a Sorted Matrix
 * @see <a href="https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix">Source</a>
 */
fun interface KthSmallest {
    operator fun invoke(matrix: Array<IntArray>, k: Int): Int
}

sealed class KthSmallestStrategy {
    /**
     * Time Complexity: let X=min(K,N);X+Klog(X)
     * Space Complexity: O(X) which is occupied by the heap.
     */
    object MinHeap : KthSmallest, KthSmallestStrategy() {
        override operator fun invoke(matrix: Array<IntArray>, k: Int): Int {
            val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
            val indices = MutableList(matrix.size) { 0 }
            matrix.indices.forEach { priorityQueue.add(Pair(matrix[it][indices[it]++], it)) }
            repeat(k - 1) {
                val (_, index) = priorityQueue.poll()
                if (indices[index] < matrix.size) {
                    priorityQueue.add(Pair(matrix[index][indices[index]++], index))
                }
            }
            return priorityQueue.first().first
        }

        override fun toString(): String = "min heap"
    }

    /**
     * Time Complexity: O(N×log(Max−Min))
     * Space Complexity: O(1)
     */
    object BinarySearch : KthSmallest, KthSmallestStrategy() {
        override operator fun invoke(matrix: Array<IntArray>, k: Int): Int {
            val n: Int = matrix.size
            var start = matrix[0][0]
            var end = matrix[n - 1][n - 1]
            while (start < end) {
                val mid = start + (end - start) / 2
                // first number is the smallest and the second number is the largest
                val smallLargePair = intArrayOf(matrix[0][0], matrix[n - 1][n - 1])
                val count = countLessEqual(matrix, mid, smallLargePair)
                if (count == k) {
                    return smallLargePair[0]
                }
                if (count < k) {
                    start = smallLargePair[1] // search higher
                } else {
                    end = smallLargePair[0] // search lower
                }
            }
            return start
        }

        private fun countLessEqual(matrix: Array<IntArray>, mid: Int, smallLargePair: IntArray): Int {
            var count = 0
            val n = matrix.size
            var row = n - 1
            var col = 0
            while (row >= 0 && col < n) {
                if (matrix[row][col] > mid) {
                    // as matrix[row][col] is bigger than the mid, let's keep track of the
                    // smallest number greater than the mid
                    smallLargePair[1] = min(smallLargePair[1], matrix[row][col])
                    row--
                } else {
                    // as matrix[row][col] is less than or equal to the mid, let's keep track of the
                    // biggest number less than or equal to the mid
                    smallLargePair[0] = max(smallLargePair[0], matrix[row][col])
                    count += row + 1
                    col++
                }
            }
            return count
        }

        override fun toString(): String = "binary search"
    }
}
