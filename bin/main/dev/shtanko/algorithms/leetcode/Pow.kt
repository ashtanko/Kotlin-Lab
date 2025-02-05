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
 * 50. Pow(x, n)
 * @see <a href="https://leetcode.com/problems/powx-n/">Source</a>
 */
fun interface Pow {
    operator fun invoke(x: Double, n: Int): Double
}

/**
 * Approach 1: Brute Force
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
class PowBruteForce : Pow {
    override operator fun invoke(x: Double, n: Int): Double {
        var a = n.toLong()
        var b = x
        if (a < 0) {
            b = 1 / x
            a = -a
        }
        var ans = 1.0
        for (i in 0 until a) {
            ans *= b
        }
        return ans
    }
}

/**
 * Approach 2: Fast Power Algorithm Recursive
 * Time complexity : O(log n).
 * Space complexity : O(log n).
 */
class PowFastRecursive : Pow {
    override operator fun invoke(x: Double, n: Int): Double {
        var a = n.toLong()
        var b = x
        if (a < 0) {
            b = 1 / x
            a = -a
        }

        return fastPow(b, a)
    }

    private fun fastPow(x: Double, n: Long): Double {
        if (n == 0L) {
            return 1.0
        }
        val half = fastPow(x, n / 2)
        return if (n % 2 == 0L) {
            half * half
        } else {
            half * half * x
        }
    }
}

/**
 * Approach 3: Fast Power Algorithm Iterative
 * Time complexity : O(log n).
 * Space complexity : O(1).
 */
class PowFastIterative : Pow {
    override operator fun invoke(x: Double, n: Int): Double {
        var a = n.toLong()
        var b = x
        if (a < 0) {
            b = 1 / x
            a = -a
        }

        var ans = 1.0
        var currentProduct = b

        var i: Long = a
        while (i > 0) {
            if (i % 2 == 1L) {
                ans *= currentProduct
            }
            currentProduct *= currentProduct
            i /= 2
        }

        return ans
    }
}
