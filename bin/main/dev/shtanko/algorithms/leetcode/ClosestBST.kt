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

import dev.shtanko.algorithms.annotations.BinarySearch
import dev.shtanko.algorithms.annotations.Iterative
import dev.shtanko.algorithms.annotations.Recursive
import java.util.LinkedList
import kotlin.math.abs

/**
 * 270. Closest Binary Search Tree Value
 * https://leetcode.com/problems/closest-binary-search-tree-value/
 */
fun interface ClosestBST {
    fun closestValue(root: TreeNode?, target: Double): Int
}

/**
 * Approach 1: Recursive Inorder + Linear search
 * Time complexity : O(N)
 * Space complexity : O(N)
 */
@Recursive
class RecursiveInorder : ClosestBST {
    override fun closestValue(root: TreeNode?, target: Double): Int {
        val nums = inorder(root)
        val comparator = Comparator<Int> { o1, o2 ->
            val first = abs(o1 - target)
            val second = abs(o2 - target)
            return@Comparator if (first < second) -1 else 1
        }
        return nums.minWith(comparator)
    }

    private fun inorder(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()
        val nums = mutableListOf<Int>()
        inorder(root.left)
        nums.add(root.value)
        inorder(root.right)
        return nums
    }
}

/**
 * Approach 2: Iterative Inorder, O(k) time
 */
@Iterative
class IterativeInorder : ClosestBST {
    override fun closestValue(root: TreeNode?, target: Double): Int {
        val stack: LinkedList<TreeNode?> = LinkedList()
        var pred = Int.MIN_VALUE
        var node = root
        while (stack.isNotEmpty() || node != null) {
            while (node != null) {
                stack.add(node)
                node = node.left
            }
            node = stack.removeLast()
            if (pred <= target && target < node!!.value) {
                return if (abs(
                        pred - target,
                    ) < abs(node.value - target)
                ) {
                    pred
                } else {
                    node.value
                }
            }
            pred = node!!.value
            node = node.right
        }
        return pred
    }
}

/**
 * Approach 3: Binary Search
 * Time complexity : O(H)
 * Space complexity : O(1)
 */
@BinarySearch
class ClosestBSTBinarySearch : ClosestBST {
    override fun closestValue(root: TreeNode?, target: Double): Int {
        var value: Int
        var node = root
        var closest: Int = node?.value ?: 0
        while (node != null) {
            value = node.value
            closest = if (abs(value - target) < abs(closest - target)) {
                value
            } else {
                closest
            }
            node = if (target < node.value) {
                node.left
            } else {
                node.right
            }
        }
        return closest
    }
}
