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

package dev.shtanko.algorithms.tree.traversals

/**
 * Represents a node in a binary tree.
 *
 * @property left The left child node of this node. Default is null.
 * @property right The right child node of this node. Default is null.
 * @property data The integer value stored in this node.
 */
class BinaryTreeNode(
    var left: BinaryTreeNode? = null,
    var right: BinaryTreeNode? = null,
    val data: Int,
) {

    /**
     * The parent node of this node. Default is null.
     */
    var parent: BinaryTreeNode? = null

    init {
        // Set the parent of the left and right child nodes to this node.
        left?.parent = this
        right?.parent = this
    }

    /**
     * Checks if this node is the right child of its parent.
     *
     * @return True if this node is the right child, false otherwise.
     */
    fun isRightChild(): Boolean = this.parent?.right == this
}
