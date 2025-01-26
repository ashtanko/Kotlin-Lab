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
 * 427. Construct Quad Tree
 * @see <a href="https://leetcode.com/problems/construct-quad-tree/">Source</a>
 */
fun interface ConstructQuadTree {
    fun construct(grid: Array<IntArray>): QuadTreeNode?
}

class ConstructQuadTreeRecursive : ConstructQuadTree {
    override fun construct(grid: Array<IntArray>): QuadTreeNode? {
        return if (grid.isEmpty()) null else helper(grid, 0, 0, grid.size)
    }

    fun helper(grid: Array<IntArray>, x: Int, y: Int, len: Int): QuadTreeNode {
        val newNode = QuadTreeNode(grid[x][y] != 0, true)
        val half = len / 2
        if (len == 1) return newNode
        val topL = helper(grid, x, y, half)
        val topR = helper(grid, x, y + half, half)
        val bottomL = helper(grid, x + half, y, half)
        val bottomR = helper(grid, x + half, y + half, half)
        val c1 = topL.isLeaf.not().or(topR.isLeaf.not()).or(bottomL.isLeaf.not()).or(bottomR.isLeaf.not())
        val c2 = (topL.value != topR.value).or(topR.value != bottomL.value).or(bottomL.value != bottomR.value)
        if (c1 || c2) {
            newNode.apply {
                topLeft = topL
                topRight = topR
                bottomLeft = bottomL
                bottomRight = bottomR
                isLeaf = false
            }
        }
        return newNode
    }
}
