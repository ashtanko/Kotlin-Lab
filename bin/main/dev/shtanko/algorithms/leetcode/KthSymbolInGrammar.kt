/*
 * Copyright 2023 Oleksii Shtanko
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

import kotlin.math.pow

/**
 * 779. K-th Symbol in Grammar
 * @see <a href="https://leetcode.com/problems/k-th-symbol-in-grammar">Source</a>
 */
fun interface KthSymbolInGrammar {
    operator fun invoke(n: Int, k: Int): Int
}

/**
 * Approach 1: Binary Tree Traversal
 */
class KthSymbolInGrammarDFS : KthSymbolInGrammar {
    override fun invoke(n: Int, k: Int): Int {
        return depthFirstSearch(n, k, 0)
    }

    private fun depthFirstSearch(n: Int, k: Int, rootVal: Int): Int {
        if (n == 1) {
            return rootVal
        }
        val totalNodes = 2.0.pow(n - 1.0)

        // Target node will be present in the right half sub-tree of the current root node.
        return if (k > totalNodes / 2) {
            val nextRootVal = if (rootVal == 0) 1 else 0
            depthFirstSearch(n - 1, k - totalNodes.toInt() / 2, nextRootVal)
        } else {
            val nextRootVal = if (rootVal == 0) 0 else 1
            depthFirstSearch(n - 1, k, nextRootVal)
        }
    }
}

/**
 * Approach 2: Normal Recursion
 */
class KthSymbolInGrammarRecursion : KthSymbolInGrammar {
    override fun invoke(n: Int, k: Int): Int {
        return recursion(n, k)
    }

    private fun recursion(n: Int, k: Int): Int {
        // First row will only have one symbol '0'.
        if (n == 1) {
            return 0
        }
        val totalElements = 2.0.pow(n - 1)
        val halfElements = totalElements / 2

        // If the target is present in the right half, we switch to the respective left half symbol.
        return if (k > halfElements) {
            1 - recursion(n, k - halfElements.toInt())
        } else {
            recursion(n - 1, k)
        }

        // Otherwise, we switch to the previous row.
    }
}

/**
 * Approach 3: Recursion to Iteration
 */
class KthSymbolInGrammarIteration : KthSymbolInGrammar {
    override fun invoke(n: Int, k: Int): Int {
        if (n == 1) {
            return 0
        }
        var k0 = k

        // We assume the symbol at the target position is '1'.
        var symbol = 1

        for (currRow in n downTo 2) {
            val totalElements = 2.0.pow(currRow - 1)
            val halfElements = totalElements / 2

            // If 'k' lies in the right half symbol, then we flip over to the respective left half symbol.
            if (k0 > halfElements) {
                // Flip the symbol.
                symbol = 1 - symbol
                // Change the position after flipping.
                k0 -= halfElements.toInt()
            }
        }

        // We reached the 1st row; if the symbol is not '0', we started with the wrong symbol.
        return if (symbol != 0) {
            // Thus, the start symbol was '0', not '1'.
            0
        } else {
            // The start symbol was indeed what we started with, a '1'.
            1
        }
    }
}

/**
 * Approach 4: Math
 */
class KthSymbolInGrammarMath : KthSymbolInGrammar {
    override fun invoke(n: Int, k: Int): Int {
        val count = Integer.bitCount(k - 1)
        return if (count % 2 == 0) 0 else 1
    }
}
