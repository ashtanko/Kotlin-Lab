/*
 * Copyright 2023 Oleksii Shtanko
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

/**
 * 1504. Count Submatrices With All Ones
 * @see <a href="https://leetcode.com/problems/count-submatrices-with-all-ones/">Source</a>
 */
fun interface CountSubmatricesWithAllOnes {
    operator fun invoke(mat: Array<IntArray>): Int
}

class CountSubmatricesWithAllOnesStack : CountSubmatricesWithAllOnes {
    override fun invoke(mat: Array<IntArray>): Int {
        val m: Int = mat.size
        val n: Int = mat[0].size

        var res = 0
        for (up in 0 until m) {
            val h = IntArray(n) { 1 }
            for (down in up until m) {
                for (k in 0 until n) h[k] = h[k] and mat[down][k]
                res += countOneRow(h)
            }
        }

        return res
    }

    private fun countOneRow(a: IntArray): Int {
        var res = 0
        var length = 0
        for (i in a.indices) {
            length = if (a[i] == 0) 0 else length + 1
            res += length
        }
        return res
    }
}
