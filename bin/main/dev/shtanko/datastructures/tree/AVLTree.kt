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

import org.slf4j.LoggerFactory

/**
 * AVL Tree
 * @see <a href="https://www.geeksforgeeks.org/avl-tree-set-1-insertion/">leetcode page</a>
 */
class AVLTree {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(AVLTree::class.java)
    }

    data class Node(
        val key: Int,
        var height: Int = 1,
        var left: Node? = null,
        var right: Node? = null,
    )

    var root: Node? = null

    fun insert(node: Node?, key: Int): Node? {
        // 1.  Perform the normal BST insertion
        if (node == null) return Node(key)
        when {
            key < node.key -> node.left = insert(node.left, key)
            key > node.key -> node.right = insert(node.right, key)
            // Duplicate keys not allowed
            else -> return node
        }

        // 2. Update height of this ancestor node
        node.height = 1 + max(
            height(node.left),
            height(node.right),
        )

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced
         */
        val balance = getBalance(node)

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && key < node.left!!.key) return rightRotate(node)

        // Right Right Case
        if (balance < -1 && key > node.right!!.key) return leftRotate(node)

        // Left Right Case
        if (balance > 1 && key > node.left!!.key) {
            node.left = leftRotate(node.left!!)
            return rightRotate(node)
        }

        // Right Left Case
        if (balance < -1 && key < node.right!!.key) {
            node.right = rightRotate(node.right!!)
            return leftRotate(node)
        }

        // return the (unchanged) node pointer
        return node
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    fun preOrder(node: Node?) {
        if (node != null) {
            LOGGER.info("${node.key}")
            preOrder(node.left)
            preOrder(node.right)
        }
    }

    // Get Balance factor of node N
    private fun getBalance(n: Node?): Int {
        return if (n == null) 0 else height(n.left) - height(n.right)
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    private fun leftRotate(x: Node): Node? {
        val y = x.right
        val t2 = y?.left

        // Perform rotation
        y?.left = x
        x.right = t2

        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1
        y?.height = max(height(y?.left), height(y?.right)) + 1

        // Return new root
        return y
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    private fun rightRotate(y: Node): Node? {
        val x = y.left
        val t2 = x?.right

        // Perform rotation
        x?.right = y
        y.left = t2

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1
        x?.height = max(height(x?.left), height(x?.right)) + 1

        // Return new root
        return x
    }

    // A utility function to get maximum of two integers
    fun max(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    // A utility function to get the height of the tree
    private fun height(n: Node?): Int {
        if (n == null) {
            return 0
        }
        return n.height
    }
}
