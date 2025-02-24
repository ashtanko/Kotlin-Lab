/*
 * Copyright 2021 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.BruteForce
import dev.shtanko.algorithms.annotations.BruteForceOptimized
import dev.shtanko.algorithms.annotations.DP
import dev.shtanko.algorithms.annotations.Math
import dev.shtanko.algorithms.annotations.Recursive
import dev.shtanko.algorithms.annotations.level.Medium
import dev.shtanko.algorithms.complexity.RuntimeComplexity
import dev.shtanko.algorithms.complexity.SpaceComplexity

/**
 * Arithmetic Slices
 * @see <a href="https://leetcode.com/problems/arithmetic-slices/">Source</a>
 */
@Medium("https://leetcode.com/problems/arithmetic-slices")
fun interface ArithmeticSlices {
    operator fun invoke(arr: IntArray): Int
}

/**
 * Approach 1: Brute Force
 * Time complexity : O(n^3).
 * Space complexity : O(1).
 */
@RuntimeComplexity(short = "O(n^3)")
@SpaceComplexity(short = "O(1)")
@BruteForce
class ArSlicesBruteForce : ArithmeticSlices {
    override fun invoke(arr: IntArray): Int {
        var count = 0
        for (start in 0 until arr.size - 2) {
            val difference: Int = arr[start + 1] - arr[start]
            for (end in start + 2 until arr.size) {
                var index: Int = start + 1
                while (index <= end) {
                    if (arr[index] - arr[index - 1] != difference) break
                    index++
                }
                if (index > end) count++
            }
        }
        return count
    }
}

/**
 * Approach 2: Better Brute Force
 * Time complexity : O(n^2).
 * Space complexity : O(1).
 */
@RuntimeComplexity(short = "O(n^2)")
@SpaceComplexity(short = "O(1)")
@BruteForceOptimized
class ArSlicesBetterBruteForce : ArithmeticSlices {
    override fun invoke(arr: IntArray): Int {
        var count = 0
        for (start in 0 until arr.size - 2) {
            val difference: Int = arr[start + 1] - arr[start]
            for (end in start + 2 until arr.size) {
                if (arr[end] - arr[end - 1] == difference) count++ else break
            }
        }
        return count
    }
}

/**
 * Approach 3: Using Recursion
 * Time complexity : O(n).
 * Space complexity : O(n).
 */
@Recursive
class ArSlicesRecursion : ArithmeticSlices {
    private var totalSum = 0
    override fun invoke(array: IntArray): Int {
        calculateSlices(array, array.size - 1)
        return totalSum
    }

    private fun calculateSlices(array: IntArray, index: Int): Int {
        if (index < 2) return 0
        var arithmeticProgression = 0
        if (array[index] - array[index - 1] == array[index - 1] - array[index - 2]) {
            arithmeticProgression = 1 + calculateSlices(array, index - 1)
            totalSum += arithmeticProgression
        } else {
            calculateSlices(array, index - 1)
        }
        return arithmeticProgression
    }
}

/**
 * Approach 4: Dynamic Programming
 * Time complexity : O(n).
 * Space complexity : O(n).
 */
@DP
class ArSlicesDP : ArithmeticSlices {
    override fun invoke(arr: IntArray): Int {
        val dp = IntArray(arr.size)
        var sum = 0
        for (i in 2 until dp.size) {
            if (arr[i] - arr[i - 1] == arr[i - 1] - arr[i - 2]) {
                dp[i] = 1 + dp[i - 1]
                sum += dp[i]
            }
        }
        return sum
    }
}

/**
 * Approach 5: Constant Space Dynamic Programming
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
@DP("Constant Space DP")
class ArSlicesConstantSpaceDP : ArithmeticSlices {
    override fun invoke(arr: IntArray): Int {
        var dp = 0
        var sum = 0
        for (i in 2 until arr.size) {
            if (arr[i] - arr[i - 1] == arr[i - 1] - arr[i - 2]) {
                dp += 1
                sum += dp
            } else {
                dp = 0
            }
        }
        return sum
    }
}

/**
 * Approach 6: Using Formula
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
@Math
class ArSlicesFormula : ArithmeticSlices {
    override fun invoke(arr: IntArray): Int {
        var count = 0
        var sum = 0
        for (i in 2 until arr.size) {
            if (arr[i] - arr[i - 1] == arr[i - 1] - arr[i - 2]) {
                count++
            } else {
                sum += (count + 1) * count / 2
                count = 0
            }
        }
        return count * (count + 1) / 2.let { sum += it; sum }
    }
}
