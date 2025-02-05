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

import dev.shtanko.utils.Quadruple

/**
 * 786. K-th Smallest Prime Fraction
 * @see <a href="https://leetcode.com/problems/k-th-smallest-prime-fraction">Source</a>
 */
fun interface KthSmallestPrimeFraction {
    operator fun invoke(arr: IntArray, k: Int): IntArray
}

class KthSmallestPrimeFractionBS : KthSmallestPrimeFraction {
    override fun invoke(arr: IntArray, k: Int): IntArray {
        val n: Int = arr.size
        var left = 0.0
        var right = 1.0
        while (left < right) {
            val mid = (left + right) / 2
            val (totalSmallerFractions, _, numeratorIdx, denominatorIdx) = calculateFractions(arr, n, mid)

            if (totalSmallerFractions == k) {
                return intArrayOf(arr[numeratorIdx], arr[denominatorIdx])
            } else if (totalSmallerFractions > k) {
                right = mid
            } else {
                left = mid
            }
        }
        return intArrayOf()
    }

    private fun calculateFractions(arr: IntArray, n: Int, mid: Double): Quadruple<Int, Double, Int, Int> {
        var maxFraction = 0.0
        var totalSmallerFractions = 0
        var numeratorIdx = 0
        var denominatorIdx = 0
        var j = 1

        for (i in 0 until n - 1) {
            while (j < n && arr[i] >= mid * arr[j]) {
                j++
            }
            totalSmallerFractions += (n - j)
            if (j == n) break
            val fraction = arr[i].toDouble() / arr[j]
            if (fraction > maxFraction) {
                numeratorIdx = i
                denominatorIdx = j
                maxFraction = fraction
            }
        }

        return Quadruple(totalSmallerFractions, maxFraction, numeratorIdx, denominatorIdx)
    }
}
