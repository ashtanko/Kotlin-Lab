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

package dev.shtanko.algorithms.exercises

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class TreeTest {

    @Test
    internal fun `clone tree test`() {
        val t1 = TreeNode(4)
        val t2 = TreeNode(8)
        val t3 = TreeNode(15)
        val t4 = TreeNode(16)
        val t5 = TreeNode(23)
        val t6 = TreeNode(42)

        t1.left = t2
        t1.right = t3
        t2.left = t4
        t2.right = t5
        t3.left = t6

        t1.traverseTree()
        val clone = t1.clone()
        clone.traverseTree()
        assertEquals(t1, clone)
    }
}
