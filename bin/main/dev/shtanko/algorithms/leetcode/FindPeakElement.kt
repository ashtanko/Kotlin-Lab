/*
 * Copyright 2020 Oleksii Shtanko
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

fun interface FindPeakElementStrategy {
    operator fun invoke(nums: IntArray): Int
}

class FindPeakElementLinear : FindPeakElementStrategy {
    override operator fun invoke(nums: IntArray): Int {
        return helper(nums, 0, nums.lastIndex)
    }

    private fun helper(num: IntArray, start: Int, end: Int): Int {
        return when {
            num.isEmpty() -> {
                0
            }

            start == end -> {
                start
            }

            start + 1 == end -> {
                if (num[start] > num[end]) start else end
            }

            else -> {
                val m = (start + end) / 2
                if (num[m] > num[m - 1] && num[m] > num[m + 1]) {
                    m
                } else if (num[m - 1] > num[m] && num[m] > num[m + 1]) {
                    helper(num, start, m - 1)
                } else {
                    helper(num, m + 1, end)
                }
            }
        }
    }
}

class FindPeakElementRecursiveBinarySearch : FindPeakElementStrategy {
    override operator fun invoke(nums: IntArray): Int {
        return search(nums, 0, nums.lastIndex)
    }

    private fun search(nums: IntArray, l: Int, r: Int): Int {
        if (nums.isEmpty()) return 0
        if (l == r) return l
        val mid = (l + r) / 2
        return if (nums[mid] > nums[mid + 1]) search(nums, l, mid) else search(nums, mid + 1, r)
    }
}

class FindPeakElementIterativeBinarySearch : FindPeakElementStrategy {
    override operator fun invoke(nums: IntArray): Int {
        var l = 0
        var r: Int = nums.lastIndex
        while (l < r) {
            val mid = (l + r) / 2
            if (nums[mid] > nums[mid + 1]) r = mid else l = mid + 1
        }
        return l
    }
}
