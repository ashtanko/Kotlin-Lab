/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.datastructures

/**
 * A Cartesian tree is a binary tree derived from a sequence of numbers; it can be uniquely defined from the properties
 * that it is heap-ordered and that a symmetric (in-order) traversal of the tree returns the original sequence.
 * This implementation builds a Cartesian tree from a given sequence of integers.
 * The Cartesian tree is built using a stack-based algorithm that maintains the heap property.
 * The time complexity is O(N) for building the tree.
 * The space complexity is O(N) for the stack.
 * The in-order and pre-order traversals of the tree can be obtained in O(N) time.
 * The space complexity is O(N) for the traversals.
 * The Cartesian tree can be used to solve a variety of problems, including finding the maximum area of a histogram.
 *
 * Tree Construction:
 * - Uses a stack to maintain a list of nodes in the tree.
 * - For each number in the sequence:
 *     - Pop elements from the stack that are greater than the current number to maintain the heap property.
 *     - Create a new node for the current number, attaching the last popped node as its left child.
 *     - Attach the new node as the right child of the last node in the stack (if any).
 *
 * @property value The value of the node.
 * @property left The left child of the node.
 * @property right The right child of the node.
 */
interface CartesianTree {
    val value: Int
    var left: CartesianTree?
    var right: CartesianTree?

    /**
     * Perform an in-order traversal of the Cartesian tree.
     * The in-order traversal of a Cartesian tree returns the original sequence of integers.
     *
     * The time complexity is O(N) for the traversal.
     * The space complexity is O(N) for the traversal.
     *
     * @return The in-order traversal of the Cartesian tree.
     */
    fun inOrderTraversal(): List<Int>

    /**
     * Perform a pre-order traversal of the Cartesian tree.
     * The pre-order traversal of a Cartesian tree returns the sequence of integers in the order they were inserted.
     *
     * The time complexity is O(N) for the traversal.
     * The space complexity is O(N) for the traversal.
     *
     * @return The pre-order traversal of the Cartesian tree.
     */
    fun preOrderTraversal(): List<Int>
}

class CartesianTreeImpl(
    override val value: Int,
    override var left: CartesianTree? = null,
    override var right: CartesianTree? = null,
) : CartesianTree {
    companion object {
        /**
         * Build a Cartesian tree from the given sequence of integers.
         * The Cartesian tree is built using a stack-based algorithm that maintains the heap property.
         *
         * The time complexity is O(N) for building the tree.
         * The space complexity is O(N) for the stack.
         *
         * @param sequence The sequence of integers to build the Cartesian tree from.
         * @return The root node of the Cartesian tree.
         */
        fun build(sequence: IntArray): CartesianTree? {
            if (sequence.isEmpty()) return null

            val stack = mutableListOf<CartesianTree>()
            for (num in sequence) {
                var lastPopped: CartesianTree? = null

                // Maintain the heap property by popping larger elements
                while (stack.isNotEmpty() && stack.last().value > num) {
                    lastPopped = stack.removeAt(stack.size - 1)
                }

                // Create a new node for the current number
                val newNode: CartesianTree = CartesianTreeImpl(num)

                // Set the last popped node as the left child
                if (lastPopped != null) {
                    newNode.left = lastPopped
                }

                // Set the new node as the right child of the last node in the stack
                if (stack.isNotEmpty()) {
                    stack.last().right = newNode
                }

                // Push the new node onto the stack
                stack.add(newNode)
            }

            // The root of the tree is the first element in the stack
            return stack.firstOrNull()
        }
    }

    override fun inOrderTraversal(): List<Int> {
        val result = mutableListOf<Int>()
        left?.inOrderTraversal()?.let { result.addAll(it) }
        result.add(value)
        right?.inOrderTraversal()?.let { result.addAll(it) }
        return result
    }

    override fun preOrderTraversal(): List<Int> {
        val result = mutableListOf<Int>()
        result.add(value)
        left?.preOrderTraversal()?.let { result.addAll(it) }
        right?.preOrderTraversal()?.let { result.addAll(it) }
        return result
    }
}
