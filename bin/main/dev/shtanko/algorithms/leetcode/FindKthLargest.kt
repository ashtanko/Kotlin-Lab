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
import java.util.Random
import kotlin.math.max
import kotlin.math.min

/**
 * 215. Kth Largest Element in an Array
 * @see <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/">Source</a>
 */
fun interface FindKthLargest {
    operator fun invoke(nums: IntArray, k: Int): Int
}

/**
 * Approach 1: Sort
 */
class FindKthLargestSort : FindKthLargest {
    override operator fun invoke(nums: IntArray, k: Int) = nums.sorted()[nums.size - k]
}

/**
 * Approach 2: Min-Heap
 */
class FindKthLargestMinHeap : FindKthLargest {
    override operator fun invoke(nums: IntArray, k: Int): Int {
        val heap: PriorityQueue<Int> = PriorityQueue()
        for (num in nums) {
            heap.add(num)
            if (heap.size > k) {
                heap.remove()
            }
        }
        return heap.peek()
    }
}

/**
 * Approach 3: Quickselect
 */
class FindKthLargestQuickSelect : FindKthLargest {
    override operator fun invoke(nums: IntArray, k: Int): Int {
        val list: MutableList<Int> = ArrayList()
        for (num in nums) {
            list.add(num)
        }
        return quickSelect(list, k)
    }

    private fun quickSelect(nums: List<Int>, k: Int): Int {
        val pivotIndex: Int = Random().nextInt(nums.size)
        val pivot = nums[pivotIndex]
        val left: MutableList<Int> = ArrayList()
        val mid: MutableList<Int> = ArrayList()
        val right: MutableList<Int> = ArrayList()
        for (num in nums) {
            if (num > pivot) {
                left.add(num)
            } else if (num < pivot) {
                right.add(num)
            } else {
                mid.add(num)
            }
        }
        if (k <= left.size) {
            return quickSelect(left, k)
        }
        return if (left.size + mid.size < k) {
            quickSelect(right, k - left.size - mid.size)
        } else {
            pivot
        }
    }
}

/**
 * Approach 4: Counting Sort
 */
class FindKthLargestCountingSort : FindKthLargest {
    override operator fun invoke(nums: IntArray, k: Int): Int {
        var minValue = Int.MAX_VALUE
        var maxValue = Int.MIN_VALUE

        for (num in nums) {
            minValue = min(minValue.toDouble(), num.toDouble()).toInt()
            maxValue = max(maxValue.toDouble(), num.toDouble()).toInt()
        }

        val count = IntArray(maxValue - minValue + 1)
        for (num in nums) {
            count[num - minValue]++
        }

        var remain = k
        for (num in count.indices.reversed()) {
            remain -= count[num]
            if (remain <= 0) {
                return num + minValue
            }
        }

        return -1
    }
}
