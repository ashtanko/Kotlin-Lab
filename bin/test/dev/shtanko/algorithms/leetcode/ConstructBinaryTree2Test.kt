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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class ConstructBinaryTree2Test<out T : ConstructBinaryTree2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(9, 3, 15, 20, 7),
                intArrayOf(9, 15, 7, 20, 3),
                listOf(3, 9, 20, 15, 7),
            ),
            Arguments.of(
                intArrayOf(-1),
                intArrayOf(-1),
                listOf(-1),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(1, 3, 4, 2),
                listOf(2, 1, 4, 3),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `build tree test`(inorder: IntArray, postorder: IntArray, expected: List<Int>) {
        val actual = strategy.buildTree(inorder, postorder).preorderTraversal()
        assertThat(actual).isEqualTo(expected)
    }
}

class ConstructBinaryTree2RecursiveTest :
    ConstructBinaryTree2Test<ConstructBinaryTree2>(ConstructBinaryTree2Recursive())
