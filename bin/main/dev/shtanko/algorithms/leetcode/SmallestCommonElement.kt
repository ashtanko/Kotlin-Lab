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

import java.util.Arrays.binarySearch
import kotlin.math.min

private const val LIMIT = 10000
private const val ARRAY_SIZE = LIMIT + 1
private const val NO_SOLUTION = -1 // returns when solution not found

/**
 * Find Smallest Common Element in All Rows
 * @see <a href="https://leetcode.com/problems/find-smallest-common-element-in-all-rows/">Source</a>
 */
fun interface SmallestCommonElement {
    operator fun invoke(mat: Array<IntArray>): Int
}

/**
 * Approach 1: Count Elements
 * Time complexity: O(nm), where nn and mm are the number of rows and columns.
 * Space complexity: O(k), where k is the number of unique elements.
 */
class SCECountElements : SmallestCommonElement {
    override operator fun invoke(mat: Array<IntArray>): Int {
        val count = IntArray(ARRAY_SIZE)
        val n: Int = mat.size
        val m: Int = mat[0].size
        for (i in 0 until n) {
            for (j in 0 until m) {
                ++count[mat[i][j]]
            }
        }
        for (k in 1..LIMIT) {
            if (count[k] == n) {
                return k
            }
        }
        return NO_SOLUTION
    }
}

/**
 * Approach 1: Count Elements Improved
 * Time complexity: O(nm), where nn and mm are the number of rows and columns.
 * Space complexity: O(1).
 */
class SCECountElementsImproved : SmallestCommonElement {
    override operator fun invoke(mat: Array<IntArray>): Int {
        val count = IntArray(ARRAY_SIZE)
        val n: Int = mat.size
        val m: Int = mat[0].size
        for (j in 0 until m) {
            for (i in 0 until n) {
                if (++count[mat[i][j]] == n) {
                    return mat[i][j]
                }
            }
        }
        return NO_SOLUTION
    }
}

/**
 * Approach 2: Binary Search
 * Time complexity: O(mn log m).
 * Space complexity: O(1).
 */
class SCEBinarySearch : SmallestCommonElement {
    override operator fun invoke(mat: Array<IntArray>): Int {
        val n: Int = mat.size
        val m: Int = mat[0].size
        for (j in 0 until m) {
            var found = true
            var i = 1
            while (i < n && found) {
                found = binarySearch(mat[i], mat[0][j]) >= 0
                ++i
            }
            if (found) {
                return mat[0][j]
            }
        }
        return NO_SOLUTION
    }
}

/**
 * Approach 2: Binary Search Improved
 * Time complexity: O(mn log m).
 * Space complexity: O(n).
 */
class SCEBinarySearchImproved : SmallestCommonElement {
    override operator fun invoke(mat: Array<IntArray>): Int {
        val n: Int = mat.size
        val m: Int = mat[0].size
        val pos = IntArray(n)
        for (j in 0 until m) {
            var found = true
            var i = 1
            while (i < n && found) {
                pos[i] = binarySearch(mat[i], pos[i], m, mat[0][j])
                if (pos[i] < 0) {
                    found = false
                    pos[i] = -pos[i] - 1
                    if (pos[i] >= m) {
                        return NO_SOLUTION
                    }
                }
                ++i
            }
            if (found) {
                return mat[0][j]
            }
        }
        return NO_SOLUTION
    }
}

/**
 * Approach 3: Row Positions
 * Time complexity: O(nm).
 * Space complexity: O(n).
 */
class SCERowPositions : SmallestCommonElement {
    override operator fun invoke(mat: Array<IntArray>): Int {
        val n: Int = mat.size
        val m: Int = mat[0].size
        val pos = IntArray(n)
        var curMax = 0
        var cnt = 0
        while (true) {
            for (i in 0 until n) {
                while (pos[i] < m && mat[i][pos[i]] < curMax) {
                    ++pos[i]
                }
                if (pos[i] >= m) {
                    return NO_SOLUTION
                }
                if (mat[i][pos[i]] != curMax) {
                    cnt = 1
                    curMax = mat[i][pos[i]]
                } else if (++cnt == n) {
                    return curMax
                }
            }
        }
    }
}

/**
 * Approach 3: Row Positions Improved
 * Time complexity: O(nm).
 * Space complexity: O(n).
 */
class SCERowPositionsImproved : SmallestCommonElement {
    override operator fun invoke(mat: Array<IntArray>): Int {
        val n: Int = mat.size
        val m: Int = mat[0].size
        val pos = IntArray(n)
        var curMax = 0
        var cnt = 0
        while (true) {
            for (i in 0 until n) {
                pos[i] = metaSearch(mat[i], pos[i], curMax)
                if (pos[i] >= m) {
                    return NO_SOLUTION
                }
                if (mat[i][pos[i]] != curMax) {
                    cnt = 1
                    curMax = mat[i][pos[i]]
                } else if (++cnt == n) {
                    return curMax
                }
            }
        }
    }

    private fun metaSearch(row: IntArray, pos: Int, value: Int): Int {
        var p = pos
        val sz = row.size
        var d = 1
        while (p < sz && row[p] < value) {
            d = d shl 1
            if (row[min(p + d, sz - 1)] >= value) {
                d = 1
            }
            p += d
        }
        return p
    }
}
