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

import dev.shtanko.algorithms.annotations.Backtracking
import dev.shtanko.algorithms.annotations.BottomUpDP
import dev.shtanko.algorithms.annotations.Math
import dev.shtanko.algorithms.annotations.TopDownDP

/**
 * 650. 2 Keys Keyboard
 * @see <a href="https://leetcode.com/problems/2-keys-keyboard/">2 Keys Keyboard</a>
 */
fun interface TwoKeysKeyboard {
    operator fun invoke(num: Int): Int
}

/**
 * Approach 1: Backtracking
 * Time Complexity: O(2^n)
 * Space Complexity: O(n)
 */
@Backtracking
class TwoKeysKeyboardBacktracking : TwoKeysKeyboard {
    private var targetLength: Int = 0

    override fun invoke(num: Int): Int {
        if (num == 1) return 0
        targetLength = num
        // First step is always a Copy All operation
        return 1 + minStepsHelper(1, 1)
    }

    private fun minStepsHelper(currLen: Int, pasteLen: Int): Int {
        // Base case: reached n A's, don't need more operations
        if (currLen == targetLength) return 0
        // Base case: exceeded n A's, not a valid sequence, so return a large value
        if (currLen > targetLength) return 1000

        // Option 1: Copy all + paste
        val opt1 = 2 + minStepsHelper(currLen * 2, currLen)
        // Option 2: Paste
        val opt2 = 1 + minStepsHelper(currLen + pasteLen, pasteLen)

        return minOf(opt1, opt2)
    }
}

/**
 * Approach 2: Top-Down Dynamic Programming
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2)
 */
@TopDownDP
class TwoKeysKeyboardTopDownDp : TwoKeysKeyboard {
    private var targetLength: Int = 0

    override fun invoke(num: Int): Int {
        if (num == 1) return 0
        targetLength = num

        val memo = Array(num + 1) { IntArray(num / 2 + 1) }
        return 1 + minStepsHelper(1, 1, memo)
    }

    private fun minStepsHelper(currLen: Int, pasteLen: Int, memo: Array<IntArray>): Int {
        if (currLen == targetLength) return 0
        if (currLen > targetLength) return 1000

        // Return result if it has been calculated already
        if (memo[currLen][pasteLen] != 0) return memo[currLen][pasteLen]

        val opt1 = 1 + minStepsHelper(currLen + pasteLen, pasteLen, memo)
        val opt2 = 2 + minStepsHelper(currLen * 2, currLen, memo)
        memo[currLen][pasteLen] = minOf(opt1, opt2)
        return memo[currLen][pasteLen]
    }
}

/**
 * Approach 3: Bottom-Up Dynamic Programming
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 */
@BottomUpDP
class TwoKeysKeyboardBottomUpDp : TwoKeysKeyboard {
    override fun invoke(num: Int): Int {
        val dp = IntArray(num + 1) { DEFAULT_VALUE }
        // Base case
        dp[1] = 0
        for (i in 2..num) {
            for (j in 1..i / 2) {
                if (i % j == 0) {
                    dp[i] = minOf(dp[i], dp[j] + i / j)
                }
            }
        }

        return dp[num]
    }

    companion object {
        private const val DEFAULT_VALUE = 1000
    }
}

/**
 * Approach 4: Prime Factorization
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
@Math
class TwoKeysKeyboardMath : TwoKeysKeyboard {
    override fun invoke(num: Int): Int {
        var number = num
        var steps = 0
        var divisor = 2
        while (number > 1) {
            // If divisor is a prime factor, keep dividing
            // number by divisor until it is no longer divisible
            while (number % divisor == 0) {
                steps += divisor
                number /= divisor
            }
            divisor++
        }
        return steps
    }
}
