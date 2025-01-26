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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class MinIntegerAdjacentSwapsTest<out T : MinIntegerAdjacentSwaps>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "4321",
                4,
                "1342",
            ),
            Arguments.of(
                "100",
                1,
                "010",
            ),
            Arguments.of(
                "36789",
                1000,
                "36789",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `min integer test`(num: String, k: Int, expected: String) {
        val actual = strategy.minInteger(num, k)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class MinIntegerAdjacentSwapsImplTest :
    MinIntegerAdjacentSwapsTest<MinIntegerAdjacentSwaps>(MinIntegerAdjacentSwapsImpl())

class MinIntegerAdjacentSwapsBitTest :
    MinIntegerAdjacentSwapsTest<MinIntegerAdjacentSwaps>(MinIntegerAdjacentSwapsBit())
