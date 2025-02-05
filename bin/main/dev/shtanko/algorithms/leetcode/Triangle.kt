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

import kotlin.math.min

/**
 * Triangle
 * @see <a href="https://leetcode.com/problems/triangle/">Source</a>
 */
fun interface Triangle {
    operator fun invoke(triangle: List<List<Int>>): Int
}

/**
 * Approach 1: Dynamic Programming (Bottom-up: In-place)
 * Time Complexity: O(n^2).
 * Space Complexity: O(1).
 */
class TriangleBottomUp : Triangle {
    override operator fun invoke(triangle: List<List<Int>>): Int {
        val result = triangle.toMutableList().map { it.toMutableList() }
        for (row in 1 until result.size) {
            for (col in 0..row) {
                var smallestAbove = Int.MAX_VALUE
                if (col > 0) {
                    smallestAbove = result[row - 1][col - 1]
                }
                if (col < row) {
                    smallestAbove = min(smallestAbove, result[row - 1][col])
                }
                val path = smallestAbove + result[row][col]
                result[row][col] = path
            }
        }
        return result[result.size - 1].minOrNull() ?: -1
    }
}

/**
 * Approach 2: Dynamic Programming (Bottom-up: Auxiliary Space)
 * Time Complexity: O(n^2).
 * Space Complexity: O(n).
 */
class TriangleAuxiliarySpace : Triangle {
    override operator fun invoke(triangle: List<List<Int>>): Int {
        var prevRow = triangle[0]
        for (row in 1 until triangle.size) {
            val currRow: MutableList<Int> = ArrayList()
            for (col in 0..row) {
                var smallestAbove = Int.MAX_VALUE
                if (col > 0) {
                    smallestAbove = prevRow[col - 1]
                }
                if (col < row) {
                    smallestAbove = min(smallestAbove, prevRow[col])
                }
                currRow.add(smallestAbove + triangle[row][col])
            }
            prevRow = currRow
        }
        return prevRow.minOrNull() ?: -1
    }
}

/**
 * Approach 3: Dynamic Programming (Bottom-up: Flip Triangle Upside Down)
 */
class TriangleUpsideDown : Triangle {
    override operator fun invoke(triangle: List<List<Int>>): Int {
        var belowRow = triangle[triangle.size - 1]
        for (row in triangle.size - 2 downTo 0) {
            val currRow: MutableList<Int> = ArrayList()
            for (col in 0..row) {
                val bestBelow = min(belowRow[col], belowRow[col + 1])
                currRow.add(bestBelow + triangle[row][col])
            }
            belowRow = currRow
        }
        return belowRow[0]
    }
}

/**
 * Approach 4: Memoization (Top-Down)
 * Time Complexity: O(n^2).
 * Space Complexity: O(n^2).
 */
class TriangleMemoization : Triangle {
    private var memoTable: MutableMap<String, Int> = HashMap()
    private lateinit var triangle: List<List<Int>>

    override operator fun invoke(triangle: List<List<Int>>): Int {
        this.triangle = triangle
        return minPath(0, 0)
    }

    private fun minPath(row: Int, col: Int): Int {
        val params = "$row:$col"
        if (memoTable.containsKey(params)) {
            return memoTable[params] ?: -1
        }
        var path = triangle[row][col]
        if (row < triangle.size - 1) {
            path += min(minPath(row + 1, col), minPath(row + 1, col + 1))
        }
        memoTable[params] = path
        return path
    }
}
