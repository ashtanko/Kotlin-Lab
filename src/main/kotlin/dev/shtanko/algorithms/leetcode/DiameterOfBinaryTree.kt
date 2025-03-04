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

/**
 * 543. Diameter of Binary Tree
 * @see <a href="https://leetcode.com/problems/diameter-of-binary-tree">Source</a>
 */
fun interface DiameterOfBinaryTree {
    operator fun invoke(root: TreeNode?): Int
}

class DiameterOfBinaryTreeMaxDepth : DiameterOfBinaryTree {
    private var ans = 0

    override fun invoke(root: TreeNode?): Int {
        maxDepth(root)

        // Return the global maximum
        return ans
    }

    private fun maxDepth(p: TreeNode?): Int {
        if (p == null) return 0
        val left = maxDepth(p.left)
        val right = maxDepth(p.right)
        ans = maxOf(ans, left + right)
        return 1 + maxOf(left, right)
    }
}
