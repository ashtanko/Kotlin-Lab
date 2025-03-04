/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.DFS
import dev.shtanko.algorithms.annotations.Recursive

/**
 * 1457. Pseudo-Palindromic Paths in a Binary Tree
 * @see <a href="https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree">Source</a>
 */
fun interface PseudoPalindromicPaths {
    operator fun invoke(root: TreeNode?): Int
}

@DFS
class PseudoPalindromicPathsDFS : PseudoPalindromicPaths {
    override fun invoke(root: TreeNode?): Int {
        var count = 0
        var path: Int

        val stack = ArrayDeque<Pair<TreeNode?, Int>>()
        stack.add(Pair(root, 0))
        while (stack.isNotEmpty()) {
            val p = stack.removeLast()
            val node = p.first
            path = p.second

            if (node != null) {
                // compute occurrence of each digit
                // in the corresponding register
                path = path xor (1 shl node.value)
                // if it's a leaf check if the path is pseudo-palindromic
                if (node.left == null && node.right == null) {
                    // check if at most one digit has an odd frequency
                    if ((path and (path - 1)) == 0) {
                        count++
                    }
                } else {
                    stack.add(Pair(node.right, path))
                    stack.add(Pair(node.left, path))
                }
            }
        }
        return count
    }
}

@Recursive
class PseudoPalindromicPathsRecursive : PseudoPalindromicPaths {
    private var count = 0

    override fun invoke(root: TreeNode?): Int {
        preorder(root, 0)
        return count
    }

    fun preorder(node: TreeNode?, path: Int) {
        if (node != null) {
            // compute occurrences of each digit
            // in the corresponding register
            val updatedPath = path xor (1 shl node.value)
            // if it's a leaf check if the path is pseudo-palindromic
            if ((node.left == null && node.right == null) && (updatedPath and (updatedPath - 1) == 0)) {
                ++count
            }
            preorder(node.left, updatedPath)
            preorder(node.right, updatedPath)
        }
    }
}
