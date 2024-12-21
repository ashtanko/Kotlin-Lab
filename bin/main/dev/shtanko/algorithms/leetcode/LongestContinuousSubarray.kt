/*
 * Copyright 2024 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.Iterative
import java.util.Deque
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.TreeMap

/**
 * 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 * @see
 * <a href="https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit">
 *     Source</a>
 */
fun interface LongestContinuousSubarray {
    operator fun invoke(nums: IntArray, limit: Int): Int
}

class LongestContinuousSubarrayTwoHeaps : LongestContinuousSubarray {
    override fun invoke(nums: IntArray, limit: Int): Int {
        val maxHeap = PriorityQueue(compareByDescending<Pair<Int, Int>> { it.first })
        val minHeap = PriorityQueue(compareBy<Pair<Int, Int>> { it.first })
        var left = 0
        var maxLength = 0

        for (right in nums.indices) {
            maxHeap.offer(Pair(nums[right], right))
            minHeap.offer(Pair(nums[right], right))

            // Check if the absolute difference between the maximum and minimum values in the current window
            // exceeds the limit
            while (maxHeap.peek().first - minHeap.peek().first > limit) {
                // Move the left pointer to the right until the condition is satisfied.
                // This ensures we remove the element causing the violation
                left = minOf(maxHeap.peek().second, minHeap.peek().second) + 1

                // Remove elements from the heaps that are outside the current window
                while (maxHeap.peek().second < left) {
                    maxHeap.poll()
                }
                while (minHeap.peek().second < left) {
                    minHeap.poll()
                }
            }

            // Update maxLength with the length of the current valid window
            maxLength = maxOf(maxLength, right - left + 1)
        }

        return maxLength
    }
}

class LongestContinuousSubarrayMultiset : LongestContinuousSubarray {
    override fun invoke(nums: IntArray, limit: Int): Int {
        val window = TreeMap<Int, Int>()
        var left = 0
        var maxLength = 0

        for (right in nums.indices) {
            window[nums[right]] = window.getOrDefault(nums[right], 0) + 1

            // Check if the absolute difference between the maximum and minimum values in the current window
            // exceeds the limit
            while (window.lastKey() - window.firstKey() > limit) {
                window[nums[left]] = window.getOrDefault(nums[left], 0) - 1
                if (window[nums[left]] == 0) {
                    window.remove(nums[left])
                }
                // Move the left pointer to the right to exclude the element causing the violation
                left++
            }

            // Update maxLength with the length of the current valid window
            maxLength = maxOf(maxLength, right - left + 1)
        }

        return maxLength
    }
}

@Iterative
class LongestContinuousSubarrayDoubleDeque : LongestContinuousSubarray {
    override fun invoke(nums: IntArray, limit: Int): Int {
        val maxDeque: Deque<Int> = LinkedList()
        val minDeque: Deque<Int> = LinkedList()
        var left = 0
        var maxLength = 0

        for (right in nums.indices) {
            maintainMaxDeque(maxDeque, nums[right])
            maintainMinDeque(minDeque, nums[right])

            while (maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
                adjustWindow(maxDeque, minDeque, nums[left])
                left++
            }

            maxLength = maxOf(maxLength, right - left + 1)
        }

        return maxLength
    }

    private fun maintainMaxDeque(maxDeque: Deque<Int>, value: Int) {
        while (maxDeque.isNotEmpty() && maxDeque.peekLast() < value) {
            maxDeque.pollLast()
        }
        maxDeque.offerLast(value)
    }

    private fun maintainMinDeque(minDeque: Deque<Int>, value: Int) {
        while (minDeque.isNotEmpty() && minDeque.peekLast() > value) {
            minDeque.pollLast()
        }
        minDeque.offerLast(value)
    }

    private fun adjustWindow(maxDeque: Deque<Int>, minDeque: Deque<Int>, value: Int) {
        if (maxDeque.peekFirst() == value) {
            maxDeque.pollFirst()
        }
        if (minDeque.peekFirst() == value) {
            minDeque.pollFirst()
        }
    }
}
