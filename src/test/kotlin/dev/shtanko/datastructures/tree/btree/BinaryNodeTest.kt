/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
    internal fun `b tree 3 massive test`() {
        val root = BinaryNode(100).apply {
            leftChild = BinaryNode(50).apply {
                leftChild = BinaryNode(25).apply {
                    leftChild = BinaryNode(12).apply {
                        leftChild = BinaryNode(6).apply {
                            leftChild = BinaryNode(3)
                            rightChild = BinaryNode(9)
                        }
                        rightChild = BinaryNode(18).apply {
                            leftChild = BinaryNode(15)
                            rightChild = BinaryNode(21)
                        }
                    }
                    rightChild = BinaryNode(30).apply {
                        leftChild = BinaryNode(28)
                        rightChild = BinaryNode(35)
                    }
                }
                rightChild = BinaryNode(75).apply {
                    leftChild = BinaryNode(60).apply {
                        leftChild = BinaryNode(55)
                        rightChild = BinaryNode(65)
                    }
                    rightChild = BinaryNode(85).apply {
                        leftChild = BinaryNode(80)
                        rightChild = BinaryNode(90)
                    }
                }
            }
            rightChild = BinaryNode(150).apply {
                leftChild = BinaryNode(125).apply {
                    leftChild = BinaryNode(110).apply {
                        leftChild = BinaryNode(105)
                        rightChild = BinaryNode(115)
                    }
                    rightChild = BinaryNode(135).apply {
                        leftChild = BinaryNode(130)
                        rightChild = BinaryNode(140)
                    }
                }
                rightChild = BinaryNode(175).apply {
                    leftChild = BinaryNode(160).apply {
                        leftChild = BinaryNode(155)
                        rightChild = BinaryNode(165)
                    }
                    rightChild = BinaryNode(190).apply {
                        leftChild = BinaryNode(185)
                        rightChild = BinaryNode(195).apply {
                            rightChild = BinaryNode(200).apply {
                                rightChild = BinaryNode(210)
                            }
                        }
                    }
                }
            }
        }

        assertThat(root.value).isEqualTo(100)
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
    internal fun `very big binary tree string test`() {
        val root = BinaryNode("H").apply {
            leftChild = BinaryNode("D").apply {
                leftChild = BinaryNode("B").apply {
                    leftChild = BinaryNode("A1").apply {
                        leftChild = BinaryNode("A0")
                        rightChild = BinaryNode("A2")
                    }
                    rightChild = BinaryNode("C1").apply {
                        leftChild = BinaryNode("C0")
                        rightChild = BinaryNode("C2")
                    }
                }
                rightChild = BinaryNode("F").apply {
                    leftChild = BinaryNode("E1").apply {
                        leftChild = BinaryNode("E0")
                        rightChild = BinaryNode("E2")
                    }
                    rightChild = BinaryNode("G1").apply {
                        leftChild = BinaryNode("G0")
                        rightChild = BinaryNode("G2")
                    }
                }
            }
            rightChild = BinaryNode("P").apply {
                leftChild = BinaryNode("K").apply {
                    leftChild = BinaryNode("I1").apply {
                        leftChild = BinaryNode("I0")
                        rightChild = BinaryNode("I2")
                    }
                    rightChild = BinaryNode("N1").apply {
                        leftChild = BinaryNode("M0")
                        rightChild = BinaryNode("O0")
                    }
                }
                rightChild = BinaryNode("U").apply {
                    leftChild = BinaryNode("R1").apply {
                        leftChild = BinaryNode("Q0")
                        rightChild = BinaryNode("S0")
                    }
                    rightChild = BinaryNode("Y").apply {
                        leftChild = BinaryNode("W1").apply {
                            leftChild = BinaryNode("V0")
                            rightChild = BinaryNode("X0")
                        }
                        rightChild = BinaryNode("Z1").apply {
                            leftChild = BinaryNode("Z0")
                            rightChild = BinaryNode("Z2")
                        }
                    }
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
