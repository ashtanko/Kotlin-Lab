/*
 * Copyright 2023 Oleksii Shtanko
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

abstract class CreateBinaryTreeTest<out T : CreateBinaryTree>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf<IntArray>(),
                listOf<Int>(),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(20, 15, 1),
                    intArrayOf(20, 17, 0),
                    intArrayOf(50, 20, 1),
                    intArrayOf(50, 80, 0),
                    intArrayOf(80, 19, 1),
                ),
                listOf(50, 20, 80, 15, 17, 19),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 1),
                    intArrayOf(2, 3, 0),
                    intArrayOf(3, 4, 1),
                ),
                listOf(1, 2, 3, 4),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 1),
                    intArrayOf(1, 3, 0),
                    intArrayOf(2, 4, 1),
                    intArrayOf(2, 5, 0),
                ),
                listOf(1, 2, 4, 5, 3),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 1),
                    intArrayOf(1, 3, 0),
                    intArrayOf(2, 4, 1),
                    intArrayOf(2, 5, 0),
                    intArrayOf(3, 6, 1),
                    intArrayOf(3, 7, 0),
                ),
                listOf(1, 2, 4, 5, 3, 6, 7),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 1),
                    intArrayOf(1, 3, 0),
                    intArrayOf(2, 4, 1),
                    intArrayOf(2, 5, 0),
                    intArrayOf(3, 6, 1),
                    intArrayOf(3, 7, 0),
                    intArrayOf(4, 8, 1),
                    intArrayOf(4, 9, 0),
                ),
                listOf(1, 2, 4, 8, 9, 5, 3, 6, 7),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 1),
                    intArrayOf(1, 3, 0),
                    intArrayOf(2, 4, 1),
                    intArrayOf(2, 5, 0),
                    intArrayOf(3, 6, 1),
                    intArrayOf(3, 7, 0),
                    intArrayOf(4, 8, 1),
                    intArrayOf(4, 9, 0),
                    intArrayOf(5, 10, 1),
                    intArrayOf(5, 11, 0),
                ),
                listOf(1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 7),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 1),
                    intArrayOf(1, 3, 0),
                    intArrayOf(2, 4, 1),
                    intArrayOf(2, 5, 0),
                    intArrayOf(3, 6, 1),
                    intArrayOf(3, 7, 0),
                    intArrayOf(4, 8, 1),
                    intArrayOf(4, 9, 0),
                    intArrayOf(5, 10, 1),
                    intArrayOf(5, 11, 0),
                    intArrayOf(6, 12, 1),
                    intArrayOf(6, 13, 0),
                ),
                listOf(1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 13, 7),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `create binary tree test`(descriptions: Array<IntArray>, expected: List<Int>) {
        val actual = strategy.invoke(descriptions).preorderTraversal()
        Assertions.assertThat(actual).containsExactlyInAnyOrder(*expected.toTypedArray())
    }
}

class CreateBinaryTreeHashMapTest : CreateBinaryTreeTest<CreateBinaryTree>(CreateBinaryTreeHashMap())
