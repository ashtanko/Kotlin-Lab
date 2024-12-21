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

abstract class MinimumEffortPathTest<out T : MinimumEffortPath>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 2),
                    intArrayOf(3, 8, 2),
                    intArrayOf(5, 3, 5),
                ),
                2,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 3),
                    intArrayOf(3, 8, 4),
                    intArrayOf(5, 3, 5),
                ),
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `minimum effort path test`(heights: Array<IntArray>, expected: Int) {
        val actual = strategy(heights)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class MinimumEffortPathDijikstraTest : MinimumEffortPathTest<MinimumEffortPath>(MinimumEffortPathDijikstra())
class MinimumEffortPathDFSTest : MinimumEffortPathTest<MinimumEffortPath>(MinimumEffortPathDFS())
