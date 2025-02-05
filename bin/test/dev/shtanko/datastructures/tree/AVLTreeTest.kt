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

package dev.shtanko.datastructures.tree

import dev.shtanko.patterns.utils.InMemoryAppender
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class AVLTreeTest {

    private lateinit var appender: InMemoryAppender

    @BeforeEach
    fun setUp() {
        appender = InMemoryAppender()
    }

    @AfterEach
    fun tearDown() {
        appender.stop()
    }

    /* The constructed AVL Tree would be
             30
            /  \
          20   40
         /  \    \
        10  25   50
     */
    @Test
    internal fun `insert items test`() {
        val tree = AVLTree()
        tree.root = tree.insert(tree.root, 10)
        tree.root = tree.insert(tree.root, 20)
        tree.root = tree.insert(tree.root, 30)
        tree.root = tree.insert(tree.root, 40)
        tree.root = tree.insert(tree.root, 50)
        tree.root = tree.insert(tree.root, 25)
        tree.preOrder(tree.root)

        assertEquals("50", appender.lastMessage)
        assertEquals(6, appender.logSize)
    }

    @Test
    internal fun `insert duplicate keys`() {
        val tree = AVLTree()
        tree.root = tree.insert(tree.root, 10)
        tree.root = tree.insert(tree.root, 10)
        tree.preOrder(tree.root)

        assertEquals("10", appender.lastMessage)
        assertEquals(1, appender.logSize)
    }

    @Test
    internal fun `insert items in random order test`() {
        val tree = AVLTree()
        tree.root = tree.insert(tree.root, 12)
        tree.root = tree.insert(tree.root, 8)
        tree.root = tree.insert(tree.root, 18)
        tree.root = tree.insert(tree.root, 17)
        tree.root = tree.insert(tree.root, 11)
        tree.root = tree.insert(tree.root, 5)
        tree.root = tree.insert(tree.root, 4)
        tree.preOrder(tree.root)

        assertEquals("17", appender.lastMessage)
        assertEquals(7, appender.logSize)
    }

    @Test
    internal fun `insert items 2 test`() {
        val tree = AVLTree()
        tree.root = tree.insert(tree.root, 10)
        tree.root = tree.insert(tree.root, 22)
        tree.root = tree.insert(tree.root, 12)
        tree.root = tree.insert(tree.root, 20)
        tree.root = tree.insert(tree.root, 25)
        tree.root = tree.insert(tree.root, 28)
        tree.root = tree.insert(tree.root, 30)
        tree.root = tree.insert(tree.root, 48)
        tree.root = tree.insert(tree.root, 38)
        tree.root = tree.insert(tree.root, 40)
        tree.root = tree.insert(tree.root, 36)
        tree.preOrder(tree.root)

        assertEquals("40", appender.lastMessage)
        assertEquals(11, appender.logSize)
    }
}
