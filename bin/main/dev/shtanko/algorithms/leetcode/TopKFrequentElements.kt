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
import java.util.Queue
import java.util.Random

/**
 * 347. Top K Frequent Elements
 * @see <a href="https://leetcode.com/problems/top-k-frequent-elements/">Source</a>
 */
fun interface TopKFrequentElements {
    operator fun invoke(nums: IntArray, k: Int): IntArray
}

/**
 * Approach 1: Heap
 */
class TopKFrequentElementsHeap : TopKFrequentElements {
    override fun invoke(nums: IntArray, k: Int): IntArray {
        // O(1) time
        if (k == nums.size) {
            return nums
        }

        // 1. build hash map : character and how often it appears
        // O(N) time
        val count: MutableMap<Int, Int> = HashMap()
        for (n in nums) {
            count[n] = count.getOrDefault(n, 0) + 1
        }

        // init heap 'the less frequent element first'
        val heap: Queue<Int> = PriorityQueue { n1, n2 -> count[n1]!! - count[n2]!! }

        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (n in count.keys) {
            heap.add(n)
            if (heap.size > k) heap.poll()
        }

        // 3. build an output array
        // O(k log k) time

        // 3. build an output array
        // O(k log k) time
        val top = IntArray(k)
        for (i in k - 1 downTo 0) {
            top[i] = heap.poll()
        }
        return top
    }
}

/**
 * Approach 2: Quickselect (Hoare's selection algorithm)
 */
class TopKFrequentElementsQuickSelect : TopKFrequentElements {
    lateinit var unique: IntArray
    var count: MutableMap<Int, Int> = HashMap()

    override fun invoke(nums: IntArray, k: Int): IntArray {
        // build hash map : character and how often it appears

        for (num in nums) {
            count[num] = count.getOrDefault(num, 0) + 1
        }

        // array of unique elements
        val n = count.size
        unique = IntArray(n)
        for ((i, num) in count.keys.withIndex()) {
            unique[i] = num
        }

        // kth top frequent element is (n - k)th less frequent.
        // Do a partial sort: from less frequent to the most frequent, till
        // (n - k)th less frequent element takes its place (n - k) in a sorted array.
        // All element on the left are less frequent.
        // All the elements on the right are more frequent.
        quickSelect(0, n - 1, n - k)
        // Return top k frequent elements
        return unique.copyOfRange(n - k, n)
    }

    fun swap(a: Int, b: Int) {
        val tmp = unique[a]
        unique[a] = unique[b]
        unique[b] = tmp
    }

    fun partition(left: Int, right: Int, pivotIndex: Int): Int {
        val pivotFrequency = count[unique[pivotIndex]]!!
        // 1. move pivot to end
        swap(pivotIndex, right)
        var storeIndex = left

        // 2. move all less frequent elements to the left
        for (i in left..right) {
            if (count[unique[i]]!! < pivotFrequency) {
                swap(storeIndex, i)
                storeIndex++
            }
        }

        // 3. move pivot to its final place
        swap(storeIndex, right)
        return storeIndex
    }

    /**
     * Sort a list within left.right till kth less frequent element
     * takes its place.
     */
    private fun quickSelect(left: Int, right: Int, kSmallest: Int) {
        // base case: the list contains only one element
        if (left == right) return

        // select a random pivot_index
        val randomNum = Random()
        var pivotIndex: Int = left + randomNum.nextInt(right - left)

        // find the pivot position in a sorted list
        pivotIndex = partition(left, right, pivotIndex)

        // if the pivot is in its final sorted position
        if (kSmallest == pivotIndex) {
            return
        } else if (kSmallest < pivotIndex) {
            // go left
            quickSelect(left, pivotIndex - 1, kSmallest)
        } else {
            // go right
            quickSelect(pivotIndex + 1, right, kSmallest)
        }
    }
}
