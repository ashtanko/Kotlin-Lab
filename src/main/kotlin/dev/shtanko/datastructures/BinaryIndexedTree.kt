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

/**
 * A Binary Indexed Tree (Fenwick Tree) implementation for point updates and range queries.
 * The tree is 1-indexed, so the first element is at index 1.
 *
 * @property size The size of the tree.
 */
class BinaryIndexedTree(private val size: Int) {
    private val tree = IntArray(size + 1) { 0 }

    /**
     * Update the element at index `idx` by adding `delta` to it.
     *
     * @param idx The index of the element to update.
     * @param delta The value to add to the element at index `idx`.
     */
    fun update(idx: Int, delta: Int) {
        var index = idx
        while (index <= size) {
            tree[index] += delta
            index += index and -index // Move to the next index
        }
    }

    /**
     * Get the sum of the elements from index 1 to `idx`.
     *
     * @param idx The index of the element to query.
     * @return The sum of the elements from index 1 to `idx`.
     */
    fun query(idx: Int): Int {
        var sum = 0
        var index = idx
        while (index > 0) {
            sum += tree[index]
            index -= index and -index // Move to the parent
        }
        return sum
    }

    /**
     * Get the sum of the elements in the range [left, right].
     *
     * @param left The left index of the range.
     * @param right The right index of the range.
     * @return The sum of the elements in the range [left, right].
     */
    fun rangeQuery(left: Int, right: Int): Int {
        return query(right) - query(left - 1)
    }
}
