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

import dev.shtanko.algorithms.annotations.Recursive

/**
 * 1008. Construct Binary Search Tree from Preorder Traversal
 * @see <a href="https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal">
 *     Source</a>
 */
fun interface BSTFromPreorder {
    operator fun invoke(preorder: IntArray): TreeNode?
}

@Recursive
class BSTFromPreorderRecursion : BSTFromPreorder {

    private var i = 0

    override operator fun invoke(preorder: IntArray): TreeNode? {
        return bstFromPreorder(preorder, Integer.MAX_VALUE)
    }

    private fun bstFromPreorder(arr: IntArray, bound: Int): TreeNode? {
        if (i == arr.size || arr[i] > bound) return null
        val root = TreeNode(arr[i++])
        root.left = bstFromPreorder(arr, root.value)
        root.right = bstFromPreorder(arr, bound)
        return root
    }
}
