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

abstract class HammingWeightTest<out T : HammingWeight>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                11, // 00000000000000000000000000001011
                3,
            ),
            Arguments.of(
                128, // 00000000000000000000000010000000
                1,
            ),
            Arguments.of(
                -3, // 11111111111111111111111111111101
                31,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun countBitsTest(num: Int, expected: Int) {
        val actual = strategy(num)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class HammingWeightSolutionTest : HammingWeightTest<HammingWeight>(HammingWeightSolution())
class HammingWeightStdTest : HammingWeightTest<HammingWeight>(HammingWeightStd())
class HammingWeightXorTest : HammingWeightTest<HammingWeight>(HammingWeightXor())
class HammingWeightAndTest : HammingWeightTest<HammingWeight>(HammingWeightAnd())
class HammingWeightUnsignedShiftRightTest : HammingWeightTest<HammingWeight>(HammingWeightUnsignedShiftRight())
