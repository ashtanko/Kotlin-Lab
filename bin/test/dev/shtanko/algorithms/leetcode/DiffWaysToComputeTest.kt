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

abstract class DiffWaysToComputeTest<out T : DiffWaysToCompute>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "1",
                listOf(1),
            ),
            Arguments.of(
                "2-1-1",
                listOf(0, 2),
            ),
            Arguments.of(
                "2*3-4*5",
                listOf(-34, -14, -10, -10, 10),
            ),
            Arguments.of(
                "4+5*6-4-7+6",
                listOf(
                    79,
                    19,
                    -51,
                    79,
                    9,
                    43,
                    31,
                    1,
                    17,
                    55,
                    -15,
                    43,
                    13,
                    29,
                    135,
                    27,
                    -99,
                    135,
                    9,
                    43,
                    31,
                    63,
                    51,
                    1,
                    17,
                    5,
                    17,
                    37,
                    55,
                    -15,
                    43,
                    13,
                    29,
                    87,
                    -39,
                    43,
                    63,
                    13,
                    29,
                    17,
                    29,
                    49,
                ),
            ),
            Arguments.of(
                "",
                listOf<Int>(),
            ),
            Arguments.of(
                "1+1",
                listOf(2),
            ),
            Arguments.of(
                "1+1+1",
                listOf(3, 3),
            ),
            Arguments.of(
                "1+1+1+1",
                listOf(4, 4, 4, 4, 4),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `diff ways to compute test`(expression: String, expected: List<Int>) {
        val actual = strategy.invoke(expression)
        assertThat(actual).containsExactlyInAnyOrder(*expected.toTypedArray())
    }
}

class DiffWaysToComputeRecursiveTest : DiffWaysToComputeTest<DiffWaysToCompute>(DiffWaysToComputeRecursive())
class DiffWaysToComputeDivideAndConquerTest :
    DiffWaysToComputeTest<DiffWaysToCompute>(DiffWaysToComputeDivideAndConquer())
