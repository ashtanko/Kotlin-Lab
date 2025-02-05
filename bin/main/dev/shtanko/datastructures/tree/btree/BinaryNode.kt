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

import java.util.LinkedList
import java.util.Queue

/**
 * A typealias representing a visitor function that accepts a single parameter of type T and returns Unit.
 */
typealias Visitor<T> = (T) -> Unit

/**
 * Internal class representing a binary node in a binary tree.
 *
 * @property value The value stored in the node.
 * @property leftChild The left child node.
 * @property rightChild The right child node.
 */
internal class BinaryNode<T>(val value: T) {
    var leftChild: BinaryNode<T>? = null
    var rightChild: BinaryNode<T>? = null

    /**
     * Returns a string representation of the binary node and its children in a tree diagram format.
     *
     * @param node The binary node to generate a diagram for.
     * @param top Prefix for the top line of the diagram.
     * @param root Prefix for the root line of the diagram.
     * @param bottom Prefix for the bottom line of the diagram.
     * @return A string containing the tree diagram.
     */
    override fun toString(): String = diagram(this)

    /**
     * Traverses the binary tree in-order and applies the given visitor function to each node's value.
     *
     * @param visit The visitor function to apply to each node's value.
     */
    fun traverseInOrder(visit: Visitor<T>) {
        leftChild?.traverseInOrder(visit)
        visit(value)
        rightChild?.traverseInOrder(visit)
    }

    private fun diagram(
        node: BinaryNode<T>?,
        top: String = "",
        root: String = "",
        bottom: String = "",
    ): String {
        return node?.let {
            if (node.leftChild == null && node.rightChild == null) {
                "$root${node.value}\n"
            } else {
                val dr = diagram(
                    node.rightChild,
                    "$top ",
                    "$top┌──",
                    "$top│ ",
                )

                val dl = diagram(
                    node.leftChild,
                    "$bottom│ ",
                    "$bottom└──",
                    "$bottom ",
                )

                dr.plus(root).plus("${node.value}\n").plus(dl)
            }
        } ?: "${root}null\n"
    }
}

/**
 * Converts a list of elements into a binary tree structure.
 *
 * @receiver The list of elements to convert.
 * @return The root node of the resulting binary tree, or null if the list is empty.
 */
internal fun <T> List<T>.toTree(): BinaryNode<T>? {
    if (isEmpty()) return null
    val root = BinaryNode(first())
    val queue: Queue<BinaryNode<T>> = LinkedList()
    queue.add(root)
    for (i in 1 until this.size) {
        val node = queue.peek()
        if (node.leftChild == null) {
            node.leftChild = BinaryNode(this[i])
            queue.add(node.leftChild)
        } else if (node.rightChild == null) {
            node.rightChild = BinaryNode(this[i])
            queue.add(node.rightChild)
            queue.remove()
        }
    }
    return root
}
