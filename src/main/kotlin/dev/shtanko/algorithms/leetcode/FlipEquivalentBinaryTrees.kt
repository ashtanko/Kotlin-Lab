/*
 * Copyright 2024 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 951. Flip Equivalent Binary Trees
 * @see <a href="https://leetcode.com/problems/flip-equivalent-binary-trees/">Flip Equivalent Binary Trees</a>
 */
@Medium("https://leetcode.com/problems/flip-equivalent-binary-trees")
fun interface FlipEquivalentBinaryTrees {
    operator fun invoke(root1: TreeNode?, root2: TreeNode?): Boolean
}

class FlipEquivalentBinaryTreesForm : FlipEquivalentBinaryTrees {
    override fun invoke(
        root1: TreeNode?,
        root2: TreeNode?,
    ): Boolean {
        if (root1?.value != root2?.value) {
            return false
        }

        return flipEquivRecursive(root1, root2)
    }

    private fun flipEquivRecursive(root1: TreeNode?, root2: TreeNode?): Boolean {
        if (root1 == null && root2 == null) {
            return true
        }

        val root1LeftValue = root1?.left?.value
        val root1RightValue = root1?.right?.value

        val root2LeftValue = root2?.left?.value
        val root2RightValue = root2?.right?.value

        if (root1LeftValue != root2LeftValue || root1RightValue != root2RightValue) {
            root2?.apply {
                val tempTreeNode = root2.left
                left = root2.right
                right = tempTreeNode
            }
            if (root1LeftValue != root2RightValue || root1RightValue != root2LeftValue) {
                return false
            }
        }

        return flipEquivRecursive(root1?.left, root2?.left) &&
            flipEquivRecursive(root1?.right, root2?.right)
    }
}
