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

abstract class NumOfArraysTest<out T : NumOfArrays>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                2,
                3,
                1,
                6,
            ),
            Arguments.of(
                5,
                2,
                3,
                0,
            ),
            Arguments.of(
                9,
                1,
                1,
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `num of arrays test`(num: Int, m: Int, k: Int, expected: Int) {
        val actual = strategy(num, m, k)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class NumOfArraysTopDownTest : NumOfArraysTest<NumOfArrays>(NumOfArraysTopDown())
class NumOfArraysBottomUpTest : NumOfArraysTest<NumOfArrays>(NumOfArraysBottomUp())
class NumOfArraysSpaceOptimizedDpTest : NumOfArraysTest<NumOfArrays>(NumOfArraysSpaceOptimizedDp())
class NumOfArraysPrefixSumsTest : NumOfArraysTest<NumOfArrays>(NumOfArraysPrefixSums())
class NumOfArraysBetterDpTest : NumOfArraysTest<NumOfArrays>(NumOfArraysBetterDp())
