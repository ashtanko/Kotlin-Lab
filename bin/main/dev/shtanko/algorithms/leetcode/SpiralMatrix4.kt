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

/**
 * 2326. Spiral Matrix IV
 * @see <a href="https://leetcode.com/problems/spiral-matrix-iv/">Source</a>
 */
fun interface SpiralMatrix4 {
    operator fun invoke(matHeight: Int, matWidth: Int, head: ListNode?): Array<IntArray>
}

class WalkSpiralMatrix4 : SpiralMatrix4 {

    /**
     * This function generates a 2D array (matrix) in a spiral order from a linked list.
     * @param matHeight The height of the matrix.
     * @param matWidth The width of the matrix.
     * @param head The head of the linked list.
     * @return A 2D array representing the matrix.
     */
    override operator fun invoke(matHeight: Int, matWidth: Int, head: ListNode?): Array<IntArray> {
        var currentNode = head
        val spiralMatrix = Array(matHeight) { IntArray(matWidth) { -1 } }
        var rowIdx = 0
        var columnIdx = 0
        var rowIdxInc = 0
        var columnIdxInc = 1
        while (currentNode != null) {
            spiralMatrix[rowIdx][columnIdx] = currentNode.value
            currentNode = currentNode.next
            if (spiralMatrix[(rowIdx + rowIdxInc + matHeight) % matHeight]
                    [(columnIdx + columnIdxInc + matWidth) % matWidth] != -1
            ) {
                val temp = rowIdxInc
                rowIdxInc = columnIdxInc
                columnIdxInc = -temp
            }
            rowIdx += rowIdxInc
            columnIdx += columnIdxInc
        }
        return spiralMatrix
    }
}
