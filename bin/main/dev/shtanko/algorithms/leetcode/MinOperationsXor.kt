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

/**
 * 2997. Minimum Number of Operations to Make Array XOR Equal to K
 * @see <a href="https://leetcode.com/problems/minimum-number-of-operations-to-make-array-xor-equal-to-k">Source</a>
 */
fun interface MinOperationsXor {
    /**
     * This function calculates the minimum number of operations to make the XOR of the array equal to a target value.
     * @param nums The array of integers.
     * @param k The target XOR value.
     * @return The minimum number of operations.
     */
    operator fun invoke(nums: IntArray, k: Int): Int
}

/**
 * This class provides an implementation of the MinOperationsXor interface.
 * It uses bitwise operations to calculate the minimum number of operations.
 */
class MinOperationsXorBit : MinOperationsXor {
    override fun invoke(nums: IntArray, targetXor: Int): Int {
        var xorOfNums = calculateXorOfNums(nums)
        var tempTargetXor = targetXor
        var operationsCount = 0

        while (tempTargetXor > 0 || xorOfNums > 0) {
            operationsCount = incrementCountIfBitsDontMatch(tempTargetXor, xorOfNums, operationsCount)
            tempTargetXor = tempTargetXor shr 1
            xorOfNums = xorOfNums shr 1
        }

        return operationsCount
    }

    /**
     * This function calculates the XOR of all numbers in the array.
     * @param nums The array of integers.
     * @return The XOR of all numbers in the array.
     */
    private fun calculateXorOfNums(nums: IntArray): Int {
        var xorOfNums = 0
        for (num in nums) {
            xorOfNums = xorOfNums xor num
        }
        return xorOfNums
    }

    /**
     * This function increments the count of operations if the least significant bits of the target XOR value and the
     * XOR of the numbers do not match.
     * @param tempTargetXor The temporary target XOR value.
     * @param xorOfNums The XOR of all numbers in the array.
     * @param operationsCount The current count of operations.
     * @return The new count of operations.
     */
    private fun incrementCountIfBitsDontMatch(tempTargetXor: Int, xorOfNums: Int, operationsCount: Int): Int {
        var newOperationsCount = operationsCount
        if (tempTargetXor % 2 != xorOfNums % 2) {
            newOperationsCount++
        }
        return newOperationsCount
    }
}

/**
 * This class provides an implementation of the MinOperationsXor interface.
 * It uses the bit count operation to calculate the minimum number of operations.
 */
class MinOperationsXorBitCount : MinOperationsXor {
    /**
     * This function calculates the minimum number of operations to make the XOR of the array equal to a target value.
     * It first calculates the XOR of all numbers in the array, then XORs this value with the target value.
     * The minimum number of operations is the number of 1 bits in this final XOR value.
     * @param nums The array of integers.
     * @param k The target XOR value.
     * @return The minimum number of operations.
     */
    override fun invoke(nums: IntArray, k: Int): Int {
        // Initialize the XOR of all numbers in the array to 0
        var finalXor = 0
        // Calculate the XOR of all numbers in the array
        for (n in nums) {
            finalXor = finalXor xor n
        }
        // The minimum number of operations is the number of 1 bits in the XOR of the XOR of all numbers and the
        // target value
        return Integer.bitCount(finalXor xor k)
    }
}
