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

/**
 * 1137. N-th Tribonacci Number
 * @see <a href="https://leetcode.com/problems/n-th-tribonacci-number/submissions/887948261/">Source</a>
 */
fun interface Tribonacci {
    operator fun invoke(num: Int): Int
}

/**
 * Approach 1: Space Optimisation : Dynamic Programming
 * Time complexity : O(N)
 */
class TribSpaceOptimisationDP : Tribonacci {
    override operator fun invoke(num: Int): Int {
        if (num < 3) return if (num == 0) 0 else 1
        var tmp: Int
        var x = 0
        var y = 1
        var z = 1
        for (i in 3..num) {
            tmp = x + y + z
            x = y
            y = z
            z = tmp
        }
        return z
    }
}

/**
 * Approach 2: Performance Optimisation : Recursion with Memoization
 * Time complexity : O(1)
 */
class TribRecursionMemo : Tribonacci {
    private val cache = IntArray(MAX).apply {
        this[1] = 1
        this[2] = 1
    }

    init {
        helper(MAX - 1)
    }

    override operator fun invoke(num: Int): Int = cache[num]

    private fun helper(k: Int): Int {
        if (k == 0) return 0
        if (cache[k] != 0) return cache[k]
        cache[k] = helper(k - 1) + helper(k - 2) + helper(k - 3)
        return cache[k]
    }

    companion object {
        private const val MAX = 38
    }
}

/**
 * Approach 3: Performance Optimisation : Dynamic Programming
 * Time complexity : O(1)
 */
class TPerformanceOptimisationDP : Tribonacci {
    private val cache = IntArray(N).apply {
        this[1] = 1
        this[2] = 1
    }

    init {
        for (i in 3 until N) {
            cache[i] = cache[i - 1] + cache[i - 2] + cache[i - 3]
        }
    }

    override operator fun invoke(num: Int): Int = cache[num]

    companion object {
        private const val N = 38
    }
}
