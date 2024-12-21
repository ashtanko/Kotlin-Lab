/*
 * Copyright 2024 Oleksii Shtanko
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

import dev.shtanko.algorithms.utils.assertTreeNodeEquals
import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class BstToGstTest<out T : BstToGst>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(4).apply {
                    left = TreeNode(1).apply {
                        left = TreeNode(0)
                        right = TreeNode(2).apply {
                            right = TreeNode(3)
                        }
                    }
                    right = TreeNode(6).apply {
                        left = TreeNode(5)
                        right = TreeNode(7).apply {
                            right = TreeNode(8)
                        }
                    }
                },
                TreeNode(30).apply {
                    left = TreeNode(36).apply {
                        left = TreeNode(36)
                        right = TreeNode(35).apply {
                            right = TreeNode(33)
                        }
                    }
                    right = TreeNode(21).apply {
                        left = TreeNode(26)
                        right = TreeNode(15).apply {
                            right = TreeNode(8)
                        }
                    }
                },
            ),
//            Arguments.of(
//
//            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `bst to gst test`(root: TreeNode, expected: TreeNode) {
        val actual = strategy(root)
        assertThat(actual).isEqualTo(expected)
        assertTreeNodeEquals(expected, actual)
    }
}

class BstToGstBruteForceTest : BstToGstTest<BstToGst>(BstToGstBruteForce())
class BstToGstInOrderTest : BstToGstTest<BstToGst>(BstToGstInOrder())
class BstToGstInOrderIterativeTest : BstToGstTest<BstToGst>(BstToGstInOrderIterative())
class BstToGstMorrisTraversalTest : BstToGstTest<BstToGst>(BstToGstMorrisTraversal())
