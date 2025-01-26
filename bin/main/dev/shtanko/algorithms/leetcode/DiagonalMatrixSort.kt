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

import java.util.Arrays
import java.util.PriorityQueue
import kotlin.math.min

/**
 * 1329. Sort the Matrix Diagonally
 * @see <a href="https://leetcode.com/problems/sort-the-matrix-diagonally/">Source</a>
 */
fun interface DiagonalMatrixSort {
    fun diagonalSort(mat: Array<IntArray>): Array<IntArray>
}

/**
 * Straight Forward
 */
class DiagonalMatrixSortSF : DiagonalMatrixSort {
    override fun diagonalSort(mat: Array<IntArray>): Array<IntArray> {
        val m: Int = mat.size
        val n: Int = mat[0].size
        var r = m - 1
        var c = 0
        while (r >= 0) {
            fillMatrix(mat, m, n, r, c)
            r--
        }
        r = 0
        c = 1
        while (c < n - 1) {
            fillMatrix(mat, m, n, r, c)
            c++
        }
        return mat
    }

    private fun fillMatrix(mat: Array<IntArray>, m: Int, n: Int, r: Int, c: Int) {
        val arr: MutableList<Int> = ArrayList()
        var i = 0
        while (r + i < m && c + i < n) {
            arr.add(mat[r + i][c + i])
            i++
        }
        i = 0
        arr.sort()
        while (r + i < m && c + i < n) {
            mat[r + i][c + i] = arr[i]
            i++
        }
    }
}

class DiagonalMatrixSortSFArray : DiagonalMatrixSort {
    override fun diagonalSort(mat: Array<IntArray>): Array<IntArray> {
        val n: Int = mat.size
        val m: Int = mat[0].size

        for (i in 0 until n) {
            val arr = IntArray(min(n - i, m))
            var j = 0
            var k = i
            while (k < n && j < m) {
                arr[j] = mat[k][j]
                k++
                j++
            }
            Arrays.sort(arr)
            j = 0
            k = i
            while (k < n && j < m) {
                mat[k][j] = arr[j]
                k++
                j++
            }
        }

        for (j in 1 until m) {
            val arr = IntArray(min(n, m - j))
            var i = 0
            var k = j
            while (k < m && i < n) {
                arr[i] = mat[i][k]
                k++
                i++
            }
            arr.sort()
            i = 0
            k = j
            while (k < m && i < n) {
                mat[i][k] = arr[i]
                k++
                i++
            }
        }
        return mat
    }
}

class DiagonalMatrixSortMap : DiagonalMatrixSort {
    override fun diagonalSort(mat: Array<IntArray>): Array<IntArray> {
        val m: Int = mat.size
        val n: Int = mat[0].size
        val d: HashMap<Int, PriorityQueue<Int>> = HashMap()
        for (i in 0 until m) {
            for (j in 0 until n) {
                d.putIfAbsent(i - j, PriorityQueue())
                d[i - j]?.add(mat[i][j])
            }
        }
        for (i in 0 until m) {
            for (j in 0 until n) {
                d[i - j]?.let {
                    mat[i][j] = it.poll()
                }
            }
        }
        return mat
    }
}
