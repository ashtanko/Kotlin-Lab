/*
 * Copyright 2023 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import kotlin.math.min

/**
 * 783. Minimum Distance Between BST Nodes
 * @see <a href="https://leetcode.com/problems/minimum-distance-between-bst-nodes/">leetcode page</a>
 */
fun interface MinDiffInBST {
    fun minDiffInBST(root: TreeNode?): Int
}

class MinDiffInBSTInorder : MinDiffInBST {
    private var previous: TreeNode? = null
    private var min = Int.MAX_VALUE

    override fun minDiffInBST(root: TreeNode?): Int {
        inOrder(root)
        return min
    }

    private fun inOrder(root: TreeNode?) {
        if (root == null) return
        inOrder(root.left)
        previous?.let { prev ->
            min = min(min, root.value - prev.value)
        }
        previous = root
        inOrder(root.right)
    }
}
