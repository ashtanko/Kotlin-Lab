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

class CountGoodNodesInBinaryTreeTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                null,
                0,
            ),
            Arguments.of(
                intArrayOf(3, 1, 4, 3, 1, 5).toTree(),
                4,
            ),
            Arguments.of(
                intArrayOf(3, 3, 4, 2).toTree(),
                3,
            ),
            Arguments.of(
                intArrayOf(1).toTree(),
                1,
            ),
            Arguments.of(
                intArrayOf(1, 2).toTree(),
                2,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3).toTree(),
                3,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4).toTree(),
                4,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5).toTree(),
                5,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count good nodes in binary tree test`(tree: TreeNode?, expected: Int) {
        val actual = CountGoodNodesInBinaryTree().goodNodes(tree)
        assertEquals(expected, actual)
    }
}
