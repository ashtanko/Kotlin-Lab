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

import kotlin.math.max
import kotlin.math.min

/**
 * Time: O(m*n).
 * Space: O(m*n).
 */
object MatrixBlockSum {
    operator fun invoke(mat: Array<IntArray>, k: Int): Array<IntArray> {
        val m: Int = mat.size
        val n: Int = mat.first().size
        val sum = Array(m + 1) { IntArray(n + 1) }

        for (r in 1..m) {
            for (c in 1..n) {
                sum[r][c] = mat[r - 1][c - 1] + sum[r - 1][c] + sum[r][c - 1] - sum[r - 1][c - 1]
            }
        }
        val ans = Array(m) { IntArray(n) }
        for (r in 0 until m) {
            for (c in 0 until n) {
                var r1 = max(0, r - k)
                var c1 = max(0, c - k)
                var r2 = min(m - 1, r + k)
                var c2 = min(n - 1, c + k)
                r1++
                c1++
                r2++
                c2++ // Since `sum` start with 1 so we need to increase r1, c1, r2, c2 by 1
                ans[r][c] = sum[r2][c2] - sum[r2][c1 - 1] - sum[r1 - 1][c2] + sum[r1 - 1][c1 - 1]
            }
        }
        return ans
    }
}
