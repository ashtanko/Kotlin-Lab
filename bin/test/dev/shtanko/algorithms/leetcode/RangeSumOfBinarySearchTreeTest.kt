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

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class RangeSumOfBinarySearchTreeTest<out T : RangeSumOfBinarySearchTree>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(10, 5, 15, 3, 7, 18),
                7,
                15,
                32,
            ),
            Arguments.of(
                intArrayOf(4, 8, 15, 16, 23, 42),
                8,
                23,
                15,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `range sum binary search tree test`(arr: IntArray, low: Int, high: Int, expected: Int) {
        val tree: TreeNode? = null
        val root = insertLevelOrder(tree, arr)
        val actual = strategy.invoke(root, low, high)
        assertEquals(expected, actual)
    }
}

class RangeSumOfBinarySearchTreeIterativeTest :
    RangeSumOfBinarySearchTreeTest<RangeSumOfBinarySearchTreeIterative>(RangeSumOfBinarySearchTreeIterative())

class RangeSumOfBinarySearchTreeRecursiveTest :
    RangeSumOfBinarySearchTreeTest<RangeSumOfBinarySearchTreeRecursive>(RangeSumOfBinarySearchTreeRecursive())
