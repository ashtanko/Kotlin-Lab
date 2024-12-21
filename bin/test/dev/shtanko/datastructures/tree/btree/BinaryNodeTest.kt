/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.datastructures.tree.btree

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BinaryNodeTest {

    @Test
    internal fun `b tree test`() {
        val zero = BinaryNode(0)
        val one = BinaryNode(1)
        val five = BinaryNode(5)
        val seven = BinaryNode(7)
        val eight = BinaryNode(8)
        val nine = BinaryNode(9)

        seven.leftChild = one
        one.leftChild = zero
        one.rightChild = five
        seven.rightChild = nine
        nine.leftChild = eight

        val root = seven
        assertThat(root.value).isEqualTo(7)
        assertThat(root.leftChild?.value).isEqualTo(1)
        println(root)
    }

    @Test
    internal fun `b tree 2 test`() {
        val root = BinaryNode(25).apply {
            leftChild = BinaryNode(20).apply {
                leftChild = BinaryNode(10).apply {
                    leftChild = BinaryNode(5).apply {
                        leftChild = BinaryNode(1)
                        rightChild = BinaryNode(8)
                    }
                    rightChild = BinaryNode(12)
                }
                rightChild = BinaryNode(22)
            }
            rightChild = BinaryNode(36).apply {
                rightChild = BinaryNode(40).apply {
                    rightChild = BinaryNode(48).apply {
                        rightChild = BinaryNode(50)
                        leftChild = BinaryNode(45)
                    }
                    leftChild = BinaryNode(38)
                }
                leftChild = BinaryNode(30).apply {
                    leftChild = BinaryNode(28)
                }
            }
        }
        assertThat(root.value).isEqualTo(25)
        println(root)
    }

    @Test
    internal fun `b tree string test`() {
        val root = BinaryNode("H").apply {
            leftChild = BinaryNode("D").apply {
                leftChild = BinaryNode("B").apply {
                    leftChild = BinaryNode("A")
                    rightChild = BinaryNode("C")
                }
                rightChild = BinaryNode("F").apply {
                    leftChild = BinaryNode("E")
                    rightChild = BinaryNode("G")
                }
            }
            rightChild = BinaryNode("P").apply {
                leftChild = BinaryNode("K").apply {
                    leftChild = BinaryNode("I")
                    rightChild = BinaryNode("N")
                }
                rightChild = BinaryNode("U").apply {
                    leftChild = BinaryNode("R")
                    rightChild = BinaryNode("Y")
                }
            }
        }
        assertThat(root.value).isEqualTo("H")
        println(root)
    }

    @Test
    internal fun `traverse in order test`() {
        val root = BinaryNode(7).apply {
            leftChild = BinaryNode(1).apply {
                leftChild = BinaryNode(0)
                rightChild = BinaryNode(5)
            }
            rightChild = BinaryNode(9).apply {
                rightChild = BinaryNode(8)
            }
        }
        val list: MutableList<Int> = ArrayList()
        root.traverseInOrder(list::add)
        val expected = listOf(0, 1, 5, 7, 8, 9)
        assertThat(list).containsAll(expected)
    }

    @Test
    internal fun `print tree test`() {
        val root = BinaryNode(7)
        assertThat(root.toString()).isEqualTo("7\n")
    }

    @Test
    internal fun `list to tree test`() {
        val list = listOf(1, 2, 3, 4, 5, 6, 6, 6, 6, 6)
        val root = list.toTree()
        println(root)
        assertThat(root?.value).isEqualTo(1)
    }

    @Test
    internal fun `to tree test`() {
        val list = listOf<String>()
        val root = list.toTree()
        assertThat(root).isNull()
    }
}
