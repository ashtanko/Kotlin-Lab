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

import kotlin.math.max

/**
 * Uncrossed Lines
 * @see <a href="https://leetcode.com/problems/uncrossed-lines/">Source</a>
 */
fun interface UncrossedLines {
    fun maxUncrossedLines(nums1: IntArray, nums2: IntArray): Int
}

/**
 * Approach 1: Recursive Dynamic Programming
 */
class UncrossedLinesRecursiveDP : UncrossedLines {
    override fun maxUncrossedLines(nums1: IntArray, nums2: IntArray): Int {
        val sizeOfArray1: Int = nums1.size
        val sizeOfArray2: Int = nums2.size

        val memoizationTable = Array(sizeOfArray1 + 1) { IntArray(sizeOfArray2 + 1) { -1 } }
        return calculateMaxUncrossedLines(sizeOfArray1, sizeOfArray2, nums1, nums2, memoizationTable)
    }

    private fun calculateMaxUncrossedLines(
        index1: Int,
        index2: Int,
        array1: IntArray,
        array2: IntArray,
        memoizationTable: Array<IntArray>,
    ): Int {
        if (index1 <= 0 || index2 <= 0) {
            return 0
        }
        if (memoizationTable[index1][index2] != -1) {
            return memoizationTable[index1][index2]
        }
        if (array1[index1 - 1] == array2[index2 - 1]) {
            memoizationTable[index1][index2] =
                1 + calculateMaxUncrossedLines(index1 - 1, index2 - 1, array1, array2, memoizationTable)
        } else {
            memoizationTable[index1][index2] = max(
                calculateMaxUncrossedLines(index1, index2 - 1, array1, array2, memoizationTable),
                calculateMaxUncrossedLines(index1 - 1, index2, array1, array2, memoizationTable),
            )
        }
        return memoizationTable[index1][index2]
    }
}

/**
 * Approach 2: Iterative Dynamic Programming
 */
class UncrossedLinesIterativeDP : UncrossedLines {
    override fun maxUncrossedLines(nums1: IntArray, nums2: IntArray): Int {
        val sizeOfArray1: Int = nums1.size
        val sizeOfArray2: Int = nums2.size

        val dpTable = Array(sizeOfArray1 + 1) { IntArray(sizeOfArray2 + 1) }

        for (index1 in 1..sizeOfArray1) {
            for (index2 in 1..sizeOfArray2) {
                if (nums1[index1 - 1] == nums2[index2 - 1]) {
                    dpTable[index1][index2] = 1 + dpTable[index1 - 1][index2 - 1]
                } else {
                    dpTable[index1][index2] =
                        max(dpTable[index1][index2 - 1], dpTable[index1 - 1][index2])
                }
            }
        }

        return dpTable[sizeOfArray1][sizeOfArray2]
    }
}
