/*
 * Copyright 2022 Oleksii Shtanko
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

import kotlin.math.min

/**
 * 927. Three Equal Parts
 * @see <a href="https://leetcode.com/problems/three-equal-parts/">Source</a>
 */
fun interface ThreeEqualParts {
    operator fun invoke(arr: IntArray): IntArray
}

class EqualOnes : ThreeEqualParts {

    private val impossible = intArrayOf(-1, -1)

    override operator fun invoke(arr: IntArray): IntArray {
        val n = arr.size

        // Count how many 1s are in arr.
        var totalOnes = 0
        for (bit in arr) {
            totalOnes += bit
        }

        // If total number of ones is not evenly divisible by 3, then no solution exists.
        if (totalOnes % 3 != 0) {
            return impossible
        }

        // Otherwise, each part should contain an equal amount of 1s.
        val targetOnes = totalOnes / 3
        if (targetOnes == 0) {
            return intArrayOf(0, n - 1)
        }

        // i1, j1 marks the index of the first and last one in the first block of 1s, etc.
        var i1 = -1
        var j1 = -1
        var i2 = -1
        var j2 = -1
        var i3 = -1
        var j3 = -1

        // Find the index of the first and last 1 in each block of ones.
        var oneCount = 0
        for (i in 0 until n) {
            if (arr[i] == 1) {
                oneCount += 1
                if (oneCount == 1) i1 = i
                if (oneCount == targetOnes) j1 = i
                if (oneCount == targetOnes + 1) i2 = i
                if (oneCount == 2 * targetOnes) j2 = i
                if (oneCount == 2 * targetOnes + 1) i3 = i
                if (oneCount == 3 * targetOnes) j3 = i
            }
        }

        // The array is in the form W [i1, j1] X [i2, j2] Y [i3, j3] Z
        // where each [i, j] is a block of 1s and W, X, Y, and Z represent blocks of 0s.
        val part1: IntArray = arr.copyOfRange(i1, j1 + 1)
        val part2: IntArray = arr.copyOfRange(i2, j2 + 1)
        val part3: IntArray = arr.copyOfRange(i3, j3 + 1)
        if (!part1.contentEquals(part2) || !part1.contentEquals(part3)) {
            return impossible
        }

        // The number of zeros after the left, middle, and right parts
        val trailingZerosLeft = i2 - j1 - 1
        val trailingZerosMid = i3 - j2 - 1
        val trailingZeros = n - j3 - 1
        return if (trailingZeros > min(trailingZerosLeft, trailingZerosMid)) {
            impossible
        } else {
            intArrayOf(j1 + trailingZeros, j2 + trailingZeros + 1)
        }
    }
}

class ThreeEqualPartsSimple : ThreeEqualParts {
    override operator fun invoke(arr: IntArray): IntArray {
        var numOne = 0
        for (i in arr) {
            if (i == 1) numOne++
        }
        val res = intArrayOf(-1, -1)
        if (numOne == 0) return intArrayOf(0, 2) // special case
        if (numOne % 3 != 0) return res
        var thirdPartStartingIndex = 0
        var count = 0
        for (i in arr.indices.reversed()) {
            if (arr[i] == 1 && ++count == numOne / 3) {
                thirdPartStartingIndex = i
                break
            }
        }
        val firstPartEndIndex = findNextEndIndexAndCompare(arr, 0, thirdPartStartingIndex)
        if (firstPartEndIndex < 0) return res
        val secondPartEndIndex = findNextEndIndexAndCompare(arr, firstPartEndIndex + 1, thirdPartStartingIndex)
        return if (secondPartEndIndex < 0) res else intArrayOf(firstPartEndIndex, secondPartEndIndex + 1)
    }

    /** the implementation idea is similar to find last k node in a list
     * in the sense that pacer is a pacer
     * when the pacer reaches the end, the end for the current part has been anchored
     * Note: we also do the comparing for the two parts of interest
     *
     * @param a
     * @param start
     * @param pacer
     * @return
     */
    private fun findNextEndIndexAndCompare(a: IntArray, start: Int, pacer: Int): Int {
        var start0 = start
        var pacer0 = pacer
        while (a[start0] == 0) {
            start0++
        }
        while (pacer0 < a.size) {
            if (a[start0] != a[pacer0]) return -1
            start0++
            pacer0++
        }
        return start0 - 1
    }
}
