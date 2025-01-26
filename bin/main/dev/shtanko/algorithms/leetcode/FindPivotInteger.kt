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
 * 2485. Find the Pivot Integer
 * @see <a href="https://leetcode.com/problems/find-the-pivot-integer">Source</a>
 */
fun interface FindPivotInteger {
    operator fun invoke(num: Int): Int
}

class FindPivotIntegerBruteForce : FindPivotInteger {
    override fun invoke(num: Int): Int {
        // Iterate through possible pivot values
        for (i in 1..num) {
            var sumLeft = 0
            var sumRight = 0

            // Calculate the sum of elements on the left side of the pivot
            for (j in 1..i) {
                sumLeft += j
            }

            // Calculate the sum of elements on the right side of the pivot
            for (k in i..num) {
                sumRight += k
            }

            // Check if the sums on both sides are equal
            if (sumLeft == sumRight) {
                return i // Return the pivot value if found
            }
        }

        return -1 // Return -1 if no pivot is found
    }
}

class FindPivotIntegerTwoPointer : FindPivotInteger {
    override fun invoke(num: Int): Int {
        var leftValue = 1
        var rightValue: Int = num
        var sumLeft = leftValue
        var sumRight = rightValue

        if (num == 1) return num

        // Iterate until the pointers meet
        while (leftValue < rightValue) {
            // Adjust sums and pointers based on comparison
            if (sumLeft < sumRight) {
                sumLeft += ++leftValue
            } else {
                sumRight += --rightValue
            }

            // Check for pivot condition
            if (sumLeft == sumRight && leftValue + 1 == rightValue - 1) {
                return leftValue + 1
            }
        }

        return -1 // Return -1 if no pivot is found
    }
}

class FindPivotIntegerBS : FindPivotInteger {
    override fun invoke(num: Int): Int {
        // Initialize left and right pointers for binary search
        var left = 1
        var right: Int = num

        // Calculate the total sum of the sequence
        val totalSum: Int = num * (num + 1) / 2

        // Binary search for the pivot point
        while (left < right) {
            // Calculate the mid-point
            val mid = (left + right) / 2

            // Check if the difference between the square of mid and the total sum is negative
            if (mid * mid - totalSum < 0) {
                left = mid + 1 // Adjust the left bound if the sum is smaller
            } else {
                right = mid // Adjust the right bound if the sum is equal or greater
            }
        }

        // Check if the square of the left pointer minus the total sum is zero
        return if (left * left - totalSum == 0) {
            left
        } else {
            -1
        }
    }
}

class FindPivotIntegerLookupTable : FindPivotInteger {
    private val precompute: IntArray = IntArray(MAX_VALUE + 1)

    override fun invoke(num: Int): Int {
        for (i in 0..MAX_VALUE) {
            precompute[i] = 0
        }

        // Check if precompute array is not initialized
        if (precompute[1] == 0) {
            var i = 1
            var j = 1
            while (i <= MAX_VALUE) {
                val sum = i * (i + 1) / 2

                // Find the first square greater than or equal to sum
                while (j * j < sum) {
                    ++j
                }

                // Check if j * j is equal to sum (pivot found), otherwise set to -1
                precompute[i] = if (j * j == sum) j else -1
                ++i
            }
        }

        return precompute[num]
    }

    companion object {
        private const val MAX_VALUE: Int = 1000
    }
}

class FindPivotIntegerMath : FindPivotInteger {
    override fun invoke(num: Int): Int {
        val sum: Int = (num * (num + 1) / 2)
        val pivot = sqrt(sum.toDouble()).toInt()
        // If pivot * pivot is equal to sum (pivot found) return pivot, else return -1
        return if (pivot * pivot == sum) pivot else -1
    }
}
