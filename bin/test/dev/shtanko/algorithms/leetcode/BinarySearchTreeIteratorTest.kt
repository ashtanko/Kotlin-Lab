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
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

private fun getTree(): TreeNode {
    return TreeNode(7).apply {
        left = TreeNode(3)
        right = TreeNode(15)
        right?.apply {
            left = TreeNode(9)
            right = TreeNode(20)
        }
    }
}

abstract class BinarySearchTreeIteratorTest<out T : BSTIterator>(private val iterator: T) {

    @Test
    fun `bst iterator test`() {
        assertEquals(3, iterator.next())
        assertEquals(7, iterator.next())
        assertTrue(iterator.hasNext())
        assertEquals(9, iterator.next())
        assertTrue(iterator.hasNext())
        assertEquals(15, iterator.next())
        assertTrue(iterator.hasNext())
        assertEquals(20, iterator.next())
        assertFalse(iterator.hasNext())
    }
}

class BSTIteratorFlatteningTest :
    BinarySearchTreeIteratorTest<BSTIteratorFlattening>(BSTIteratorFlattening(getTree()))

class BSTIteratorControlledRecursionTest :
    BinarySearchTreeIteratorTest<BSTIteratorControlledRecursion>(BSTIteratorControlledRecursion(getTree()))
