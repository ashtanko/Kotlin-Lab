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
 * Persistent Segment Tree implementation that supports point updates and range queries.
 * This implementation is a simplified version of the persistent segment tree that only supports point updates and
 * range queries.
 *
 * @property size The size of the array
 *
 * Key Features:
 * - Point updates and range queries
 * - Supports any version query
 * - Time complexity: O(log N) for both updates and queries
 * - Space complexity: O(N log N)
 * - Useful for problems that require multiple versions of the segment tree
 * - Not suitable for problems that require lazy propagation
 * - Not suitable for problems that require range updates
 * - Not suitable for problems that require range maximum/minimum queries
 * - Not suitable for problems that require range updates and queries
 * - Not suitable for problems that require offline processing
 *
 * Usage:
 * This implementation supports point updates and range sum queries but can be extended to support other
 * operations (e.g., range minimum/maximum, lazy propagation).
 *
 * @see "https://cp-algorithms.com/data_structures/segment_tree.html"
 */
class PersistentSegmentTree(private val size: Int) {
    // Internal node class representing the segment tree nodes
    data class Node(val value: Int, val left: Node? = null, val right: Node? = null)

    private val roots = mutableListOf<Node>()

    init {
        // Initial empty version (all zeros)
        roots.add(build(0, size - 1))
    }

    /**
     * Build the segment tree for the given range [start, end].
     * This method is called only once during initialization.
     * The tree is built recursively using a top-down approach.
     * The leaf nodes are initialized with zeros.
     * The internal nodes are the sum of their children.
     * The tree is 0-indexed.
     * The tree is built for the range [start, end].
     * The time complexity is O(N).
     * The space complexity is O(N).
     *
     * @param start The start index of the range.
     * @param end The end index of the range.
     * @return The root node of the segment tree.
     */
    private fun build(start: Int, end: Int): Node {
        if (start == end) return Node(0) // Leaf node
        val mid = (start + end) / 2
        val leftChild = build(start, mid)
        val rightChild = build(mid + 1, end)
        return Node(0, leftChild, rightChild)
    }

    /**
     * Update the value at index [idx] in the given version [version] to [value].
     * This method creates a new version of the segment tree with the updated value.
     * The tree is updated recursively using a top-down approach.
     * The time complexity is O(log N).
     * The space complexity is O(log N).
     *
     * @param version The version of the segment tree to update.
     * @param idx The index to update.
     * @param value The new value to set.
     *
     * @return The version index of the new root.
     */
    fun update(version: Int, idx: Int, value: Int): Int {
        val newRoot = update(roots[version], 0, size - 1, idx, value)
        roots.add(newRoot)
        return roots.size - 1 // Return the version index of the new root
    }

    /**
     * Update the value at index [idx] in the given node [node] to [value].
     * This method is called recursively to update the tree.
     * The tree is updated using a top-down approach.
     * The time complexity is O(log N).
     * The space complexity is O(log N).
     *
     * @param node The current node in the segment tree.
     * @param start The start index of the range.
     * @param end The end index of the range.
     * @param idx The index to update.
     * @param value The new value to set.
     *
     * @return The updated node.
     */
    private fun update(node: Node, start: Int, end: Int, idx: Int, value: Int): Node {
        if (start == end) return Node(value) // Create new leaf node
        val mid = (start + end) / 2
        return if (idx <= mid) {
            val leftChild = update(node.left!!, start, mid, idx, value)
            Node(leftChild.value + (node.right?.value ?: 0), leftChild, node.right)
        } else {
            val rightChild = update(node.right!!, mid + 1, end, idx, value)
            Node((node.left?.value ?: 0) + rightChild.value, node.left, rightChild)
        }
    }

    /**
     * Query the sum of the elements in the range [l, r] in the given version [version].
     * This method returns the sum of the elements in the range [l, r] in the specified version.
     * The time complexity is O(log N).
     * The space complexity is O(1).
     *
     * @param version The version of the segment tree to query.
     * @param l The left index of the range.
     * @param r The right index of the range.
     *
     * @return The sum of the elements in the range [l, r].
     */
    fun query(version: Int, l: Int, r: Int): Int {
        return query(roots[version], 0, size - 1, l, r)
    }

    /**
     * Query the sum of the elements in the range [l, r] in the given node [node].
     * This method is called recursively to query the tree.
     * The time complexity is O(log N).
     * The space complexity is O(1).
     *
     * @param node The current node in the segment tree.
     * @param start The start index of the range.
     * @param end The end index of the range.
     * @param l The left index of the query range.
     * @param r The right index of the query range.
     *
     * @return The sum of the elements in the range [l, r].
     */
    private fun query(node: Node, start: Int, end: Int, l: Int, r: Int): Int {
        if (r < start || l > end) return 0 // Out of range
        if (l <= start && end <= r) return node.value // Fully in range
        val mid = (start + end) / 2
        val leftSum = node.left?.let { query(it, start, mid, l, r) } ?: 0
        val rightSum = node.right?.let { query(it, mid + 1, end, l, r) } ?: 0
        return leftSum + rightSum
    }
}
