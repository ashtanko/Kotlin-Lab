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

import java.util.TreeSet

fun maxSumSubMatrix(matrix: Array<IntArray>, target: Int): Int {
    val row: Int = matrix.size
    if (row == 0) return 0
    val col: Int = matrix[0].size
    val m = row.coerceAtMost(col)
    val n = row.coerceAtLeast(col)
    val colIsBig = col > row
    var res = Int.MIN_VALUE
    for (i in 0 until m) {
        val array = IntArray(n)
        for (j in i downTo 0) {
            var value = 0
            val set: TreeSet<Int> = TreeSet()
            set.add(0)
            for (k in 0 until n) {
                array[k] = array[k] + if (colIsBig) matrix[j][k] else matrix[k][j]
                value += array[k]
                val subRes: Int? = set.ceiling(value - target)
                if (null != subRes) {
                    res = res.coerceAtLeast(value - subRes)
                }
                set.add(value)
            }
        }
    }
    return res
}
