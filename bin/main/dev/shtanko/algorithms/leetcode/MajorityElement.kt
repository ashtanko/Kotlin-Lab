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

import java.util.Random

/**
 * 169. Majority Element
 * @see <a href="https://leetcode.com/problems/majority-element">Source</a>
 */
fun interface MajorityElement {
    operator fun invoke(nums: IntArray): Int
}

class MajorityElementHashTable : MajorityElement {
    override fun invoke(nums: IntArray): Int {
        val counter = HashMap<Int, Int>()

        for (num in nums) {
            val count = counter.getOrDefault(num, 0) + 1
            counter[num] = count

            if (count > nums.size / 2) {
                return num
            }
        }

        return 0
    }
}

class MajorityElementSorting : MajorityElement {
    override fun invoke(nums: IntArray) = nums.takeIf { it.isNotEmpty() }?.sorted()?.get(nums.size / 2) ?: 0
}

class MajorityElementRandomization : MajorityElement {
    override fun invoke(nums: IntArray): Int {
        val arraySize = nums.size
        if (arraySize == 0) return 0
        val random = Random()
        var candidate: Int
        var counter: Int

        while (true) {
            candidate = nums[random.nextInt(arraySize)]
            counter = 0

            for (num in nums) {
                if (num == candidate) {
                    counter++
                }
            }

            if (counter > arraySize / 2) {
                return candidate
            }
        }
    }
}

class MajorityElementDivideAndConquer : MajorityElement {
    override fun invoke(nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }
        return findMajorityElement(nums.toList(), 0, nums.size - 1)
    }

    private fun findMajorityElement(nums: List<Int>, leftIndex: Int, rightIndex: Int): Int {
        if (leftIndex == rightIndex) {
            return nums[leftIndex]
        }

        val middleIndex = leftIndex + (rightIndex - leftIndex) / 2
        val leftMajority = findMajorityElement(nums, leftIndex, middleIndex)
        val rightMajority = findMajorityElement(nums, middleIndex + 1, rightIndex)

        if (leftMajority == rightMajority) {
            return leftMajority
        }

        val leftMajorityCount = nums.subList(leftIndex, rightIndex + 1).count { it == leftMajority }
        val rightMajorityCount = nums.subList(leftIndex, rightIndex + 1).count { it == rightMajority }

        return if (leftMajorityCount > rightMajorityCount) leftMajority else rightMajority
    }
}

class MajorityElementBit : MajorityElement {

    companion object {
        private const val TOTAL_BITS = 32
    }

    override fun invoke(nums: IntArray): Int {
        var majorityElement = 0

        for (bitIndex in 0 until TOTAL_BITS) {
            var bitCount = 0

            for (num in nums) {
                if (num and (1 shl bitIndex) != 0) {
                    bitCount++
                }
            }

            if (bitCount > nums.size / 2) {
                majorityElement = majorityElement or (1 shl bitIndex)
            }
        }

        return majorityElement
    }
}
