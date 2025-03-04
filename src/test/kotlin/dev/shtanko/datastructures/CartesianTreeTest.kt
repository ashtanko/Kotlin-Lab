/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.datastructures

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CartesianTreeTest {
    @Test
    fun testEmptySequence() {
        val sequence = intArrayOf()
        val tree: CartesianTree? = CartesianTreeImpl.build(sequence)
        assertEquals(null, tree, "Tree should be null for an empty sequence.")
    }

    @Test
    fun testSingleElementSequence() {
        val sequence = intArrayOf(5)
        val tree: CartesianTree? = CartesianTreeImpl.build(sequence)

        assertEquals(5, tree?.value, "Root value should be the single element in the sequence.")
        assertEquals(null, tree?.left, "Left child should be null for a single element.")
        assertEquals(null, tree?.right, "Right child should be null for a single element.")

        assertEquals(
            sequence.toList(),
            tree?.inOrderTraversal(),
            "In-order traversal should match the original sequence.",
        )
        assertEquals(
            sequence.toList(),
            tree?.preOrderTraversal(),
            "Pre-order traversal should match the original sequence.",
        )
    }

    @Test
    fun testIncreasingSequence() {
        val sequence = intArrayOf(1, 2, 3, 4, 5)
        val tree: CartesianTree? = CartesianTreeImpl.build(sequence)

        assertEquals(
            sequence.toList(),
            tree?.inOrderTraversal(),
            "In-order traversal should match the original sequence.",
        )
        assertEquals(
            sequence.toList(),
            tree?.preOrderTraversal(),
            "Pre-order traversal should match the original sequence in this case.",
        )
    }

    @Test
    fun testDecreasingSequence() {
        val sequence = intArrayOf(5, 4, 3, 2, 1)
        val tree: CartesianTree? = CartesianTreeImpl.build(sequence)

        assertEquals(
            sequence.toList(),
            tree?.inOrderTraversal(),
            "In-order traversal should match the original sequence.",
        )
        assertEquals(
            listOf(1, 2, 3, 4, 5),
            tree?.preOrderTraversal(),
            "Pre-order traversal should show the structure of the tree.",
        )
    }

    @Test
    fun testRandomSequence() {
        val sequence = intArrayOf(3, 2, 6, 1, 9, 5)
        val tree: CartesianTree? = CartesianTreeImpl.build(sequence)

        assertEquals(
            sequence.toList(),
            tree?.inOrderTraversal(),
            "In-order traversal should match the original sequence.",
        )
        assertEquals(
            listOf(1, 2, 3, 6, 5, 9),
            tree?.preOrderTraversal(),
            "Pre-order traversal should reflect the correct tree structure.",
        )
    }

    @Test
    fun testDuplicateValues() {
        val sequence = intArrayOf(3, 3, 3, 3, 3)
        val tree: CartesianTree? = CartesianTreeImpl.build(sequence)

        assertEquals(
            sequence.toList(),
            tree?.inOrderTraversal(),
            "In-order traversal should match the original sequence.",
        )
        assertEquals(
            sequence.toList(),
            tree?.preOrderTraversal(),
            "Pre-order traversal should match the original sequence.",
        )
    }

    @Test
    fun testMixedSequence() {
        val sequence = intArrayOf(5, 1, 6, 2, 4, 3)
        val tree: CartesianTree? = CartesianTreeImpl.build(sequence)

        assertEquals(
            sequence.toList(),
            tree?.inOrderTraversal(),
            "In-order traversal should match the original sequence.",
        )
        assertEquals(
            listOf(1, 5, 2, 6, 3, 4),
            tree?.preOrderTraversal(),
            "Pre-order traversal should reflect the correct tree structure.",
        )
    }
}
