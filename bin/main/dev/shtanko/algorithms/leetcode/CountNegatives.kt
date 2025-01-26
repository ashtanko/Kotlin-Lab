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

fun interface AbstractCountNegativesStrategy {
    operator fun invoke(grid: Array<IntArray>): Int
}

/**
 *
 */
class SimpleCountNegatives : AbstractCountNegativesStrategy {
    override operator fun invoke(grid: Array<IntArray>): Int {
        return grid.count()
    }

    private fun Array<IntArray>.count(): Int {
        val rows = this.size
        val cols = this[0].size
        var count = 0

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (this[i][j] < 0) {
                    count += this[i].size - j
                    break
                }
            }
        }
        return count
    }
}

/**
 *
 */
class CountNegativesTwoPointers : AbstractCountNegativesStrategy {
    override operator fun invoke(grid: Array<IntArray>): Int {
        return grid.count()
    }

    private fun Array<IntArray>.count(): Int {
        var res = 0
        val m = this.size
        val n = this[0].size
        var i = 0
        var j = n - 1
        while (i < m && j >= 0) {
            if (this[i][j] < 0) {
                res += m - i
                j--
            } else {
                i++
            }
        }
        return res
    }
}

/**
 *
 */
class CountNegativesBinary : AbstractCountNegativesStrategy {
    override operator fun invoke(grid: Array<IntArray>): Int {
        return grid.count()
    }

    private fun Array<IntArray>.count(): Int {
        if (this.isEmpty()) return 0
        if (this.size < 2 && first().isEmpty()) {
            return 0
        }
        var min = this[0].size - 1
        var res = 0

        for (arr in this) {
            min = arr.copyOfRange(0, min + 1).binarySearchToGetBigNegativeElementIndex()
            if (min == -1) {
                min = arr.size - 1
            } else {
                res += size - min
            }
        }

        return res
    }

    private fun IntArray.binarySearchToGetBigNegativeElementIndex(): Int {
        if (this.isEmpty()) return 0
        var low = 0
        var high = size - 1
        while (low < high) {
            val local = high - low
            val mid = low + local / 2
            if (this[mid] > 0) {
                low = mid + 1
            } else {
                high = mid
            }
        }
        return if (this[low] > -1) -1 else low
    }
}
