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

abstract class AmountOfTimeTest<out T : AmountOfTime>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(5).apply {
                        right = TreeNode(4).apply {
                            left = TreeNode(9)
                            right = TreeNode(2)
                        }
                    }
                    right = TreeNode(3).apply {
                        left = TreeNode(10)
                        right = TreeNode(6)
                    }
                },
                3,
                4,
            ),
            Arguments.of(
                TreeNode(1),
                1,
                0,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                },
                1,
                1,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(2)
                    right = TreeNode(3)
                },
                2,
                2,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun test(root: TreeNode?, start: Int, expected: Int) {
        val actual = strategy(root, start)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class AmountOfTimeBFSTest : AmountOfTimeTest<AmountOfTime>(AmountOfTimeBFS())
class AmountOfTimeDFSTest : AmountOfTimeTest<AmountOfTime>(AmountOfTimeDFS())
