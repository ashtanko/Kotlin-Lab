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

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BinarySearchTreeTest {

    @Test
    internal fun `empty test`() {
        val tree = BinarySearchTree<Int, Int>()
        assertEquals(0, tree.size)
        assertTrue(tree.isEmpty())
    }

    @Test
    internal fun `size of one test`() {
        val tree = BinarySearchTree<Int, String>()
        tree.add(1, "1")
        assertFalse(tree.isEmpty())
        assertEquals(1, tree.size)
        assertEquals(1, tree.height())
        assertEquals(1, tree.min())
        assertEquals(1, tree.max())
        assertEquals("1", tree[1])
        tree.pollMin()
        assertTrue(tree.isEmpty())
    }

    @Test
    internal fun `size of three test`() {
        val tree = BinarySearchTree<Int, String>()
        tree.add(1, "1")
        tree.add(2, "2")
        tree.add(3, "3")

        assertFalse(tree.isEmpty())
        assertEquals(3, tree.size)
        assertEquals(3, tree.height())
        assertEquals(1, tree.min())
        assertEquals(3, tree.max())
        assertEquals("1", tree[1])
        assertEquals("2", tree[2])
        assertEquals("3", tree[3])
        tree.pollMin()
        assertEquals(2, tree.min())
        assertEquals(3, tree.max())
        assertEquals("2", tree[2])
        assertEquals("3", tree[3])
        tree.pollMax()
        assertEquals(2, tree.min())
        assertEquals(2, tree.max())
        assertEquals("2", tree[2])
    }

    @Test
    internal fun `overwrite test`() {
        val tree = BinarySearchTree<Int, String>()
        tree.add(1, "1")
        assertFalse(tree.isEmpty())
        assertEquals(1, tree.size)
        assertEquals(1, tree.height())
        assertEquals("1", tree[1])
        tree.add(1, "2")
        assertFalse(tree.isEmpty())
        assertEquals(1, tree.size)
        assertEquals(1, tree.height())
        assertEquals(1, tree.min())
        assertEquals(1, tree.max())
        assertEquals("2", tree[1])
        tree.pollMin()
        assertTrue(tree.isEmpty())
    }

    @Test
    internal fun `letters test`() {
        val tree = BinarySearchTree<Char, String>()
        val letters = arrayOf(
            'j', 'p', 'q', 's', 'f', 'o', 'g', 'v', 'h', 'm', 'x', 'z',
            'l', 'n', 'd', 'c', 'a', 'r', 'b', 't', 'i', 'u', 'w', 'k', 'y', 'e',
        )
        letters.forEach { tree.add(it, it.toString()) }

        assertEquals(letters.toSet(), tree.keys)
        assertArrayEquals(
            letters.map { it.toString() }.sorted().toTypedArray(),
            tree.values.sorted().toTypedArray(),
        )

        assertEquals(26, tree.size)
        assertEquals('a', tree.min())
        assertEquals('z', tree.max())
        tree.pollMin()
        assertEquals(25, tree.size)
        assertEquals('b', tree.min())
        assertEquals('z', tree.max())
        tree.pollMax()
        assertEquals(24, tree.size)
        assertEquals('b', tree.min())
        assertEquals('y', tree.max())
        tree.pollMin()
        assertEquals(23, tree.size)
        assertEquals('c', tree.min())
        assertEquals('y', tree.max())
        tree.pollMax()
        assertEquals(22, tree.size)
        assertEquals('c', tree.min())
        assertEquals('x', tree.max())
        tree.pollMin()
        assertEquals(21, tree.size)
        assertEquals('d', tree.min())
        assertEquals('x', tree.max())
        tree.pollMax()
        assertEquals(20, tree.size)
        assertEquals('d', tree.min())
        assertEquals('w', tree.max())
        tree.pollMin()
        tree.pollMin()
        tree.pollMin()
        assertEquals(17, tree.size)
        assertEquals('g', tree.min())
        assertEquals('w', tree.max())
        tree.pollMax()
        tree.pollMax()
        tree.pollMax()
        assertEquals(14, tree.size)
        assertEquals('g', tree.min())
        assertEquals('t', tree.max())
        tree.pollMin()
        tree.pollMin()
        tree.pollMin()
        tree.pollMin()
        tree.pollMin()
        assertEquals(9, tree.size)
        assertEquals('l', tree.min())
        assertEquals('t', tree.max())
        tree.pollMax()
        tree.pollMax()
        tree.pollMax()
        tree.pollMax()
        tree.pollMax()
        assertEquals(4, tree.size)
        assertEquals('l', tree.min())
        assertEquals('o', tree.max())
        tree.pollMin()
        assertEquals(3, tree.size)
        assertEquals('m', tree.min())
        assertEquals('o', tree.max())
        tree.pollMax()
        assertEquals(2, tree.size)
        assertEquals('m', tree.min())
        assertEquals('n', tree.max())
        tree.pollMin()
        assertEquals(1, tree.size)
        assertEquals('n', tree.min())
        assertEquals('n', tree.max())
        tree.pollMin()
        assertTrue(tree.isEmpty())
    }

    @Test
    internal fun `remove test`() {
        val tree = BinarySearchTree<Int, String>()
        for (i in 0..30) {
            tree.add(i, (i * i).toString())
        }

        for (i in 0..30) {
            assertEquals((i * i).toString(), tree[i])
        }

        var counter = 0
        for ((key, value) in tree) {
            assertEquals(counter, key)
            assertEquals((counter * counter).toString(), value)
            counter++
        }

        tree.remove(15)
        tree.remove(0)
        tree.remove(30)
        assertEquals(1, tree.min())
        assertEquals(29, tree.max())
        tree.remove(14)
        tree.remove(16)
        tree.remove(1)
        tree.remove(29)
        assertEquals(2, tree.min())
        assertEquals(28, tree.max())
        tree.remove(13)
        tree.remove(17)
        tree.remove(2)
        tree.remove(28)
        assertEquals(3, tree.min())
        assertEquals(27, tree.max())
        tree.remove(12)
        tree.remove(18)
        tree.remove(3)
        tree.remove(27)
        assertEquals(4, tree.min())
        assertEquals(26, tree.max())
        tree.remove(11)
        tree.remove(19)
        tree.remove(4)
        tree.remove(26)
        assertEquals(5, tree.min())
        assertEquals(25, tree.max())
        assertEquals(12, tree.size)

        assertEquals("25", tree[5])
        assertEquals("36", tree[6])
        assertEquals("49", tree[7])
        assertEquals("64", tree[8])
        assertEquals("81", tree[9])
        assertEquals("100", tree[10])
        assertEquals("400", tree[20])
        assertEquals("441", tree[21])
        assertEquals("484", tree[22])
        assertEquals("529", tree[23])
        assertEquals("576", tree[24])
        assertEquals("625", tree[25])
    }

    @Test
    internal fun `remove in empty tree test`() {
        val tree = BinarySearchTree<Int, String>()
        assertThrows<NoSuchElementException> {
            tree.remove(2)
        }
    }

    @Test
    internal fun `contains key test`() {
        val tree = BinarySearchTree<Int, String>()
        assertThat(tree.containsKey(0)).isFalse
        tree.add(23, "w")
        assertThat(tree.containsKey(23)).isTrue
    }

    @Test
    internal fun `contains value test`() {
        val tree = BinarySearchTree<Int, String>()
        assertThat(tree.containsValue("")).isFalse
        tree.add(1, "q")
        assertThat(tree.containsValue("q")).isTrue
    }

    @Test
    internal fun `poll min test`() {
        assertThrows<NoSuchElementException> {
            val tree = BinarySearchTree<Int, String>()
            tree.pollMin()
        }
    }

    @Test
    internal fun `poll max test`() {
        assertThrows<NoSuchElementException> {
            val tree = BinarySearchTree<Int, String>()
            tree.pollMax()
        }
    }

    @Test
    internal fun `get min test`() {
        assertThrows<NoSuchElementException> {
            val tree = BinarySearchTree<Int, String>()
            tree.min()
        }
    }

    @Test
    internal fun `get max test`() {
        assertThrows<NoSuchElementException> {
            val tree = BinarySearchTree<Int, String>()
            tree.max()
        }
    }

    @Test
    internal fun `get value test`() {
        val tree = BinarySearchTree<Int, String>()
        assertThat(tree[0]).isNull()
    }

    @Test
    internal fun `remove left test`() {
        val tree = BinarySearchTree<Int, TestData>()
        tree.add(1, TestData("q"))
        tree.add(4, TestData("q"))
        tree.add(18, TestData("q"))
        tree.add(20, TestData("q"))
        tree.add(25, TestData("q"))
        tree.add(40, TestData("q"))
        tree.add(45, TestData("q"))
        tree.add(70, TestData("q"))
        tree.add(77, TestData("q"))
        tree.add(88, TestData("q"))
        tree.add(105, TestData("q"))
        tree.remove(25)
        tree.remove(1)
        tree.remove(20)
        tree.remove(77)
        assertThat(tree[25]).isNull()
    }

    private data class TestData(val name: String)
}
