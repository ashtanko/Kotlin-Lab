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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class IncreasingOrderSearchTreeTest {

    @Test
    fun `increasing order search tree test`() {
        val root = TreeNode(5).apply {
            left = TreeNode(3)
            right = TreeNode(6)
            left?.apply {
                left = TreeNode(2)
                left?.left = TreeNode(1)
                right = TreeNode(4)
            }
            right?.apply {
                right = TreeNode(8)
                right?.left = TreeNode(7)
                right?.right = TreeNode(9)
            }
        }

        val increasingRoot = increasingBST(root)
        val actual = increasingRoot.levelOrder()

        val expected = mutableListOf<List<Int>>()
        for (i in 1 until 10) {
            expected.add(listOf(i))
        }
        assertEquals(expected, actual)
        assertNull(increasingRoot?.left)
    }
}
