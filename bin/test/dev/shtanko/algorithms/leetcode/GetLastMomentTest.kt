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

abstract class GetLastMomentTest<out T : GetLastMoment>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                3,
                intArrayOf(4, 3),
                intArrayOf(0, 1),
                4,
            ),
            Arguments.of(
                7,
                intArrayOf(),
                intArrayOf(0, 1, 2, 3, 4, 5, 6, 7),
                7,
            ),
            Arguments.of(
                7,
                intArrayOf(0, 1, 2, 3, 4, 5, 6, 7),
                intArrayOf(),
                7,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `get last moment test`(num: Int, left: IntArray, right: IntArray, expected: Int) {
        val actual = strategy(num, left, right)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class GetLastMomentSolutionTest : GetLastMomentTest<GetLastMoment>(GetLastMomentSolution())
