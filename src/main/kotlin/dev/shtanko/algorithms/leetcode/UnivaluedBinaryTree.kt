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
import dev.shtanko.algorithms.annotations.Recursive

fun interface UnivaluedBinaryTree {
    operator fun invoke(root: TreeNode?): Boolean
}

@DFS
class UnivaluedBinaryTreeDFS : UnivaluedBinaryTree {

    private var values: MutableList<Int> = mutableListOf()

    override operator fun invoke(root: TreeNode?): Boolean {
        dfs(root)
        for (value in values) {
            if (value != values.first()) return false
        }
        return true
    }

    private fun dfs(node: TreeNode?) {
        if (node != null) {
            values.add(node.value)
            dfs(node.left)
            dfs(node.right)
        }
    }
}

@Recursive
class UnivaluedBinaryTreeRecursive : UnivaluedBinaryTree {
    override operator fun invoke(root: TreeNode?): Boolean {
        val isLeftCorrect = root?.left == null || root.value == root.left?.value && invoke(root.left)
        val isRightCorrect = root?.right == null || root.value == root.right?.value && invoke(root.right)
        return isLeftCorrect && isRightCorrect
    }
}
