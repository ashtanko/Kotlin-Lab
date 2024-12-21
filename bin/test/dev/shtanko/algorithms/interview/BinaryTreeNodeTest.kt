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

package dev.shtanko.algorithms.interview

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class BinaryTreeNodeTest {

    @Test
    internal fun `binary tree node test`() {
        testRootOnly()
        test()
    }

    private fun testRootOnly() {
        val node = BinaryTreeNode(null)
        assertTrue(node == node.getRandom())
    }

    private fun test() {
        val root = BinaryTreeNode(null)

        val left = BinaryTreeNode(root)
        root.addLeftChild(left)

        val right = BinaryTreeNode(root)
        root.addRightChild(right)

        val rightRight = BinaryTreeNode(right)
        right.addRightChild(rightRight)

        assertTrue(left == root.getRandom { 0 })
        assertTrue(right == root.getRandom { 1 })

        assertTrue(root == root.getRandom { 4 })
        var switch = false
        assertTrue(
            rightRight == root.getRandom {
                if (!switch) {
                    switch = true
                    1
                } else {
                    0
                }
            },
        )
    }
}
