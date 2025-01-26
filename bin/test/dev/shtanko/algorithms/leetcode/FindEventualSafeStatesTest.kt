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

abstract class FindEventualSafeStatesTest<out T : FindEventualSafeStates>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                    intArrayOf(5),
                    intArrayOf(0),
                    intArrayOf(5),
                    intArrayOf(),
                    intArrayOf(),
                ),
                listOf(2, 4, 5, 6),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 3, 4),
                    intArrayOf(1, 2),
                    intArrayOf(3, 4),
                    intArrayOf(0, 4),
                    intArrayOf(),
                ),
                listOf(4),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find duplicate test`(graph: Array<IntArray>, expected: List<Int>) {
        val actual = strategy.eventualSafeNodes(graph)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class FindEventualSafeStatesKahnTest : FindEventualSafeStatesTest<FindEventualSafeStates>(FindEventualSafeStatesKahn())
class FindEventualSafeStatesDFSTest : FindEventualSafeStatesTest<FindEventualSafeStates>(FindEventualSafeStatesDFS())
