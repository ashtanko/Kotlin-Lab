/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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
import dev.shtanko.algorithms.annotations.Iterative
import java.util.Stack

/**
 * 938. Range Sum of BST
 * @see <a href="https://leetcode.com/problems/range-sum-of-bst">Source</a>
 */
fun interface RangeSumOfBinarySearchTree {
    operator fun invoke(root: TreeNode?, left: Int, right: Int): Int
}

@Iterative
class RangeSumOfBinarySearchTreeIterative : RangeSumOfBinarySearchTree {
    override operator fun invoke(root: TreeNode?, left: Int, right: Int): Int {
        var ans = 0
        val stack: Stack<TreeNode> = Stack()
        stack.push(root)
        while (stack.isNotEmpty()) {
            val node: TreeNode? = stack.pop()
            if (node != null) {
                if (node.value in left..right) ans += node.value
                if (left < node.value) stack.push(node.left)
                if (node.value < right) stack.push(node.right)
            }
        }
        return ans
    }
}

@DFS
class RangeSumOfBinarySearchTreeRecursive : RangeSumOfBinarySearchTree {
    override operator fun invoke(root: TreeNode?, left: Int, right: Int): Int {
        return rangeSumBSTRecursive(root, left, right)
    }

    private fun rangeSumBSTRecursive(root: TreeNode?, left: Int, right: Int): Int {
        return dfs(root, left, right, 0)
    }

    private fun dfs(node: TreeNode?, left: Int, right: Int, a: Int): Int {
        var ans = a
        if (node != null) {
            if (node.value in left..right) ans += node.value
            if (left < node.value) ans = dfs(node.left, left, right, ans)
            if (node.value < right) ans = dfs(node.right, left, right, ans)
        }
        return ans
    }
}
