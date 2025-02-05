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

import java.util.LinkedList
import java.util.Queue

/**
 * 1970. Last Day Where You Can Still Cross
 * @see <a href="https://leetcode.com/problems/last-day-where-you-can-still-cross/">Source</a>
 */
fun interface LatestDayToCross {
    operator fun invoke(row: Int, col: Int, cells: Array<IntArray>): Int
}

class DSU(n: Int) {
    var root: IntArray = IntArray(n)
    var size: IntArray

    init {
        for (i in 0 until n) {
            root[i] = i
        }
        size = IntArray(n) { 1 }
    }

    fun find(x: Int): Int {
        if (root[x] != x) {
            root[x] = find(root[x])
        }
        return root[x]
    }

    fun union(x: Int, y: Int) {
        var rootX = find(x)
        var rootY = find(y)
        if (rootX == rootY) {
            return
        }
        if (size[rootX] > size[rootY]) {
            val tmp = rootX
            rootX = rootY
            rootY = tmp
        }
        root[rootX] = rootY
        size[rootY] += size[rootX]
    }
}

/**
 * Approach 1: Binary Search + Breadth-First Search
 */
class LatestDayToCrossBSBFS : LatestDayToCross {

    private val directions = arrayOf(intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1))

    override operator fun invoke(row: Int, col: Int, cells: Array<IntArray>): Int {
        var left = 1
        var right = row * col
        while (left < right) {
            val mid = right - (right - left) / 2
            if (canCross(row, col, cells, mid)) {
                left = mid
            } else {
                right = mid - 1
            }
        }
        return left
    }

    private fun canCross(row: Int, col: Int, cells: Array<IntArray>, day: Int): Boolean {
        val grid = Array(row) { IntArray(col) }
        val queue: Queue<IntArray> = LinkedList()
        for (i in 0 until day) {
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1
        }
        for (i in 0 until col) {
            if (grid[0][i] == 0) {
                queue.offer(intArrayOf(0, i))
                grid[0][i] = -1
            }
        }
        while (queue.isNotEmpty()) {
            val cur: IntArray = queue.poll()
            val r = cur[0]
            val c = cur[1]
            if (r == row - 1) {
                return true
            }
            for (dir in directions) {
                val newRow = r + dir[0]
                val newCol = c + dir[1]
                if (newRow in 0 until row && newCol >= 0 && newCol < col && grid[newRow][newCol] == 0) {
                    grid[newRow][newCol] = -1
                    queue.offer(intArrayOf(newRow, newCol))
                }
            }
        }
        return false
    }
}

/**
 * Approach 2: Binary Search + Depth-First Search
 */
class LatestDayToCrossBSDFS : LatestDayToCross {

    private val directions = arrayOf(intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1))

    override operator fun invoke(row: Int, col: Int, cells: Array<IntArray>): Int {
        var left = 1
        var right = row * col
        while (left < right) {
            val mid = right - (right - left) / 2
            if (canCross(row, col, cells, mid)) {
                left = mid
            } else {
                right = mid - 1
            }
        }
        return left
    }

    private fun canCross(row: Int, col: Int, cells: Array<IntArray>, day: Int): Boolean {
        val grid = Array(row) { IntArray(col) }
        for (i in 0 until day) {
            val r = cells[i][0] - 1
            val c = cells[i][1] - 1
            grid[r][c] = 1
        }
        for (i in 0 until day) {
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1
        }
        for (i in 0 until col) {
            if (grid[0][i] == 0 && dfs(grid, 0, i, row, col)) {
                return true
            }
        }
        return false
    }

    private fun dfs(grid: Array<IntArray>, r: Int, c: Int, row: Int, col: Int): Boolean {
        if (r < 0 || r >= row || c < 0 || c >= col || grid[r][c] != 0) {
            return false
        }
        if (r == row - 1) {
            return true
        }
        grid[r][c] = -1
        for (dir in directions) {
            val newR = r + dir[0]
            val newC = c + dir[1]
            if (dfs(grid, newR, newC, row, col)) {
                return true
            }
        }
        return false
    }
}

/**
 * Approach 3: Disjoint Set Union (on land cells)
 */
class LatestDayToCrossDisjoint : LatestDayToCross {
    override operator fun invoke(row: Int, col: Int, cells: Array<IntArray>): Int {
        return performCross(
            row,
            col,
            cells,
            arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0)),
        )
    }
}

/**
 * Approach 4: Disjoint Set Union (on water cells)
 */
class LatestDayToCrossUnion : LatestDayToCross {
    override operator fun invoke(row: Int, col: Int, cells: Array<IntArray>): Int {
        val dsu = DSU(row * col + 2)
        val grid = Array(row) { IntArray(col) }
        val directions = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, -1),
            intArrayOf(1, 0),
            intArrayOf(-1, 0),
            intArrayOf(1, 1),
            intArrayOf(1, -1),
            intArrayOf(-1, 1),
            intArrayOf(-1, -1),
        )

        for (i in 0 until row * col) {
            val r = cells[i][0] - 1
            val c = cells[i][1] - 1
            grid[r][c] = 1
            val index1 = r * col + c + 1
            directions.forEach { d ->
                val newR = r + d[0]
                val newC = c + d[1]
                val index2 = newR * col + newC + 1
                if (newR in 0 until row && newC >= 0 && newC < col && grid[newR][newC] == 1) {
                    dsu.union(index1, index2)
                }
            }
            if (c == 0) {
                dsu.union(0, index1)
            }
            if (c == col - 1) {
                dsu.union(row * col + 1, index1)
            }
            if (dsu.find(0) == dsu.find(row * col + 1)) {
                return i
            }
        }
        return -1
    }
}

private fun performCross(
    row: Int,
    col: Int,
    cells: Array<IntArray>,
    directions: Array<IntArray>,
): Int {
    val dsu = DSU(row * col + 2)
    val grid = Array(row) { IntArray(col) }

    cells.indices.reversed().forEach { i ->
        val r = cells[i][0] - 1
        val c = cells[i][1] - 1
        grid[r][c] = 1
        val index1 = r * col + c + 1
        for (d in directions) {
            val newR = r + d[0]
            val newC = c + d[1]
            val index2 = newR * col + newC + 1
            if (newR in 0 until row && newC >= 0 && newC < col && grid[newR][newC] == 1) {
                dsu.union(index1, index2)
            }
        }
        if (r == 0) {
            dsu.union(0, index1)
        }
        if (r == row - 1) {
            dsu.union(row * col + 1, index1)
        }
        if (dsu.find(0) == dsu.find(row * col + 1)) {
            return i
        }
    }
    return -1
}
