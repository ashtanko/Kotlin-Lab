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

import java.util.Stack
import kotlin.math.max

/**
 * 827. Making A Large Island
 * @see <a href="https://leetcode.com/problems/making-a-large-island/">Source</a>
 */
fun interface LargestIsland {
    operator fun invoke(grid: Array<IntArray>): Int
}

/**
 * Approach 1: Naive Depth First Search
 */
class LargestIslandDFS : LargestIsland {
    private val dr = intArrayOf(-1, 0, 1, 0)
    private val dc = intArrayOf(0, -1, 0, 1)

    override operator fun invoke(grid: Array<IntArray>): Int {
        val n: Int = grid.size

        var ans = 0
        var hasZero = false
        for (r in 0 until n) for (c in 0 until n) if (grid[r][c] == 0) {
            hasZero = true
            grid[r][c] = 1
            ans = max(ans, check(grid, r, c))
            grid[r][c] = 0
        }

        return if (hasZero) ans else n * n
    }

    fun check(grid: Array<IntArray>, r0: Int, c0: Int): Int {
        val n = grid.size
        val stack: Stack<Int> = Stack()
        val seen: MutableSet<Int> = HashSet()
        stack.push(r0 * n + c0)
        seen.add(r0 * n + c0)
        while (stack.isNotEmpty()) {
            val code: Int = stack.pop()
            val r = code / n
            val c = code % n
            for (k in 0..3) {
                val nr = r + dr[k]
                val nc = c + dc[k]
                val isContains = !seen.contains(nr * n + nc)
                val local = nr in 0 until n && 0 <= nc && nc < n
                if (isContains && local && grid[nr][nc] == 1) {
                    stack.push(nr * n + nc)
                    seen.add(nr * n + nc)
                }
            }
        }
        return seen.size
    }
}

/**
 * Approach #2: Component Sizes
 */
class LargestIslandComponentSizes : LargestIsland {
    private val dr = intArrayOf(-1, 0, 1, 0)
    private val dc = intArrayOf(0, -1, 0, 1)
    private var grid: Array<IntArray> = arrayOf()
    private var n = 0

    override operator fun invoke(grid: Array<IntArray>): Int {
        this.grid = grid
        n = grid.size

        var index = 2
        val area = IntArray(n * n + 2)
        for (r in 0 until n) {
            for (c in 0 until n) {
                if (grid[r][c] == 1) {
                    area[index] = dfs(r, c, index++)
                }
            }
        }

        var ans = 0
        for (x in area) ans = max(ans, x)
        for (r in 0 until n) for (c in 0 until n) if (grid[r][c] == 0) {
            val seen: MutableSet<Int> = HashSet()
            for (move in neighbors(r, c)) {
                if (grid[move / n][move % n] > 1) {
                    seen.add(grid[move / n][move % n])
                }
            }
            var bns = 1
            for (i in seen) {
                bns += area[i]
            }
            ans = max(ans, bns)
        }

        return ans
    }

    private fun dfs(r: Int, c: Int, index: Int): Int {
        var ans = 1
        grid[r][c] = index
        for (move in neighbors(r, c)) {
            if (grid[move / n][move % n] == 1) {
                grid[move / n][move % n] = index
                ans += dfs(move / n, move % n, index)
            }
        }
        return ans
    }

    private fun neighbors(r: Int, c: Int): List<Int> {
        val ans: MutableList<Int> = ArrayList()
        for (k in 0..3) {
            val nr = r + dr[k]
            val nc = c + dc[k]
            if (nr in 0 until n && 0 <= nc && nc < n) {
                ans.add(nr * n + nc)
            }
        }
        return ans
    }
}

class LargestIslandUnionFind : LargestIsland {
    override operator fun invoke(grid: Array<IntArray>): Int {
        val rows: Int = grid.size
        val cols: Int = grid.first().size

        // create father array and size array, and initialize them
        val father = IntArray(rows * cols)
        for (i in 0 until rows * cols) {
            father[i] = i
        }
        val size = IntArray(rows * cols) { 1 }

        val dx = intArrayOf(0, 1, -1, 0)
        val dy = intArrayOf(1, 0, 0, -1)

        scanGrid(rows, cols, grid, dx, dy, father, size)

        // find current max component size
        val max = findMaxComponent(rows, cols, grid, dx, dy, father, size)

        // return whole size if the grid is an all 1 matrix, otherwise return the value of max
        return if (max == 0) rows * cols else max
    }

    private fun findMaxComponent(
        rows: Int,
        cols: Int,
        grid: Array<IntArray>,
        dx: IntArray,
        dy: IntArray,
        father: IntArray,
        size: IntArray,
    ): Int {
        var max = 0
        for (i in size.indices) {
            max = max(max, size[i])
        }
        // find max component size if we set any 0 to 1
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (grid[i][j] == 0) {
                    var combinedSize = 1
                    val prevFather: MutableSet<Int> = HashSet()
                    for (k in 0..3) {
                        val newI = i + dx[k]
                        val newJ = j + dy[k]
                        val newId = newI * cols + newJ
                        if (isValid(rows, cols, newI, newJ) && grid[newI][newJ] == 1) {
                            val currFather = find(father, newId)
                            if (prevFather.isEmpty() || !prevFather.contains(currFather)) {
                                combinedSize += size[currFather]
                                prevFather.add(currFather)
                            }
                        }
                    }
                    max = max(max, combinedSize)
                }
            }
        }
        return max
    }

    private fun scanGrid(
        rows: Int,
        cols: Int,
        grid: Array<IntArray>,
        dx: IntArray,
        dy: IntArray,
        father: IntArray,
        size: IntArray,
    ) {
        // scan grid, update father array and size array
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (grid[i][j] == 1) {
                    val id = i * cols + j
                    for (k in 0..3) {
                        val newI = i + dx[k]
                        val newJ = j + dy[k]
                        val newId = newI * cols + newJ
                        if (isValid(rows, cols, newI, newJ) && grid[newI][newJ] == 1) {
                            union(father, size, id, newId)
                        }
                    }
                }
            }
        }
    }

    private fun find(father: IntArray, id: Int): Int {
        return if (father[id] == id) {
            id
        } else {
            find(father, father[id]).also { father[id] = it }
        }
    }

    private fun union(father: IntArray, size: IntArray, id1: Int, id2: Int) {
        val fa1 = find(father, id1)
        val fa2 = find(father, id2)
        if (fa1 != fa2) {
            father[fa1] = fa2
            size[fa2] += size[fa1]
        }
    }

    private fun isValid(rows: Int, cols: Int, i: Int, j: Int): Boolean {
        return i in 0 until rows && j >= 0 && j < cols
    }
}
