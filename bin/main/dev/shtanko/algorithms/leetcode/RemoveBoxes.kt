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
 * 546. Remove Boxes
 * @see <a href="https://leetcode.com/problems/remove-boxes/">Source</a>
 */
fun interface RemoveBoxes {
    operator fun invoke(boxes: IntArray): Int
}

class RemoveBoxesTopDown : RemoveBoxes {
    override operator fun invoke(boxes: IntArray): Int {
        val n: Int = boxes.size
        val dp = Array(n) {
            Array(n) { IntArray(n) }
        }
        return removeBoxesSub(boxes, 0, n - 1, 0, dp)
    }

    private fun removeBoxesSub(boxes: IntArray, i: Int, j: Int, k: Int, dp: Array<Array<IntArray>>): Int {
        var i0 = i
        var k0 = k
        if (i0 > j) return 0
        if (dp[i0][j][k0] > 0) return dp[i0][j][k0]
        while (i0 + 1 <= j && boxes[i0 + 1] == boxes[i0]) {
            // optimization: all boxes of the same color counted continuously from the first box should be
            // grouped together
            i0++
            k0++
        }
        var res = (k0 + 1) * (k0 + 1) + removeBoxesSub(boxes, i0 + 1, j, 0, dp)
        for (m in i0 + 1..j) {
            if (boxes[i0] == boxes[m]) {
                res = max(
                    res,
                    removeBoxesSub(boxes, i0 + 1, m - 1, 0, dp).plus(
                        removeBoxesSub(boxes, m, j, k0 + 1, dp),
                    ),
                )
            }
        }
        dp[i][j][k] = res // When updating the dp matrix, we should use the initial values of i, j and k
        return res
    }
}

class RemoveBoxesBottomUp : RemoveBoxes {
    override operator fun invoke(boxes: IntArray): Int {
        val n: Int = boxes.size
        val dp = Array(n) {
            Array(n) { IntArray(n) }
        }

        for (j in 0 until n) {
            for (k in 0..j) {
                dp[j][j][k] = (k + 1) * (k + 1)
            }
        }

        for (l in 1 until n) {
            for (j in l until n) {
                val i = j - l
                for (k in 0..i) {
                    var res = (k + 1) * (k + 1) + dp[i + 1][j][0]
                    for (m in i + 1..j) {
                        if (boxes[m] == boxes[i]) {
                            res = max(res, dp[i + 1][m - 1][0] + dp[m][j][k + 1])
                        }
                    }
                    dp[i][j][k] = res
                }
            }
        }

        return if (n == 0) 0 else dp[0][n - 1][0]
    }
}
