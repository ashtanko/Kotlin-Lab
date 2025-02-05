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

import java.util.BitSet
import kotlin.math.abs

/**
 * 2441. Largest Positive Integer That Exists With Its Negative
 * @see <a href="https://leetcode.com/problems/largest-positive-integer-that-exists-with-its-negative/">Source</a>
 */
fun interface FindMaxK {
    operator fun invoke(nums: IntArray): Int
}

class FindMaxKBruteForce : FindMaxK {
    override fun invoke(nums: IntArray): Int {
        var ans = -1
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                if (nums[i] == -nums[j]) {
                    ans = maxOf(ans, abs(nums[i]))
                }
            }
        }
        return ans
    }
}

class FindMaxKTwoPointer : FindMaxK {
    override fun invoke(nums: IntArray): Int {
        nums.sort()
        var left = 0
        var right = nums.size - 1
        while (left < right) {
            when {
                nums[left] == -nums[right] -> return maxOf(nums[left], nums[right])
                nums[left] < -nums[right] -> left++
                else -> right--
            }
        }
        return -1
    }
}

class FindMaxKTwoPass : FindMaxK {
    override fun invoke(nums: IntArray): Int {
        // Initialize a HashSet to store negative numbers
        val neg: MutableSet<Int> = HashSet()

        // Loop through the numbers and insert negative numbers into the set
        for (num in nums) {
            if (num < 0) {
                neg.add(num)
            }
        }

        var ans = -1

        for (num in nums) {
            // If current number is greater than ans and its negation exists in the set
            if (num > ans && neg.contains(-num)) {
                ans = num
            }
        }

        return ans
    }
}

class FindMaxKOnePass : FindMaxK {
    override fun invoke(nums: IntArray): Int {
        var ans = -1

        val seenNumbers: MutableSet<Int> = HashSet()

        for (number in nums) {
            val absoluteNumber = abs(number)
            // If the absolute value is greater than the current maximum and its negation is seen
            if (absoluteNumber > ans && seenNumbers.contains(-number)) {
                ans = absoluteNumber
            }
            seenNumbers.add(number) // Insert the current number into the set
        }

        return ans
    }
}

class FindMaxKBitwise : FindMaxK {
    override fun invoke(nums: IntArray): Int {
        val bitSet = BitSet()
        var ans = -1

        for (num in nums) {
            val numAbs = abs(num)
            if (numAbs > ans && bitSet[numAbs]) {
                ans = numAbs
            }
            bitSet.set(numAbs)
        }

        return ans
    }
}
