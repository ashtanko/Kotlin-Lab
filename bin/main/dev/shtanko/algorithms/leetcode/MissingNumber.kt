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

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 */
fun interface AbstractMissingNumberStrategy {
    operator fun invoke(nums: IntArray): Int
}

// Time complexity : O(n lg n)
// Space complexity : O(1) or O(n)
class MissingNumberSorting : AbstractMissingNumberStrategy {
    override operator fun invoke(nums: IntArray): Int {
        nums.sort()
        if (nums.isEmpty()) return 0
        if (nums.last() != nums.size) {
            return nums.size
        } else if (nums.first() != 0) {
            return 0
        }

        for (i in 1 until nums.size) {
            val expectedNum = nums[i - 1] + 1
            if (nums[i] != expectedNum) {
                return expectedNum
            }
        }

        return -1
    }
}

// Time complexity : O(n)
// Space complexity : O(n)
class MissingNumberHashSet : AbstractMissingNumberStrategy {
    override operator fun invoke(nums: IntArray): Int {
        val numSet: MutableSet<Int> = HashSet()
        for (num in nums) {
            numSet.add(num)
        }
        val expectedNumCount = nums.size + 1
        for (number in 0 until expectedNumCount) {
            if (!numSet.contains(number)) {
                return number
            }
        }
        return -1
    }
}

// Time complexity : O(n)
// Space complexity : O(1)
class MissingNumberBitManipulation : AbstractMissingNumberStrategy {
    override operator fun invoke(nums: IntArray): Int {
        var missing = nums.size
        for (i in nums.indices) {
            missing = missing xor (i xor nums[i])
        }
        return missing
    }
}

// Time complexity : O(n)
// Space complexity : O(1)
class MissingNumberGaussFormula : AbstractMissingNumberStrategy {
    override operator fun invoke(nums: IntArray): Int {
        val local = nums.size + 1
        val expectedSum = nums.size * local / 2
        var actualSum = 0
        for (num in nums) {
            actualSum += num
        }
        return expectedSum - actualSum
    }
}
