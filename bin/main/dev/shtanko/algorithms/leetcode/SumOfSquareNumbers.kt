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

import kotlin.math.sqrt

/**
 * 633. Sum of Square Numbers
 * @see <a href="https://leetcode.com/problems/sum-of-square-numbers/">Sum of Square Numbers</a>
 */
fun interface SumOfSquareNumbers {
    operator fun invoke(target: Int): Boolean
}

/**
 * This class implements the SumOfSquareNumbers interface using a brute force approach.
 * It checks all possible pairs of integers to find a pair whose squares sum up to the target number.
 */
class SumOfSquareNumbersBruteForce : SumOfSquareNumbers {
    override fun invoke(target: Int): Boolean {
        var firstFactor: Long = 0
        while (firstFactor * firstFactor <= target) {
            var secondFactor: Long = 0
            while (secondFactor * secondFactor <= target) {
                if (firstFactor * firstFactor + secondFactor * secondFactor == target.toLong()) {
                    return true
                }
                secondFactor++
            }
            firstFactor++
        }
        return false
    }
}

/**
 * This class implements the SumOfSquareNumbers interface using a slightly optimized brute force approach.
 * It reduces the search space by subtracting the square of the first factor from the target number and only
 * checking the remainder.
 */
class SumOfSquareNumbersBetterBruteForce : SumOfSquareNumbers {
    override fun invoke(target: Int): Boolean {
        var firstFactor: Long = 0
        while (firstFactor * firstFactor <= target) {
            val remainder: Int = target - (firstFactor * firstFactor).toInt()
            var increment = 1
            var sum = 0
            while (sum < remainder) {
                sum += increment
                increment += 2
            }
            if (sum == remainder) return true
            firstFactor++
        }
        return false
    }
}

/**
 * This class implements the SumOfSquareNumbers interface using the square root property.
 * It reduces the search space by checking if the square root of the difference between the target number and the
 * square of the first factor is an integer.
 */
class SumOfSquareNumbersSqrt : SumOfSquareNumbers {
    override fun invoke(target: Int): Boolean {
        var firstFactor: Long = 0
        while (firstFactor * firstFactor <= target) {
            val squareRoot = sqrt(target.toDouble() - firstFactor * firstFactor)
            if (squareRoot == squareRoot.toInt().toDouble()) {
                return true
            }
            firstFactor++
        }
        return false
    }
}

/**
 * This class implements the SumOfSquareNumbers interface using binary search.
 * It reduces the search space by performing a binary search on the range from 0 to the remainder after subtracting the
 * square of the first factor from the target number.
 */
class SumOfSquareNumbersBinarySearch : SumOfSquareNumbers {
    override fun invoke(target: Int): Boolean {
        var firstFactor: Long = 0
        while (firstFactor * firstFactor <= target) {
            val remainder: Int = target - (firstFactor * firstFactor).toInt()
            if (binarySearch(0, remainder.toLong(), remainder)) return true
            firstFactor++
        }
        return false
    }

    /**
     * This is a helper function that performs a binary search on the range from start to end to find a number whose
     * square is equal to the target.
     */
    private fun binarySearch(start: Long, end: Long, target: Int): Boolean {
        if (start > end) return false
        val mid = start + (end - start) / 2
        if (mid * mid == target.toLong()) {
            return true
        }
        if (mid * mid > target) {
            return binarySearch(start, mid - 1, target)
        }
        return binarySearch(mid + 1, end, target)
    }
}

/**
 * This class implements the SumOfSquareNumbers interface using Fermat's theorem.
 * It reduces the search space by checking if the target number can be expressed as the sum of two squares based
 * on the theorem.
 */
class SumOfSquareNumbersFermatTheorem : SumOfSquareNumbers {
    override fun invoke(target: Int): Boolean {
        var factor = 2
        var remainingTarget = target
        while (factor * factor <= remainingTarget) {
            var count = 0
            if (remainingTarget % factor == 0) {
                while (remainingTarget % factor == 0) {
                    count++
                    remainingTarget /= factor
                }
                if (factor % 4 == 3 && count % 2 != 0) {
                    return false
                }
            }
            factor++
        }
        return remainingTarget % 4 != 3
    }
}
