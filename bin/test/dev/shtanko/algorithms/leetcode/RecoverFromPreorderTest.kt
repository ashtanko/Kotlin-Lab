/*
 * Copyright 2022 Oleksii Shtanko
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

abstract class RecoverFromPreorderTest<out T : RecoverFromPreorder>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "1-2--3--4-5--6--7",
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(3)
                        right = TreeNode(4)
                    }
                    right = TreeNode(5).apply {
                        left = TreeNode(6)
                        right = TreeNode(7)
                    }
                },
            ),
            Arguments.of(
                "1-2--3---4-5--6---7",
                TreeNode(1).apply {
                    left = TreeNode(2).apply {
                        right = TreeNode(3).apply {
                            right = TreeNode(4)
                        }
                    }
                    right = TreeNode(5).apply {
                        right = TreeNode(6).apply {
                            right = TreeNode(7)
                        }
                    }
                },
            ),
            Arguments.of(
                "1-401--349---90--88",
                TreeNode(1).apply {
                    left = TreeNode(401).apply {
                        left = TreeNode(349).apply {
                            left = TreeNode(90)
                        }
                        right = TreeNode(88)
                    }
                },
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `recover from preorder test`(traversal: String, expected: TreeNode?) {
        val actual = strategy.invoke(traversal).preorderTraversal()
        assertThat(actual).isEqualTo(expected.preorderTraversal())
    }
}

class RecoverFromPreorderIterativeTest : RecoverFromPreorderTest<RecoverFromPreorder>(RecoverFromPreorderIterative())
class RecoverFromPreorderRecursiveTest : RecoverFromPreorderTest<RecoverFromPreorder>(RecoverFromPreorderRecursive())
