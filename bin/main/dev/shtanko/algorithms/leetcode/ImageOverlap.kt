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

import kotlin.math.max

/**
 * 835. Image Overlap
 * @see <a href="https://leetcode.com/problems/image-overlap/">Source</a>
 */
fun interface ImageOverlap {
    fun largestOverlap(img1: Array<IntArray>, img2: Array<IntArray>): Int
}

/**
 * Approach 1: Shift and Count
 */
class ImageOverlapShiftAndCount : ImageOverlap {
    override fun largestOverlap(img1: Array<IntArray>, img2: Array<IntArray>): Int {
        var maxOverlaps = 0
        for (yShift in img1.indices) {
            for (xShift in img1.indices) {
                // move the matrix A to the up-right and up-left directions.
                maxOverlaps = max(maxOverlaps, shiftAndCount(xShift, yShift, img1, img2))
                // move the matrix B to the up-right and up-left directions, which is equivalent to moving A to the
                // down-right and down-left directions
                maxOverlaps = max(maxOverlaps, shiftAndCount(xShift, yShift, img2, img1))
            }
        }
        return maxOverlaps
    }

    /**
     * Shift the matrix M in up-left and up-right directions
     * and count the ones in the overlapping zone.
     */
    private fun shiftAndCount(xShift: Int, yShift: Int, m: Array<IntArray>, r: Array<IntArray>): Int {
        var leftShiftCount = 0
        var rightShiftCount = 0
        var rRow = 0
        // count the cells of ones in the overlapping zone.
        for (mRow in yShift until m.size) {
            var rCol = 0
            for (mCol in xShift until m.size) {
                if (m[mRow][mCol] == 1 && m[mRow][mCol] == r[rRow][rCol]) leftShiftCount += 1
                if (m[mRow][rCol] == 1 && m[mRow][rCol] == r[rRow][mCol]) rightShiftCount += 1
                rCol += 1
            }
            rRow += 1
        }
        return max(leftShiftCount, rightShiftCount)
    }
}

/**
 * Approach 2: Linear Transformation
 */
class ImageOverlapLinear : ImageOverlap {
    override fun largestOverlap(img1: Array<IntArray>, img2: Array<IntArray>): Int {
        val onesA = nonZeroCells(img1)
        val onesB = nonZeroCells(img2)
        var maxOverlaps = 0
        val groupCount: HashMap<Pair<Int, Int>, Int> = HashMap()
        for (a in onesA) {
            for (b in onesB) {
                val vec: Pair<Int, Int> = Pair(b.first - a.first, b.second - a.second)
                if (groupCount.containsKey(vec)) {
                    groupCount[vec] = groupCount.getOrDefault(vec, 0) + 1
                } else {
                    groupCount[vec] = 1
                }
                maxOverlaps = max(maxOverlaps, groupCount.getOrDefault(vec, 0))
            }
        }
        return maxOverlaps
    }

    private fun nonZeroCells(m: Array<IntArray>): List<Pair<Int, Int>> {
        val ret: MutableList<Pair<Int, Int>> = ArrayList()
        for (row in m.indices) {
            for (col in m.indices) {
                if (m[row][col] == 1) ret.add(Pair(row, col))
            }
        }
        return ret
    }
}

/**
 * Approach 3: Imagine Convolution
 */
class ImageOverlapImagineConvolution : ImageOverlap {
    override fun largestOverlap(img1: Array<IntArray>, img2: Array<IntArray>): Int {
        val n = img1.size
        val paddedB = Array(3 * n - 2) { IntArray(3 * n - 2) }
        for (row in 0 until n) {
            for (col in 0 until n) {
                paddedB[row + n - 1][col + n - 1] = img2[row][col]
            }
        }
        var maxOverlaps = 0
        for (xShift in 0 until 2 * n - 1) {
            for (yShift in 0 until 2 * n - 1) {
                maxOverlaps = max(
                    maxOverlaps,
                    convolute(img1, paddedB, xShift, yShift),
                )
            }
        }
        return maxOverlaps
    }

    private fun convolute(a: Array<IntArray>, kernel: Array<IntArray>, xShift: Int, yShift: Int): Int {
        var result = 0
        for (row in a.indices) {
            for (col in a.indices) {
                result += a[row][col] * kernel[row + yShift][col + xShift]
            }
        }
        return result
    }
}
