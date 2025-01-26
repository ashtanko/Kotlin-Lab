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

import java.util.PriorityQueue

/**
 * 1337. The K Weakest Rows in a Matrix
 * @see <a href="https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix">Source</a>
 */
fun interface KWeakestRows {
    /**
     * This function takes a matrix of integers and a number k, and returns an array of the indices of the k weakest
     * rows in the matrix.
     * A row is considered weak if the sum of its elements is less than the sum of the elements of another row.
     * The weaker the row, the earlier it appears in the array.
     *
     * @param mat The matrix of integers.
     * @param k The number of weakest rows to find.
     * @return An array of the indices of the k weakest rows in the matrix.
     */
    operator fun invoke(mat: Array<IntArray>, k: Int): IntArray
}

/**
 * This class provides an implementation of the KWeakestRows interface using a priority queue.
 */
class KWeakestRowsPQ : KWeakestRows {
    override fun invoke(mat: Array<IntArray>, k: Int): IntArray {
        // Helper function to count the number of ones in a row
        fun numOnes(row: IntArray): Int {
            var lo = 0
            var hi = row.size
            while (lo < hi) {
                val mid = lo + (hi - lo) / 2
                if (row[mid] == 1) lo = mid + 1 else hi = mid
            }
            return lo
        }

        // Priority queue to keep track of the weakest rows
        val pq: PriorityQueue<IntArray> = PriorityQueue { a, b ->
            if (a[0] != b[0]) b[0] - a[0] else b[1] - a[1]
        }
        var k0 = k
        val ans = IntArray(k0)

        // Add each row to the priority queue
        for (i in mat.indices) {
            pq.offer(intArrayOf(numOnes(mat[i]), i))
            if (pq.size > k0) pq.poll()
        }

        // Extract the k weakest rows from the priority queue
        while (k0 > 0) {
            ans[--k0] = pq.poll()[1]
        }

        return ans
    }
}

/**
 * This class provides an implementation of the KWeakestRows interface using brute force.
 */
class KWeakestRowsBF : KWeakestRows {
    override fun invoke(mat: Array<IntArray>, k: Int) = Pair(mat, k).kWeakestRows()

    /**
     * This function finds the k weakest rows in the matrix using brute force.
     * It calculates a score for each row, which is the sum of its elements times the number of rows plus the row index.
     * The function then sorts the scores and returns the indices of the k weakest rows.
     *
     * @return An array of the indices of the k weakest rows in the matrix.
     */
    private fun Pair<Array<IntArray>, Int>.kWeakestRows(): IntArray {
        val matrix = first
        val rows = matrix.size
        val cols = matrix.first().size
        val score = IntArray(rows)
        var j: Int
        for (i in 0 until rows) {
            j = 0
            while (j < cols) {
                if (matrix[i][j] == 0) {
                    break
                }
                j++
            }
            score[i] = j * rows + i
        }
        score.sort()
        for (i in score.indices) {
            score[i] = score[i] % rows
        }
        return score.copyOfRange(0, second)
    }
}
