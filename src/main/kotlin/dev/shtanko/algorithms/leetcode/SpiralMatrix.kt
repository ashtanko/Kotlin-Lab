package dev.shtanko.algorithms.leetcode

import java.util.ArrayList

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 */
fun Array<IntArray>.spiralOrder(): List<Int> {

    val res: MutableList<Int> = ArrayList()
    if (this.isEmpty()) {
        return res
    }
    var rowBegin = 0
    var rowEnd = this.size - 1
    var colBegin = 0
    var colEnd: Int = this[0].size - 1
    while (rowBegin <= rowEnd && colBegin <= colEnd) {
        // Traverse Right
        for (j in colBegin..colEnd) {
            res.add(this[rowBegin][j])
        }
        rowBegin++

        // Traverse Down
        for (j in rowBegin..rowEnd) {
            res.add(this[j][colEnd])
        }
        colEnd--
        if (rowBegin <= rowEnd) {
            // Traverse Left
            for (j in colEnd downTo colBegin) {
                res.add(this[rowEnd][j])
            }
        }
        rowEnd--
        if (colBegin <= colEnd) {
            // Traver Up
            for (j in rowEnd downTo rowBegin) {
                res.add(this[j][colBegin])
            }
        }
        colBegin++
    }
    return res
}

/**
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 */
fun Int.generateSpiralMatrix(): Array<IntArray> {
    val res = Array(this) { IntArray(this) }
    var current = 1
    var rowBegin = 0
    var rowEnd = this - 1
    var colBegin = 0
    var colEnd: Int = this - 1

    while (current <= this * this) {
        for (j in colBegin..colEnd) {
            res[rowBegin][j] = current++
        }
        rowBegin++

        for (i in rowBegin..rowEnd) {
            res[i][colEnd] = current++
        }
        colEnd--
        for (j in colEnd downTo colBegin) {
            res[rowEnd][j] = current++
        }
        rowEnd--
        for (i in rowEnd downTo rowBegin) {
            res[i][colBegin] = current++
        }
        colBegin++
    }

    return res
}