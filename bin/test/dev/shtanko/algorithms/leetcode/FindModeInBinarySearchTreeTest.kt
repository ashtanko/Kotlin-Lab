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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.leetcode.FindModeInBinarySearchTreeStrategy.BFS
import dev.shtanko.algorithms.leetcode.FindModeInBinarySearchTreeStrategy.DFS
import dev.shtanko.algorithms.leetcode.FindModeInBinarySearchTreeStrategy.DFSArray
import dev.shtanko.algorithms.leetcode.FindModeInBinarySearchTreeStrategy.DFSList
import dev.shtanko.algorithms.leetcode.FindModeInBinarySearchTreeStrategy.IterativeDFS
import dev.shtanko.algorithms.leetcode.FindModeInBinarySearchTreeStrategy.MorrisTraversal
import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class FindModeInBinarySearchTreeTest<out T : FindModeInBinarySearchTree>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(1),
                intArrayOf(1),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    right = TreeNode(2)
                },
                intArrayOf(1, 2),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    right = TreeNode(2).apply {
                        left = TreeNode(2)
                    }
                },
                intArrayOf(2),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(1)
                    right = TreeNode(2)
                },
                intArrayOf(1),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(1)
                    right = TreeNode(2).apply {
                        left = TreeNode(2)
                    }
                },
                intArrayOf(1, 2),
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(1).apply {
                        left = TreeNode(1)
                    }
                    right = TreeNode(2).apply {
                        left = TreeNode(2)
                    }
                },
                intArrayOf(1),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find mode in binary search tree test`(root: TreeNode, expected: IntArray) {
        val actual = strategy(root)
        assertArrayEquals(actual, expected)
    }
}

class FindModeInBinarySearchTreeDFSTest : FindModeInBinarySearchTreeTest<FindModeInBinarySearchTree>(DFS)
class FindModeInBinarySearchTreeIterativeDFSTest :
    FindModeInBinarySearchTreeTest<FindModeInBinarySearchTree>(IterativeDFS)

class FindModeInBinarySearchBFSTest : FindModeInBinarySearchTreeTest<FindModeInBinarySearchTree>(BFS)
class FindModeInBinarySearchDFSListTest : FindModeInBinarySearchTreeTest<FindModeInBinarySearchTree>(DFSList)

class FindModeInBinarySearchDFSArrayTest : FindModeInBinarySearchTreeTest<FindModeInBinarySearchTree>(DFSArray)
class FindModeInBinarySearchMorrisTraversalTest :
    FindModeInBinarySearchTreeTest<FindModeInBinarySearchTree>(MorrisTraversal)
