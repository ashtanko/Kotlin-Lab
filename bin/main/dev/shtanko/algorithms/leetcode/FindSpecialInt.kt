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

/**
 * 1287. Element Appearing More Than 25% In Sorted Array
 * @see <a href="https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array">Source</a>
 */
fun interface FindSpecialInt {
    operator fun invoke(arr: IntArray): Int
}

class FindSpecialIntHashMap : FindSpecialInt {
    override fun invoke(arr: IntArray): Int {
        val counts: MutableMap<Int, Int> = HashMap()
        val target: Int = arr.size / 4
        for (num in arr) {
            val value = counts.getOrDefault(num, 0)
            counts[num] = value + 1
            if (value > target) {
                return num
            }
        }

        return -1
    }
}

class FindSpecialIntCheckElement : FindSpecialInt {
    override fun invoke(arr: IntArray): Int {
        val size: Int = arr.size / 4
        for (i in 0 until arr.size - size) {
            if (arr[i] == arr[i + size]) {
                return arr[i]
            }
        }

        return -1
    }
}

class FindSpecialIntBS : FindSpecialInt {
    override fun invoke(arr: IntArray): Int {
        val n: Int = arr.size
        if (n > 1) {
            val candidates = intArrayOf(arr[n / 4], arr[n / 2], arr[3 * n / 4])
            val target = n / 4

            for (candidate in candidates) {
                val left: Int = lowerBound(arr, candidate)
                val right: Int = upperBound(arr, candidate) - 1
                if (right - left + 1 > target) {
                    return candidate
                }
            }
        }
        return -1
    }

    private fun upperBound(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size
        while (left < right) {
            val mid = left + (right - left) / 2
            if (arr[mid] > target) {
                right = mid
            } else {
                left = mid + 1
            }
        }

        return left
    }

    private fun lowerBound(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size
        while (left < right) {
            val mid = left + (right - left) / 2
            if (arr[mid] >= target) {
                right = mid
            } else {
                left = mid + 1
            }
        }

        return left
    }
}
