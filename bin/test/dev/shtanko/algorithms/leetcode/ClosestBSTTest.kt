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

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class ClosestBSTTest<out T : ClosestBST>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(p0: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(4).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(1)
                        right = TreeNode(3)
                    }
                    right = TreeNode(5)
                },
                3.714286,
                4,
            ),
            Arguments.of(
                TreeNode(1),
                4.428571,
                1,
            ),
            Arguments.of(
                TreeNode(-1),
                4.428571,
                -1,
            ),
            Arguments.of(
                TreeNode(22).apply {
                    left = TreeNode(18).apply {
                        right = TreeNode(19)
                        left = TreeNode(9)
                    }
                    right = TreeNode(32).apply {
                        right = TreeNode(45).apply {
                            right = TreeNode(50)
                            left = TreeNode(40)
                        }
                    }
                },
                25.5,
                22,
            ),
            Arguments.of(
                TreeNode(2147483647),
                0.0,
                2147483647,
            ),
            Arguments.of(
                TreeNode(5).apply {
                    left = TreeNode(3).apply {
                        left = TreeNode(2).apply {
                            left = TreeNode(1)
                        }
                        right = TreeNode(4)
                    }
                    right = TreeNode(6)
                },
                4.714286,
                5,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `closest value test`(root: TreeNode, target: Double, expected: Int) {
        val actual = strategy.closestValue(root, target)
        assertThat(actual).isEqualTo(expected)
    }
}

class RecursiveInorderTest : ClosestBSTTest<RecursiveInorder>(RecursiveInorder())
class IterativeInorderTest : ClosestBSTTest<IterativeInorder>(IterativeInorder())
class ClosestBSTBinarySearchTest : ClosestBSTTest<ClosestBSTBinarySearch>(ClosestBSTBinarySearch())
