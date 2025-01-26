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

abstract class ValidateBinaryTreeNodesTest<out T : ValidateBinaryTreeNodes>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                4,
                intArrayOf(1, -1, 3, -1),
                intArrayOf(2, -1, -1, -1),
                true,
            ),
            Arguments.of(
                4,
                intArrayOf(1, -1, 3, -1),
                intArrayOf(2, 3, -1, -1),
                false,
            ),
            Arguments.of(
                2,
                intArrayOf(1, 0),
                intArrayOf(-1, -1),
                false,
            ),
            Arguments.of(
                6,
                intArrayOf(1, -1, -1, 4, -1, -1),
                intArrayOf(2, -1, -1, 5, -1, -1),
                false,
            ),
            Arguments.of(
                4,
                intArrayOf(1, 2, 0, -1),
                intArrayOf(-1, -1, -1, -1),
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `validate binary tree nodes test`(num: Int, leftChild: IntArray, rightChild: IntArray, expected: Boolean) {
        val actual = strategy(num, leftChild, rightChild)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class ValidateBinaryTreeNodesDFSTest :
    ValidateBinaryTreeNodesTest<ValidateBinaryTreeNodes>(ValidateBinaryTreeNodesDFS())

class ValidateBinaryTreeNodesBFSTest :
    ValidateBinaryTreeNodesTest<ValidateBinaryTreeNodes>(ValidateBinaryTreeNodesBFS())
