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

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class PseudoPalindromicPathsTest<out T : PseudoPalindromicPaths>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(2).apply {
                    left = TreeNode(3).apply {
                        left = TreeNode(3)
                        right = TreeNode(1)
                    }
                    right = TreeNode(1).apply {
                        right = TreeNode(1)
                    }
                },
                2,
            ),
            Arguments.of(
                TreeNode(2).apply {
                    left = TreeNode(1).apply {
                        left = TreeNode(1)
                        right = TreeNode(3).apply {
                            right = TreeNode(1)
                        }
                    }
                    right = TreeNode(1)
                },
                1,
            ),
            Arguments.of(
                TreeNode(9),
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `pseudo palindromic paths test`(root: TreeNode, expected: Int) {
        val actual = strategy(root)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class PseudoPalindromicPathsDFSTest : PseudoPalindromicPathsTest<PseudoPalindromicPaths>(PseudoPalindromicPathsDFS())
class PseudoPalindromicPathsRecursiveTest :
    PseudoPalindromicPathsTest<PseudoPalindromicPaths>(PseudoPalindromicPathsRecursive())
