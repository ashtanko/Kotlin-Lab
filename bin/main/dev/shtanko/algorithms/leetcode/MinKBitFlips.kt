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

import java.util.Deque
import java.util.LinkedList

/**
 * 995. Minimum Number of K Consecutive Bit Flips
 * @see <a href="https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/">Source</a>
 */
fun interface MinKBitFlips {
    operator fun invoke(nums: IntArray, k: Int): Int
}

class MinKBitFlipsArray : MinKBitFlips {
    override fun invoke(nums: IntArray, k: Int): Int {
        val flipped = BooleanArray(nums.size)
        var validFlipsFromPastWindow = 0
        var flipCount = 0

        for (i in nums.indices) {
            if (i >= k && flipped[i - k]) {
                validFlipsFromPastWindow--
            }

            if (validFlipsFromPastWindow % 2 == nums[i]) {
                if (i + k > nums.size) {
                    return -1
                }
                validFlipsFromPastWindow++
                flipped[i] = true
                flipCount++
            }
        }

        return flipCount
    }
}

class MinKBitFlipsDeque : MinKBitFlips {
    override fun invoke(nums: IntArray, k: Int): Int {
        val n: Int = nums.size
        val flipQueue: Deque<Int> = LinkedList()
        var flipped = 0
        var result = 0

        for (i in 0 until n) {
            if (i >= k && flipQueue.isNotEmpty()) {
                flipped = flipped xor flipQueue.poll()
            }
            if (flipped == nums[i]) {
                if (i + k > n) {
                    return -1
                }
                flipQueue.offer(1)
                flipped = flipped xor 1
                result += 1
            } else {
                flipQueue.offer(0)
            }
        }

        return result
    }
}

class MinKBitFlipsSpaceOptimized : MinKBitFlips {
    override fun invoke(nums: IntArray, k: Int): Int {
        var currentFlips = 0
        var totalFlips = 0

        for (i in nums.indices) {
            if (i >= k && nums[i - k] == 2) {
                currentFlips--
            }
            if ((currentFlips % 2) == nums[i]) {
                if (i + k > nums.size) {
                    return -1
                }
                nums[i] = 2
                currentFlips++
                totalFlips++
            }
        }

        return totalFlips
    }
}
