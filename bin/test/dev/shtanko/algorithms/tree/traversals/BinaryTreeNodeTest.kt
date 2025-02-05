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

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class BinaryTreeNodeTest {

    @Test
    fun `isRightChild returns true when node is right child of its parent`() {
        val parent = BinaryTreeNode(data = 1)
        val rightChild = BinaryTreeNode(data = 2)
        parent.right = rightChild
        rightChild.parent = parent

        assertTrue(rightChild.isRightChild())
    }

    @Test
    fun `isRightChild returns false when node is left child of its parent`() {
        val parent = BinaryTreeNode(data = 1)
        val leftChild = BinaryTreeNode(data = 2)
        parent.left = leftChild
        leftChild.parent = parent

        assertFalse(leftChild.isRightChild())
    }

    @Test
    fun `isRightChild returns false when node has no parent`() {
        val node = BinaryTreeNode(data = 1)

        assertFalse(node.isRightChild())
    }
}
