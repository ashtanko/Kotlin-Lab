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

abstract class SameTreeTest<out T : SameTree>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                },
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                },
                true,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                },
                TreeNode(1).apply {
                    right = TreeNode(2)
                },
                false,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(2)
                },
                TreeNode(1).apply {
                    right = TreeNode(2)
                    left = TreeNode(1)
                },
                false,
            ),
            Arguments.of(
                null,
                null,
                true,
            ),
            Arguments.of(
                TreeNode(1),
                null,
                false,
            ),
            Arguments.of(
                null,
                TreeNode(1),
                false,
            ),
            Arguments.of(
                TreeNode(1),
                TreeNode(1),
                true,
            ),
            Arguments.of(
                TreeNode(1),
                TreeNode(2),
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `is same test test`(t1: TreeNode?, t2: TreeNode?, expected: Boolean) {
        val actual = strategy(t1, t2)
        assertEquals(expected, actual)
    }
}

class SameTreeRecursiveTest : SameTreeTest<SameTree>(SameTreeRecursive())
