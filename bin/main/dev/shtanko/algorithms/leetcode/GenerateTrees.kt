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
 * Unique Binary Search Trees 2
 */
fun invoke(n: Int): MutableList<TreeNode?> {
    var res: MutableList<TreeNode?> = ArrayList()
    if (n <= 0) return res
    res.add(null)
    // insert i into tree that stores values from n to i+1
    for (i in n downTo 1) {
        // `next` stores all possible trees that store values from n to i
        val next: MutableList<TreeNode?> = ArrayList()
        for (node in res) {
            /* Case 1: put n on the root,
             * and the original @node tree is its right tree
             * since i is the smallest value in the @node tree (from n to i+1)
             */
            val root = TreeNode(i)
            root.right = node
            next.add(root)

            /* Other Cases: put n on root.left, root.left.left, root.left....left,
             * the root of the new tree is still @node,
             * i put on insertParent.left,
             * and the original left tree of the insertParent is set as the right subtree of the new node since i is
             * small than values in the subtree.
             */
            var insertParent = node
            while (insertParent != null) {
                // Step 1: generate a new tree from the @node tree
                val cRoot = TreeNode(node?.value!!)
                // clone left subtree since we need to change it by inserting i
                cRoot.left = node.left.clone()
                // reusing the right tree since it is fixed
                cRoot.right = node.right

                // Step 2: insert i into the new tree
                val insertParentInNewTree = getValNode(cRoot, insertParent.value)
                val tmp = insertParentInNewTree?.left
                insertParentInNewTree?.left = TreeNode(i)
                insertParentInNewTree?.left?.right = tmp
                next.add(cRoot)
                insertParent = insertParent.left
            }
        }
        res = next
    }
    return res
}

private fun getValNode(n: TreeNode, value: Int): TreeNode? { // find the cutoff node in the new tree
    var node: TreeNode? = n
    while (node != null) {
        if (node.value == value) break
        node = node.left
    }
    return node
}
