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

fun interface AbstractRobber {
    operator fun invoke(arr: IntArray): Int
}

/**
 * Figure out recursive relation.
 * A robber has 2 options: a) rob current house i; b) don't rob current house.
 * If an option "a" is selected it means she can't rob previous i-1 house but can safely proceed to the one before
 * previous i-2 and gets all cumulative loot that follows.
 * If an option "b" is selected the robber gets all the possible loot from robbery of i-1 and all the
 * following buildings.
 * So it boils down to calculating what is more profitable:
 *  - robbery of current house + loot from houses before the previous
 *  - loot from the previous house robbery and any loot captured before that
 *
 *  Recursive (top-down)
 */
class RecursiveRobber : AbstractRobber {
    override operator fun invoke(arr: IntArray): Int {
        return arr.rob(arr.size - 1)
    }

    private fun IntArray.rob(i: Int): Int {
        if (i < 0) return 0
        return kotlin.math.max(this.rob(i - 2) + this[i], this.rob(i - 1))
    }
}

/**
 * Recursive + memo (top-down).
 * Much better, this should run in O(n) time. Space complexity is O(n) as well, because of the recursion stack
 */
class RecursiveRobberMemo : AbstractRobber {

    private lateinit var memo: IntArray

    override operator fun invoke(arr: IntArray): Int {
        memo = IntArray(arr.size + 1) { -1 }
        return arr.rob(arr.size - 1)
    }

    private fun IntArray.rob(i: Int): Int {
        if (i < 0) {
            return 0
        }

        if (memo[i] >= 0) {
            return memo[i]
        }

        val result = kotlin.math.max(this.rob(i - 2) + this[i], this.rob(i - 1))
        memo[i] = result
        return result
    }
}

/**
 * Iterative + memo (bottom-up)
 */
class IterativeRobberMemo : AbstractRobber {
    override operator fun invoke(arr: IntArray): Int {
        if (arr.isEmpty()) return 0
        val memo = IntArray(arr.size + 1)
        memo[0] = 0
        memo[1] = arr[0]

        for (i in 1 until arr.size) {
            val value = arr[i]
            memo[i + 1] = kotlin.math.max(memo[i], memo[i - 1] + value)
        }
        return memo[arr.size]
    }
}

/**
 * Iterative + 2 variables (bottom-up)
 *
 * We can notice that in the previous step we use only memo[i] and memo[i-1], so going just 2 steps back.
 * We can hold them in 2 variables instead. This optimization is met in Fibonacci sequence
 * creation and some other problems
 */
class IterativeRobber : AbstractRobber {
    override operator fun invoke(arr: IntArray): Int {
        if (arr.isEmpty()) return 0

        var prev1 = 0
        var prev2 = 0

        for (num in arr) {
            val tmp = prev1
            prev1 = kotlin.math.max(prev2 + num, prev1)
            prev2 = tmp
        }

        return prev1
    }
}
