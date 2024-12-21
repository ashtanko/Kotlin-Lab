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

import dev.shtanko.algorithms.dp.fibonacciAt
import kotlin.math.pow
import kotlin.math.roundToLong
import kotlin.math.sqrt

/**
 * 509. Fibonacci Number
 * @see <a href="https://leetcode.com/problems/fibonacci-number/">Source</a>
 */
fun interface FibonacciStrategy {
    operator fun invoke(n: Int): Long
}

/**
 * Solution 1: Iterative
 */
class FibonacciIterative : FibonacciStrategy {
    override operator fun invoke(n: Int): Long {
        var n1: Long = n.toLong()
        if (n1 <= 1) return n1
        var a = 0
        var b = 1

        while (n1-- > 1) {
            val sum = a + b
            a = b
            b = sum
        }
        return b.toLong()
    }
}

/**
 * Solution 2: Recursive
 */
class FibonacciRecursion : FibonacciStrategy {
    override operator fun invoke(n: Int): Long {
        if (n <= 1) {
            return n.toLong()
        }
        return invoke(n - 1) + invoke(n - 2)
    }
}

class FibonacciOptimizedRecursion : FibonacciStrategy {
    override operator fun invoke(n: Int): Long {
        return fibonacciAt(n)
    }
}

/**
 * Solution 4: Dynamic Programming - Bottom Up Approach
 */
class FibonacciBottomUp : FibonacciStrategy {
    override operator fun invoke(n: Int): Long {
        if (n <= 1) {
            return n.toLong()
        }
        return memoize(n)
    }

    private fun memoize(n: Int): Long {
        val cache = LongArray(n + 1)
        cache[1] = 1
        for (i in 2..n) {
            cache[i] = cache[i - 1] + cache[i - 2]
        }
        return cache[n]
    }
}

/**
 * Solution 3: Dynamic Programming - Top-Down Approach
 */
class FibonacciTopDown : FibonacciStrategy {
    private val cache = arrayOfNulls<Long>(SIZE)

    private companion object {
        private const val SIZE = 60
    }

    override operator fun invoke(n: Int): Long {
        if (n <= 1) {
            return n.toLong()
        }
        cache[0] = 0
        cache[1] = 1
        return memoize(n)
    }

    private fun memoize(n: Int): Long {
        if (cache[n] != null) {
            return cache[n]!!
        }
        cache[n] = memoize(n - 1) + memoize(n - 2)
        return memoize(n)
    }
}

class FibonacciIterativeTopDown : FibonacciStrategy {
    override operator fun invoke(n: Int): Long {
        if (n <= 1) {
            return n.toLong()
        }
        if (n == 2) {
            return 1
        }

        var current = 0
        var prev1 = 1
        var prev2 = 1

        for (i in 3..n) {
            current = prev1 + prev2
            prev2 = prev1
            prev1 = current
        }
        return current.toLong()
    }
}

class FibonacciMatrixExponentiation : FibonacciStrategy {
    override operator fun invoke(n: Int): Long {
        if (n <= 1) {
            return n.toLong()
        }
        val a = arrayOf(intArrayOf(1, 1), intArrayOf(1, 0))
        matrixPower(a, n - 1)

        return a[0][0].toLong()
    }

    private fun matrixPower(arr: Array<IntArray>, n: Int) {
        if (n <= 1) {
            return
        }
        matrixPower(arr, n / 2)
        multiply(arr, arr)
        val b = arrayOf(intArrayOf(1, 1), intArrayOf(1, 0))
        if (n % 2 != 0) {
            multiply(arr, b)
        }
    }

    private fun multiply(matrixA: Array<IntArray>, matrixB: Array<IntArray>) {
        val x = matrixA[0][0] * matrixB[0][0] + matrixA[0][1] * matrixB[1][0]
        val y = matrixA[0][0] * matrixB[0][1] + matrixA[0][1] * matrixB[1][1]
        val z = matrixA[1][0] * matrixB[0][0] + matrixA[1][1] * matrixB[1][0]
        val w = matrixA[1][0] * matrixB[0][1] + matrixA[1][1] * matrixB[1][1]
        matrixA[0][0] = x
        matrixA[0][1] = y
        matrixA[1][0] = z
        matrixA[1][1] = w
    }
}

class FibonacciMath : FibonacciStrategy {

    override operator fun invoke(n: Int): Long {
        val goldenRatio = 1.plus(sqrt(GOLDEN_RATIO_VALUE)) / 2
        return goldenRatio.pow(n).div(sqrt(GOLDEN_RATIO_VALUE)).roundToLong()
    }

    companion object {
        private const val GOLDEN_RATIO_VALUE = 5.0
    }
}
