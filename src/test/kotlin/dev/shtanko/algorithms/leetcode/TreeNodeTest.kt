/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.algorithms.leetcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TreeNodeTest {

    @Test
    fun `get tree node height with one item`() {
        val tree = TreeNode(1)
        assertThat(tree.height()).isEqualTo(0)
    }

    @Test
    fun `get tree node height with several items`() {
        val tree = TreeNode(6).apply {
            right = TreeNode(7)
            left = TreeNode(5)
        }
        assertThat(tree.height()).isEqualTo(1)
    }

    @Test
    fun `get tree node height with many items`() {
        val tree = TreeNode(1).apply {
            left = TreeNode(2).apply {
                left = TreeNode(4)
                right = TreeNode(5)
            }
            right = TreeNode(3).apply {
                left = TreeNode(6)
            }
        }
        assertThat(tree.height()).isEqualTo(2)
    }

    @Test
    fun `get tree node height with many items 2`() {
        val tree = TreeNode(4).apply {
            left = TreeNode(1).apply {
                left = TreeNode(2).apply {
                    left = TreeNode(3)
                    right = TreeNode(1)
                }
            }
            right = TreeNode(1).apply {
                right = TreeNode(6).apply {
                    left = TreeNode(5)
                }
            }
        }
        assertThat(tree.height()).isEqualTo(3)
    }

    @Test
    fun `prettyPrinted test`() {
        val tree = TreeNode(6).apply {
            right = TreeNode(7)
            left = TreeNode(5)
        }
        tree.prettyPrint()
    }

    @Test
    fun `pretty printed test`() {
        val tree = TreeNode(3).apply {
            left = TreeNode(9)
            right = TreeNode(20).apply {
                left = TreeNode(15)
                right = TreeNode(7)
            }
        }
        val actual = tree.prettyPrint()

        val expected = "            3           \n" +
            "      ┌─────┴─────┐     \n" +
            "      9          20     \n" +
            "               ┌──┴──┐  \n" +
            "              15     7  \n"
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `pretty printed one value test`() {
        val tree = TreeNode(3)
        val actual = tree.prettyPrint()
        val expected = "   3  \n"
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `pretty printed two values test`() {
        val tree = TreeNode(3).apply {
            left = TreeNode(9)
        }
        val actual = tree.prettyPrint()
        println(actual)
        val expected = "      3     \n" +
            "   ┌──┘     \n" +
            "   9        \n"
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `clone single node test`() {
        val original = TreeNode(5)
        val cloned = original.clone()

        Assertions.assertEquals(original.value, cloned?.value)
        Assertions.assertEquals(original.left, cloned?.left)
        Assertions.assertEquals(original.right, cloned?.right)
    }

    @Test
    fun `clone tree test`() {
        val original = TreeNode(1)
        original.left = TreeNode(2)
        original.right = TreeNode(3)
        original.left?.left = TreeNode(4)
        original.left?.right = TreeNode(5)
        original.right?.left = TreeNode(6)
        original.right?.right = TreeNode(7)

        val cloned = original.clone()

        assertTreesEqual(original, cloned)
    }

    @Test
    fun `clone single node with offset test`() {
        val original = TreeNode(5)
        val offset = 10
        val cloned = original.clone(offset)

        Assertions.assertEquals(original.value + offset, cloned?.value)
        Assertions.assertEquals(original.left, cloned?.left)
        Assertions.assertEquals(original.right, cloned?.right)
    }

    @Test
    fun `clone tree with offset test`() {
        val original = TreeNode(1)
        original.left = TreeNode(2)
        original.right = TreeNode(3)
        original.left?.left = TreeNode(4)
        original.left?.right = TreeNode(5)
        original.right?.left = TreeNode(6)
        original.right?.right = TreeNode(7)
        val offset = 10

        val cloned = original.clone(offset)

        assertTreesEqualWithOffset(original, cloned, offset)
    }

    @Test
    fun `test empty array returns null`() {
        val result = arrayOf<Int?>().buildTree()
        assertThat(result).isNull()
    }

    @Test
    fun `test array with one element returns single node`() {
        val result = arrayOf<Int?>(1).buildTree()
        assertThat(result).isNotNull
        assertThat(result?.value).isEqualTo(1)
        assertThat(result?.left).isNull()
        assertThat(result?.right).isNull()
    }

    @Test
    fun `test array with two elements creates correct tree`() {
        val result = arrayOf<Int?>(1, 2).buildTree()
        assertThat(result).isNotNull
        assertThat(result?.value).isEqualTo(1)
        assertThat(result?.left?.value).isEqualTo(2)
        assertThat(result?.right).isNull()
    }

    @Test
    fun `test array with three elements creates correct tree`() {
        val result = arrayOf<Int?>(1, 2, 3).buildTree()
        assertThat(result).isNotNull
        assertThat(result?.value).isEqualTo(1)
        assertThat(result?.left?.value).isEqualTo(2)
        assertThat(result?.right?.value).isEqualTo(3)
    }

    @Test
    fun `test array with nulls creates correct tree`() {
        val result = arrayOf<Int?>(1, 2, 3, 4, 5, 6, 7).buildTree()
        assertThat(result).isNotNull
        assertThat(result?.value).isEqualTo(1)
        assertThat(result?.left?.value).isEqualTo(2)
        assertThat(result?.right?.value).isEqualTo(3)
        assertThat(result?.left?.left?.value).isEqualTo(4)
        assertThat(result?.left?.right?.value).isEqualTo(5)
        assertThat(result?.right?.left?.value).isEqualTo(6)
        assertThat(result?.right?.right?.value).isEqualTo(7)
        print(result?.prettyPrint())
    }

    @Test
    fun `test array with missing children`() {
        val result = arrayOf<Int?>(1, 2, 3, 4, null, 6, null).buildTree()
        assertThat(result).isNotNull
        assertThat(result?.value).isEqualTo(1)
        assertThat(result?.left?.value).isEqualTo(2)
        assertThat(result?.right?.value).isEqualTo(3)
        assertThat(result?.left?.left?.value).isEqualTo(4)
        assertThat(result?.left?.right).isNull()
        assertThat(result?.right?.left?.value).isEqualTo(6)
        assertThat(result?.right?.right).isNull()
    }

    @Test
    fun `test specific tree construction`() {
        val specificTreeArray = arrayOf<Int?>(1, 2, 3, 4, 5, 6, null, null, null, 7, 8)

        val result = specificTreeArray.buildTree()

        assertThat(result).isNotNull
        assertThat(result?.value).isEqualTo(1)
        assertThat(result?.left?.value).isEqualTo(2)
        assertThat(result?.right?.value).isEqualTo(3)

        // Check left subtree of 2
        assertThat(result?.left?.left?.value).isEqualTo(4)
        assertThat(result?.left?.right?.value).isEqualTo(5)

        // Check right subtree of 1
        assertThat(result?.right?.value).isEqualTo(3)
        assertThat(result?.right?.left?.value).isEqualTo(6)

        // Check children of 5
        assertThat(result?.left?.right?.left?.value).isEqualTo(7)
        assertThat(result?.left?.right?.right?.value).isEqualTo(8)
        print(result?.prettyPrint())
    }

    @Test
    fun `test specific tree construction with deeper branches`() {
        val specificTreeArray = arrayOf<Int?>(1, 3, 2, null, 6, 4, 5, null, null, null, null, 8, 7)

        val result = specificTreeArray.buildTree()

        assertThat(result).isNotNull
        assertThat(result?.value).isEqualTo(1)
        assertThat(result?.left?.value).isEqualTo(3)
        assertThat(result?.right?.value).isEqualTo(2)

        print(result?.prettyPrint())
    }

    private fun assertTreesEqual(expected: TreeNode?, actual: TreeNode?) {
        if (expected == null && actual == null) return
        Assertions.assertEquals(expected?.value, actual?.value)
        assertTreesEqual(expected?.left, actual?.left)
        assertTreesEqual(expected?.right, actual?.right)
    }

    private fun assertTreesEqualWithOffset(expected: TreeNode?, actual: TreeNode?, offset: Int) {
        if (expected == null && actual == null) return
        Assertions.assertEquals(expected?.value?.plus(offset), actual?.value)
        assertTreesEqualWithOffset(expected?.left, actual?.left, offset)
        assertTreesEqualWithOffset(expected?.right, actual?.right, offset)
    }
}
