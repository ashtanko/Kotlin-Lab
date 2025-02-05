/*
 * Copyright 2021 Oleksii Shtanko
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

import kotlin.math.max

/**
 * Find Leaves of Binary Tree
 * @see <a href="https://leetcode.com/problems/find-leaves-of-binary-tree/">Source</a>
 */
fun interface FindLeaves {
    operator fun invoke(root: TreeNode?): List<List<Int>>
}

/**
 * Approach 1: DFS (Depth-First Search) with sorting
 */
class FindLeavesDFS : FindLeaves {

    private val pairs: MutableList<Pair<Int, Int>> = ArrayList()

    override operator fun invoke(root: TreeNode?): List<List<Int>> {
        getHeight(root)

        // sort all the (height, val) pairs
        pairs.sortBy {
            it.first
        }

        val n = pairs.size
        var height = 0
        var i = 0

        val solution: MutableList<List<Int>> = ArrayList()

        while (i < n) {
            val nums: MutableList<Int> = ArrayList()
            while (i < n && pairs[i].first == height) {
                nums.add(pairs[i].second)
                i++
            }
            solution.add(nums)
            height++
        }
        return solution
    }

    private fun getHeight(root: TreeNode?): Int {
        // return -1 for null nodes
        if (root == null) return -1

        // first calculate the height of the left and right children
        val leftHeight = getHeight(root.left)
        val rightHeight = getHeight(root.right)

        // based on the height of the left and right children, obtain the height of the current (parent) node
        val currHeight = max(leftHeight, rightHeight) + 1

        // collect the pair -> (height, val)
        this.pairs.add(currHeight to root.value)

        // return the height of the current node
        return currHeight
    }
}

/**
 * Approach 2: DFS (Depth-First Search) without sorting
 */
class FindLeavesDFS2 : FindLeaves {

    private val solution: MutableList<MutableList<Int>> = ArrayList()

    override operator fun invoke(root: TreeNode?): List<List<Int>> {
        getHeight(root)
        return this.solution
    }

    private fun getHeight(root: TreeNode?): Int {
        // return -1 for null nodes
        if (root == null) {
            return -1
        }

        // first calculate the height of the left and right children
        val leftHeight = getHeight(root.left)
        val rightHeight = getHeight(root.right)
        val currHeight = max(leftHeight, rightHeight) + 1
        if (solution.size == currHeight) {
            solution.add(ArrayList<Int>())
        }
        solution[currHeight].add(root.value)
        return currHeight
    }
}
