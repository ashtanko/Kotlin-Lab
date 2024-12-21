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

/**
 * A Binary Search Tree implementation of the Map interface.
 *
 * @param K The key type, must be Comparable.
 * @param V The value type.
 */
class BinarySearchTree<K : Comparable<K>, V> : Map<K, V> {

    /**
     * Represents a node in the Binary Search Tree.
     *
     * @param key The key of the node.
     * @param value The value associated with the key.
     * @param left The left child node.
     * @param right The right child node.
     * @param size The number of nodes in the subtree rooted at this node.
     */
    data class Node<K, V>(
        override val key: K,
        override var value: V,
        var left: Node<K, V>? = null,
        var right: Node<K, V>? = null,
        var size: Int = 1,
    ) : Map.Entry<K, V>

    private var root: Node<K, V>? = null

    override val size: Int
        get() = size(root)

    override val entries: Set<Map.Entry<K, V>>
        get() {
            val set = mutableSetOf<Node<K, V>>()
            inorder(root) { set.add(it.copy()) }
            return set
        }

    override val keys: Set<K>
        get() {
            val set = mutableSetOf<K>()
            inorder(root) { set.add(it.key) }
            return set
        }

    override val values: Collection<V>
        get() {
            val queue = mutableListOf<V>()
            inorder(root) { queue.add(it.value) }
            return queue
        }

    override fun containsKey(key: K): Boolean {
        return get(key) != null
    }

    override fun containsValue(value: V): Boolean {
        return any { it.value == value }
    }

    override fun get(key: K): V? {
        var x = root
        while (x != null) {
            x = when {
                key < x.key -> x.left
                key > x.key -> x.right
                else -> return x.value
            }
        }
        return null
    }

    override fun isEmpty(): Boolean = size == 0

    /**
     * Returns the height of the binary search tree.
     *
     * @return The height of the binary search tree.
     */
    fun height(): Int {
        return height(root)
    }

    /**
     * Returns the minimum key in the binary search tree.
     *
     * @return The minimum key.
     * @throws NoSuchElementException if the tree is empty.
     */
    fun min(): K {
        return min(root).key
    }

    /**
     * Adds a new key-value pair to the binary search tree.
     *
     * @param key The key to be added.
     * @param value The value associated with the key.
     */
    fun add(key: K, value: V) {
        root = add(key, value, root)
    }

    /**
     * Returns the maximum key in the binary search tree.
     *
     * @return The maximum key.
     * @throws NoSuchElementException if the tree is empty.
     */
    fun max(): K {
        return max(root).key
    }

    /**
     * Removes a key and its associated value from the binary search tree.
     *
     * @param key The key to be removed.
     */
    fun remove(key: K) {
        root = remove(key, root)
    }

    /**
     * Removes the minimum key and its associated value from the binary search tree.
     *
     * @throws NoSuchElementException if the tree is empty.
     */
    fun pollMin() {
        if (root == null) throw NoSuchElementException()
        root = pollMin(root!!)
    }

    /**
     * Removes the maximum key and its associated value from the binary search tree.
     *
     * @throws NoSuchElementException if the tree is empty.
     */
    fun pollMax() {
        if (root == null) throw NoSuchElementException()
        root = pollMax(root!!)
    }

    /**
     * Returns the height of the binary search tree.
     *
     * @param x The root node of the subtree.
     * @return The height of the subtree rooted at node x.
     */
    private fun height(x: Node<K, V>?): Int {
        if (x == null) return 0
        return maxOf(height(x.left), height(x.right)) + 1
    }

    /**
     * Removes the maximum key and its associated value from the binary search tree.
     *
     * @param x The root node of the subtree.
     * @return The root node of the modified subtree after removal.
     * @throws NoSuchElementException if the subtree is empty.
     */
    private fun pollMax(x: Node<K, V>): Node<K, V>? {
        if (x.right == null) return x.left
        x.right = pollMax(x.right!!)
        x.size = size(x.left) + size(x.right) + 1
        return x
    }

    /**
     * Returns the maximum node in the binary search tree.
     *
     * @param node The root node of the subtree.
     * @return The maximum node in the subtree.
     * @throws NoSuchElementException if the subtree is empty.
     */
    private fun max(node: Node<K, V>?): Node<K, V> {
        if (node == null) throw NoSuchElementException()
        var x: Node<K, V> = node
        while (x.right != null) {
            x = x.right!!
        }
        return x
    }

    /**
     * Returns the minimum node in the binary search tree.
     *
     * @param node The root node of the subtree.
     * @return The minimum node in the subtree.
     * @throws NoSuchElementException if the subtree is empty.
     */
    private fun min(node: Node<K, V>?): Node<K, V> {
        if (node == null) throw NoSuchElementException()
        var x: Node<K, V> = node
        while (x.left != null) {
            x = x.left!!
        }
        return x
    }

    /**
     * Removes a key and its associated value from the binary search tree.
     *
     * @param key The key to be removed.
     * @param root The root node of the subtree.
     * @return The root node of the modified subtree after removal.
     * @throws NoSuchElementException if the key is not found in the subtree.
     */
    private fun remove(key: K, root: Node<K, V>?): Node<K, V>? {
        var x: Node<K, V> = root ?: throw NoSuchElementException()
        when {
            key < x.key -> x.left = remove(key, x.left)
            key > x.key -> x.right = remove(key, x.right)
            else -> {
                if (x.left == null) return x.right
                if (x.right == null) return x.left
                val tmp = x
                tmp.right?.let {
                    pollMin(it)?.let {
                        x = it
                        x.right = pollMax(it)
                        x.left = tmp.left
                    }
                }
                x = pollMin(tmp.right!!)!!
                x.right = min(tmp.right)
                x.left = tmp.left
            }
        }
        x.size = size(x.left) + size(x.right) + 1
        return x
    }

    /**
     * Removes the minimum key and its associated value from the binary search tree.
     *
     * @param x The root node of the subtree.
     * @return The root node of the modified subtree after removal.
     * @throws NoSuchElementException if the subtree is empty.
     */
    private fun pollMin(x: Node<K, V>): Node<K, V>? {
        if (x.left == null) return x.right
        x.left = pollMin(x.left!!)
        x.size = size(x.left) + size(x.right) + 1
        return x
    }

    /**
     * Adds a new key-value pair to the binary search tree.
     *
     * @param key The key to be added.
     * @param value The value associated with the key.
     * @param x The root node of the subtree.
     * @return The root node of the modified subtree after addition.
     */
    private fun add(key: K, value: V, x: Node<K, V>?): Node<K, V> {
        if (x == null) return Node(key, value)
        when {
            key < x.key -> x.left = add(key, value, x.left)
            key > x.key -> x.right = add(key, value, x.right)
            else -> x.value = value
        }
        x.size = size(x.left) + size(x.right) + 1
        return x
    }

    /**
     * Returns the number of nodes in the subtree rooted at the given node.
     *
     * @param x The root node of the subtree.
     * @return The number of nodes in the subtree rooted at node x.
     */
    private fun size(x: Node<K, V>?): Int = x?.size ?: 0

    /**
     * Performs an inorder traversal of the binary search tree.
     *
     * @param x The root node of the subtree.
     * @param lambda The lambda function to apply to each node during traversal.
     */
    private fun inorder(x: Node<K, V>?, lambda: (Node<K, V>) -> Unit) {
        if (x == null) return
        inorder(x.left, lambda)
        lambda(x)
        inorder(x.right, lambda)
    }
}
