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
 * 96. Unique Binary Search Trees
 * @see <a href="https://leetcode.com/problems/unique-binary-search-trees/">Source</a>
 */
fun interface UniqueBinarySearchTrees {
    operator fun invoke(numberOfNodes: Int): Int
}

/**
 * Approach 1: Dynamic Programming
 * Time complexity O(N^2)
 * Space complexity O(N)
 */
class UniqueBSTDP : UniqueBinarySearchTrees {
    override fun invoke(numberOfNodes: Int): Int {
        val numberOfTrees = IntArray(numberOfNodes + 1)
        numberOfTrees[0] = 1
        if (numberOfNodes > 0) {
            numberOfTrees[1] = 1
        }

        for (currentNode in 2..numberOfNodes) {
            for (rootNode in 1..currentNode) {
                numberOfTrees[currentNode] += numberOfTrees[rootNode - 1] * numberOfTrees[currentNode - rootNode]
            }
        }
        return numberOfTrees[numberOfNodes]
    }
}

/**
 * Approach 2: Mathematical Deduction
 * Time complexity : O(N)
 * Space complexity : O(1)
 */
class UniqueBSTDeduction : UniqueBinarySearchTrees {
    override fun invoke(numberOfNodes: Int): Int {
        // Note: we should use long here instead of int, otherwise overflow
        var catalanNumber: Long = 1
        for (index in 0 until numberOfNodes) {
            catalanNumber = catalanNumber * 2 * (2 * index + 1) / (index + 2)
        }
        return catalanNumber.toInt()
    }
}
