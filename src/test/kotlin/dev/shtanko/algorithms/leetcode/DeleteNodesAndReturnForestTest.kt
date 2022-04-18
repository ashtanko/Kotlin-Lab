/*
 * Copyright 2020 Oleksii Shtanko
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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DeleteNodesAndReturnForestTest {

    @Test
    internal fun `delete nodes and return forest test`() {
        val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7)
        val tree: TreeNode? = null
        val root = insertLevelOrder(tree, arr, 0)
        val toDelete = intArrayOf(3, 5)

        val actual = delNodes(root, toDelete).map { treeNode ->
            treeNode.levelOrder()
        }
        val expected = listOf(
            listOf(
                listOf(1),
                listOf(2),
                listOf(4)
            ),
            listOf(listOf(6)),
            listOf(listOf(7)),
        )
        assertEquals(expected, actual)
    }
}
