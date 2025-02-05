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
 * Recover Binary Search Tree
 */
fun recoverTree(root: TreeNode?) {
    var rootNode = root
    var pre: TreeNode? = null
    var first: TreeNode? = null
    var second: TreeNode? = null
    // Morris Traversal
    var temp: TreeNode?
    while (rootNode != null) {
        if (rootNode.left != null) {
            // connect threading for root
            temp = rootNode.left
            while (temp!!.right != null && temp.right != rootNode) temp = temp.right
            // the threading already exists
            if (temp.right != null) {
                if (pre != null && pre.value > rootNode.value) {
                    if (first == null) {
                        first = pre
                        second = rootNode
                    } else {
                        second = rootNode
                    }
                }
                pre = rootNode
                temp.right = null
                rootNode = rootNode.right
            } else {
                // construct the threading
                temp.right = rootNode
                rootNode = rootNode.left
            }
        } else {
            if (pre != null && pre.value > rootNode.value) {
                if (first == null) {
                    first = pre
                    second = rootNode
                } else {
                    second = rootNode
                }
            }
            pre = rootNode
            rootNode = rootNode.right
        }
    }
    // swap two node values;
    if (first != null && second != null) {
        val t: Int = first.value
        first.value = second.value
        second.value = t
    }
}
