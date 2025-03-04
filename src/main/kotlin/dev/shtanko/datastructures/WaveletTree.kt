/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.datastructures

/**
 * A wavelet tree is a binary tree that represents a sequence of integers. It allows for fast queries
 * such as counting the number of occurrences of a value in a range, finding the k-th smallest element
 * in a range, and finding the rank of a value in a range.
 * The wavelet tree is built recursively by partitioning the sequence into two halves based on a threshold value.
 * The threshold value is the median of the range of values in the current node.
 * The time complexity for building the wavelet tree is O(N log M), where N is the number of elements in the sequence
 * and M is the range of values.
 * The space complexity is O(N log M).
 * The time complexity for queries is O(log M), where M is the range of values.
 * The space complexity for queries is O(log M).
 * The wavelet tree can be used to solve a variety of problems, including range queries on sequences of integers.
 * The tree is 0-indexed, so the first element is at index 0.
 * The tree is built for the range [minValue, maxValue].
 * The range of values in the sequence should be in the range [minValue, maxValue].
 * The wavelet tree supports the following operations:
 * - count(x, l, r): Count the number of occurrences of value `x` in the range [l, r].
 * - kthSmallest(l, r, k): Find the k-th smallest element in the range [l, r].
 * - rank(x, l, r): Find the rank of value `x` in the range [l, r].
 *
 * @property array The sequence of integers.
 * @property minValue The minimum value in the sequence.
 * @property maxValue The maximum value in the sequence.
 */
class WaveletTree(array: IntArray, private val minValue: Int, private val maxValue: Int) {

    private var leftTree: WaveletTree? = null
    private var rightTree: WaveletTree? = null
    private val boundary: Int
    private val prefixSums: IntArray

    /**
     * Initialize the wavelet tree with the given sequence of integers and the range of values.
     * The tree is built recursively by partitioning the sequence into two halves based on a threshold value.
     * The threshold value is the median of the range of values in the current node.
     * The time complexity for building the wavelet tree is O(N log M), where N is the number of elements in the
     * sequence and M is the range of values.
     * The space complexity is O(N log M).
     */
    init {
        if (minValue == maxValue) {
            // Leaf node
            boundary = minValue
            prefixSums = IntArray(array.size + 1) { it } // Cumulative count of elements
        } else {
            // Non-leaf node
            val mid = (minValue + maxValue) / 2
            boundary = mid
            prefixSums = IntArray(array.size + 1)

            val leftArray = mutableListOf<Int>()
            val rightArray = mutableListOf<Int>()

            for (i in array.indices) {
                prefixSums[i + 1] = prefixSums[i]
                if (array[i] <= mid) {
                    leftArray.add(array[i])
                    prefixSums[i + 1]++
                } else {
                    rightArray.add(array[i])
                }
            }

            if (leftArray.isNotEmpty()) {
                leftTree = WaveletTree(leftArray.toIntArray(), minValue, mid)
            }
            if (rightArray.isNotEmpty()) {
                rightTree = WaveletTree(rightArray.toIntArray(), mid + 1, maxValue)
            }
        }
    }

    /**
     * Count the number of occurrences of value `x` in the range [l, r].
     * The time complexity for this operation is O(log M), where M is the range of values.
     * The space complexity for this operation is O(log M).
     *
     * @param x The value to count.
     * @param l The left index of the range.
     * @param r The right index of the range.
     *
     * @return The number of occurrences of value `x` in the range [l, r].
     */
    fun count(x: Int, l: Int, r: Int): Int {
        if (x < minValue || x > maxValue || l > r) return 0
        if (minValue == maxValue) return r - l + 1
        val leftCount = prefixSums[l]
        val rightCount = prefixSums[r + 1]
        return if (x <= boundary) {
            leftTree?.count(x, leftCount, rightCount - 1) ?: 0
        } else {
            rightTree?.count(x, l - leftCount, r - rightCount) ?: 0
        }
    }

    /**
     * Find the k-th smallest element in the range [l, r].
     * The time complexity for this operation is O(log M), where M is the range of values.
     * The space complexity for this operation is O(log M).
     *
     * @param l The left index of the range.
     * @param r The right index of the range.
     * @param k The k-th smallest element to find.
     *
     * @return The k-th smallest element in the range [l, r].
     */
    fun kthSmallest(l: Int, r: Int, k: Int): Int {
        if (minValue == maxValue) return minValue
        val leftCount = prefixSums[r + 1] - prefixSums[l]
        return if (k <= leftCount) {
            leftTree?.kthSmallest(prefixSums[l], prefixSums[r + 1] - 1, k) ?: minValue
        } else {
            rightTree?.kthSmallest(l - prefixSums[l], r - prefixSums[r + 1], k - leftCount) ?: maxValue
        }
    }

    /**
     * Find the rank of value `x` in the range [l, r].
     * The time complexity for this operation is O(log M), where M is the range of values.
     * The space complexity for this operation is O(log M).
     *
     * @param x The value to find the rank of.
     * @param l The left index of the range.
     * @param r The right index of the range.
     *
     * @return The rank of value `x` in the range [l, r].
     */
    fun rank(x: Int, l: Int, r: Int): Int {
        if (x < minValue || x > maxValue) return 0
        if (minValue == maxValue) return r - l + 1
        val leftCount = prefixSums[r + 1] - prefixSums[l]
        return if (x <= boundary) {
            leftTree?.rank(x, prefixSums[l], prefixSums[r + 1] - 1) ?: 0
        } else {
            leftCount + (rightTree?.rank(x, l - prefixSums[l], r - prefixSums[r + 1]) ?: 0)
        }
    }
}
