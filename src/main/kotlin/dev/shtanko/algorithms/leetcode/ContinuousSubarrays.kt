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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.TwoPointers
import java.util.PriorityQueue

/**
 * 2762. Continuous Subarrays
 * @see <a href="https://leetcode.com/problems/continuous-subarrays/">Source</a>
 */
sealed interface ContinuousSubarrays {
    operator fun invoke(nums: IntArray): Long

    data object SortedMap : ContinuousSubarrays {
        override fun invoke(nums: IntArray): Long {
            // TreeMap to maintain sorted frequency map of current window
            val freq = sortedMapOf<Int, Int>()
            var left = 0
            var count = 0L // Total count of valid subarrays

            for (right in nums.indices) {
                // Add current element to frequency map
                freq[nums[right]] = freq.getOrDefault(nums[right], 0) + 1

                // While window violates the condition |nums[i] - nums[j]| ≤ 2
                // Shrink window from left
                while (freq.lastKey() - freq.firstKey() > 2) {
                    // Remove leftmost element from frequency map
                    freq[nums[left]] = freq[nums[left]]!! - 1
                    if (freq[nums[left]] == 0) {
                        freq.remove(nums[left])
                    }
                    left++
                }

                // Add count of all valid subarrays ending at right
                count += (right - left + 1).toLong()
            }

            return count
        }
    }

    data object PriorityQueueStrategy : ContinuousSubarrays {
        override fun invoke(nums: IntArray): Long {
            var left = 0
            var count = 0L // Total count of valid subarrays

            // Min and max heaps storing indices, sorted by nums[index] values
            val minHeap = PriorityQueue<Int> { a, b -> nums[a] - nums[b] }
            val maxHeap = PriorityQueue<Int> { a, b -> nums[b] - nums[a] }

            for (right in nums.indices) {
                // Add current index to both heaps
                minHeap.add(right)
                maxHeap.add(right)

                // While window violates |nums[i] - nums[j]| ≤ 2 condition
                // Shrink window from left and remove outdated indices
                while (nums[maxHeap.peek()] - nums[minHeap.peek()] > 2) {
                    left++

                    // Remove indices that are now outside the window
                    while (maxHeap.isNotEmpty() && maxHeap.peek() < left) {
                        maxHeap.poll()
                    }
                    while (minHeap.isNotEmpty() && minHeap.peek() < left) {
                        minHeap.poll()
                    }
                }

                // Add count of all valid subarrays ending at right
                count += (right - left + 1).toLong()
            }

            return count
        }
    }

    data object MonotonicDeque : ContinuousSubarrays {
        override fun invoke(nums: IntArray): Long {
            // Monotonic deque to track maximum and minimum elements
            val maxQ = ArrayDeque<Int>()
            val minQ = ArrayDeque<Int>()
            var left = 0
            var count = 0L

            for (right in nums.indices) {
                // Maintain decreasing monotonic queue for maximum values
                while (maxQ.isNotEmpty() && nums[maxQ.last()] < nums[right]) {
                    maxQ.removeLast()
                }
                maxQ.addLast(right)

                // Maintain increasing monotonic queue for minimum values
                while (minQ.isNotEmpty() && nums[minQ.last()] > nums[right]) {
                    minQ.removeLast()
                }
                minQ.addLast(right)

                // Shrink window if max-min difference exceeds 2
                while (maxQ.isNotEmpty() && minQ.isNotEmpty() &&
                    nums[maxQ.first()] - nums[minQ.first()] > 2
                ) {
                    // Move left pointer past the element that breaks the condition
                    if (maxQ.first() < minQ.first()) {
                        left = maxQ.removeFirst() + 1
                    } else {
                        left = minQ.removeFirst() + 1
                    }
                }

                // Add count of all valid subarrays ending at current right pointer
                count += (right - left + 1).toLong()
            }
            return count
        }
    }

    @TwoPointers
    data object OptimizedTwoPointer : ContinuousSubarrays {
        override fun invoke(nums: IntArray): Long {
            var right = 0
            var left = 0
            var curMin = nums[0]
            var curMax = nums[0]
            var windowLen: Long
            var total = 0L

            for (right in nums.indices) {
                // Update min and max for the current window
                curMin = minOf(curMin, nums[right])
                curMax = maxOf(curMax, nums[right])

                // If window condition breaks (diff > 2)
                if (curMax - curMin > 2) {
                    // Add subarrays from the previous valid window
                    windowLen = (right - left).toLong()
                    total += (windowLen * (windowLen + 1)) / 2

                    // Start new window at the current position
                    left = right
                    curMin = nums[right]
                    curMax = nums[right]

                    // Expand left boundary while maintaining condition
                    while (left > 0 && kotlin.math.abs(nums[right] - nums[left - 1]) <= 2) {
                        left--
                        curMin = minOf(curMin, nums[left])
                        curMax = maxOf(curMax, nums[left])
                    }

                    // Remove overcounted subarrays if the left boundary expanded
                    if (left < right) {
                        windowLen = (right - left).toLong()
                        total -= (windowLen * (windowLen + 1)) / 2
                    }
                }
            }

            // Add subarrays from the final window
            windowLen = (nums.size - left).toLong()
            total += (windowLen * (windowLen + 1)) / 2

            return total
        }
    }
}
