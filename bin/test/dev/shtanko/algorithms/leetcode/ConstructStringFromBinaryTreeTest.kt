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

abstract class ConstructStringFromBinaryTreeTest<out T : ConstructStringFromBinaryTreeStrategy>(
    private val strategy: T,
) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                    left?.left = TreeNode(4)
                },
                "1(2(4))(3)",
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    left?.right = TreeNode(4)
                    right = TreeNode(3)
                },
                "1(2()(4))(3)",
            ),
            Arguments.of(
                TreeNode(1),
                "1",
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                    left?.left = TreeNode(4)
                    right?.right = TreeNode(5)
                },
                "1(2(4))(3()(5))",
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                    left?.right = TreeNode(4)
                    right?.right = TreeNode(5)
                },
                "1(2()(4))(3()(5))",
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                    left?.left = TreeNode(4)
                    left?.right = TreeNode(5)
                    right?.left = TreeNode(6)
                    right?.right = TreeNode(7)
                },
                "1(2(4)(5))(3(6)(7))",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `construct string from binary tree test`(tree: TreeNode, expected: String) {
        val actual = strategy.invoke(tree)
        assertEquals(expected, actual)
    }
}

class ConstructStringFromBinaryTreeRecursionTest :
    ConstructStringFromBinaryTreeTest<ConstructStringFromBinaryTreeRecursion>(ConstructStringFromBinaryTreeRecursion())

class ConstructStringFromBinaryTreeStackTest :
    ConstructStringFromBinaryTreeTest<ConstructStringFromBinaryTreeStack>(ConstructStringFromBinaryTreeStack())
