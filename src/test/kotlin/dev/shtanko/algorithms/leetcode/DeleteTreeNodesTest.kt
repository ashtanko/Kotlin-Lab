/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class DeleteTreeNodesTest<out T : DeleteTreeNodes>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                7,
                intArrayOf(-1, 0, 0, 1, 2, 2, 2),
                intArrayOf(1, -2, 4, 0, -2, -1, -1),
                2,
            ),
            Arguments.of(
                7,
                intArrayOf(-1, 0, 0, 1, 2, 2, 2),
                intArrayOf(1, -2, 4, 0, -2, -1, -2),
                6,
            ),
            Arguments.of(
                5,
                intArrayOf(-1, 0, 1, 0, 0),
                intArrayOf(-672, 441, 18, 728, 378),
                5,
            ),
            Arguments.of(
                5,
                intArrayOf(-1, 0, 0, 1, 1),
                intArrayOf(-686, -842, 616, -739, -746),
                5,
            ),
            Arguments.of(
                5,
                intArrayOf(-1, 0, 1, 2, 3),
                intArrayOf(1, -1, 0, 0, 0),
                0,
            ),
            Arguments.of(
                5,
                intArrayOf(-1, 0, 1, 2, 3),
                intArrayOf(1, -1, 0, 1, 0),
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `delete tree nodes test`(nodes: Int, parent: IntArray, value: IntArray, expected: Int) {
        val actual = strategy.invoke(nodes, parent, value)
        assertThat(actual).isEqualTo(expected)
    }
}

class DeleteTreeNodesBruteForceTest :
    DeleteTreeNodesTest<DeleteTreeNodesBruteForce>(DeleteTreeNodesBruteForce())

class DeleteTreeNodesDFSTest : DeleteTreeNodesTest<DeleteTreeNodesDFS>(DeleteTreeNodesDFS())
