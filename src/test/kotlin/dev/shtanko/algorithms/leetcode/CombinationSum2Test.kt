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

abstract class CombinationSum2Test<out T : CombinationSum2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(10, 1, 2, 7, 6, 1, 5),
                8,
                listOf(
                    listOf(1, 1, 6),
                    listOf(1, 2, 5),
                    listOf(1, 7),
                    listOf(2, 6),
                ),
            ),
            Arguments.of(
                intArrayOf(2, 5, 2, 1, 2),
                5,
                listOf(
                    listOf(1, 2, 2),
                    listOf(5),
                ),
            ),
            Arguments.of(
                intArrayOf(),
                0,
                listOf(
                    listOf<Int>(),
                ),
            ),
            Arguments.of(
                intArrayOf(),
                1,
                listOf<List<Int>>(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `combination sum 2 test`(candidates: IntArray, target: Int, expected: List<List<Int>>) {
        val actual = strategy.invoke(candidates, target)
        assertThat(actual).isEqualTo(expected)
    }
}

class BacktrackingWithCountersTest : CombinationSum2Test<CombinationSum2>(BacktrackingWithCounters())
class BacktrackingWithIndexTest : CombinationSum2Test<CombinationSum2>(BacktrackingWithIndex())
