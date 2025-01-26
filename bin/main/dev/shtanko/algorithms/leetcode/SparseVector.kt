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

package dev.shtanko.algorithms.leetcode

/**
 * 1570. Dot Product of Two Sparse Vectors
 * @see <a href="https://leetcode.com/problems/dot-product-of-two-sparse-vectors/">Source</a>
 */
fun interface SparseVector<T> {
    fun dotProduct(vec: T): Int
}

/**
 * Approach 1: Non-efficient Array Approach
 * Time complexity: O(n).
 * Space complexity: O(1).
 */
class SparseVectorArray(val array: IntArray) : SparseVector<SparseVectorArray> {
    override fun dotProduct(vec: SparseVectorArray): Int {
        var result = 0

        for (i in array.indices) {
            result += array[i] * vec.array[i]
        }
        return result
    }
}

/**
 * Approach 2: Hash Set
 * Time complexity: O(n).
 * Space complexity: O(L).
 */
class SparseVectorHashSet(val array: IntArray) : SparseVector<SparseVectorHashSet> {

    // Map the index to value for all non-zero values in the vector
    private val mapping: MutableMap<Int, Int> = HashMap()

    init {
        for (i in array.indices) {
            if (array[i] != 0) {
                mapping[i] = array[i]
            }
        }
    }

    override fun dotProduct(vec: SparseVectorHashSet): Int {
        var result = 0
        for (key in mapping.keys) {
            if (vec.mapping.containsKey(key)) {
                result += this.mapping[key]!! * vec.mapping[key]!!
            }
        }
        return result
    }
}

/**
 * Approach 3: Index-Value Pairs
 */
class SparseVectorPairs(val array: IntArray) : SparseVector<SparseVectorPairs> {

    private val pairs: MutableList<IntArray> = ArrayList()

    init {
        for (i in array.indices) {
            if (array[i] != 0) {
                pairs.add(intArrayOf(i, array[i]))
            }
        }
    }

    override fun dotProduct(vec: SparseVectorPairs): Int {
        var result = 0
        var p = 0
        var q = 0
        while (p < pairs.size && q < vec.pairs.size) {
            when {
                pairs[p][0] == vec.pairs[q][0] -> {
                    result += pairs[p][1] * vec.pairs[q][1]
                    p++
                    q++
                }

                pairs[p][0] > vec.pairs[q][0] -> {
                    q++
                }

                else -> {
                    p++
                }
            }
        }
        return result
    }
}
