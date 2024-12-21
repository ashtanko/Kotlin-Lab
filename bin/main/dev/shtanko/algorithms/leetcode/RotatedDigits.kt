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

import dev.shtanko.algorithms.DECIMAL

/**
 * Rotated Digits
 * @see <a href="https://leetcode.com/problems/rotated-digits/">Source</a>
 */
fun interface RotatedDigits {
    operator fun invoke(n: Int): Int
}

class RotatedDigitsBruteForce : RotatedDigits {
    override operator fun invoke(n: Int): Int {
        // Count how many n in [1, N] are good.
        var ans = 0
        for (i in 1..n) if (good(i, false)) ans++
        return ans
    }

    // Return true if n is good.
    // The flag is true iff we have an occurrence of 2, 5, 6, 9.
    private fun good(n: Int, flag: Boolean): Boolean {
        if (n == 0) return flag
        val d = n % DECIMAL
        if (invalidRotation.contains(d)) return false
        return if (validRotation.contains(d)) good(n / DECIMAL, flag) else good(n / DECIMAL, true)
    }

    companion object {
        private val invalidRotation = listOf(3, 4, 7)
        private val validRotation = listOf(0, 1, 8)
    }
}

class RotatedDigitsDP : RotatedDigits {
    override operator fun invoke(n: Int): Int {
        val a = n.toString().toCharArray()
        val k = a.size
        val memo = Array(k + 1) { Array(2) { IntArray(2) } }
        memo[k][0][1] = 1.also { memo[k][1][1] = it }
        for (i in k - 1 downTo 0) {
            for (eqf in 0..1) for (invf in 0..1) {
                var ans = 0
                var d = '0'
                while (d <= if (eqf == 1) a[i] else '9') {
                    if (d == '3' || d == '4' || d == '7') {
                        ++d
                        continue
                    }
                    val invo = d == '2' || d == '5' || d == '6' || d == '9'
                    ans += memo[i + 1][if (d == a[i]) eqf else 0][if (invo) 1 else invf]
                    ++d
                }
                memo[i][eqf][invf] = ans
            }
        }
        return memo[0][1][0]
    }
}
