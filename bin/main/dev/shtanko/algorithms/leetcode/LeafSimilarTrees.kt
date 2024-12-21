/*
 * Copyright 2020 Oleksii Shtanko
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
 * 872. Leaf-Similar Trees
 * @see <a href="https://leetcode.com/problems/leaf-similar-trees">Source</a>
 */
fun interface LeafSimilarTrees {
    operator fun invoke(root1: TreeNode?, root2: TreeNode?): Boolean
}

class LeafSimilarDepthFirstSearch : LeafSimilarTrees {
    override fun invoke(root1: TreeNode?, root2: TreeNode?): Boolean {
        val leaves1: MutableList<Int?> = ArrayList()
        val leaves2: MutableList<Int?> = ArrayList()
        dfs(root1, leaves1)
        dfs(root2, leaves2)
        return leaves1 == leaves2
    }

    private fun dfs(node: TreeNode?, leafValues: MutableList<Int?>) {
        if (node != null) {
            if (node.left == null && node.right == null) leafValues.add(node.value)
            dfs(node.left, leafValues)
            dfs(node.right, leafValues)
        }
    }
}
