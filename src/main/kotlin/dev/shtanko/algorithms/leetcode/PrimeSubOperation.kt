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

import dev.shtanko.algorithms.annotations.level.Medium
import dev.shtanko.extensions.isPrime

/**
 * 2601. Prime Subtraction Operation
 * @see <a href="https://leetcode.com/problems/prime-subtraction-operation/">Source</a>
 */
@Medium("https://leetcode.com/problems/prime-subtraction-operation")
fun interface PrimeSubOperation {
    operator fun invoke(nums: IntArray): Boolean
}

class PrimeSubOperationStoringPrimes : PrimeSubOperation {
    override fun invoke(nums: IntArray): Boolean {
        val maxElement = nums.maxOrNull() ?: return false

        // Store the previousPrime array.
        val previousPrime = IntArray(maxElement + 1)
        for (i in 2..maxElement) {
            previousPrime[i] = if (i.isPrime()) i else previousPrime[i - 1]
        }

        for (i in nums.indices) {
            // In case of first index, we need to find the largest prime less than nums[0].
            val bound = if (i == 0) {
                nums[0]
            } else {
                // Otherwise, we need to find the largest prime that makes the current element
                // closest to the previous element.
                nums[i] - nums[i - 1]
            }

            // If the bound is less than or equal to 0, then the array cannot be made strictly increasing.
            if (bound <= 0) return false

            // Find the largest prime less than bound.
            val largestPrime = previousPrime[bound - 1]

            // Subtract this value from nums[i].
            nums[i] -= largestPrime
        }

        return true
    }
}

class PrimeSubOperationTwoPointers : PrimeSubOperation {
    override fun invoke(nums: IntArray): Boolean {
        val maxElement = nums.maxOrNull() ?: return false

        // Store the sieve array.
        val sieve = IntArray(maxElement + 1) { 1 }
        sieve[1] = 0
        for (i in 2..kotlin.math.sqrt(maxElement.toDouble()).toInt()) {
            if (sieve[i] == 1) {
                for (j in i * i..maxElement step i) {
                    sieve[j] = 0
                }
            }
        }

        // Start by storing the currValue as 1, and the initial index as 0.
        var currValue = 1
        var i = 0
        while (i < nums.size) {
            // Store the difference needed to make nums[i] equal to currValue.
            val difference = nums[i] - currValue

            // If difference is less than 0, then nums[i] is already less than currValue.
            // Return false in this case.
            if (difference < 0) return false

            // If the difference is prime or zero, then nums[i] can be made equal to currValue.
            if (sieve[difference] == 1 || difference == 0) {
                i += 1
                currValue += 1
            } else {
                // Otherwise, try for the next currValue.
                currValue += 1
            }
        }
        return true
    }
}
