/*
 * Copyright 2020 Oleksii Shtanko
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

import dev.shtanko.algorithms.utils.measureTime
import java.util.stream.Stream
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class AddBinaryTest<out T : AddBinary>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "",
                "",
                "",
            ),
            Arguments.of(
                "0",
                "1",
                "1",
            ),
            Arguments.of(
                "0",
                "0",
                "0",
            ),
            Arguments.of(
                "11",
                "1",
                "100",
            ),
            Arguments.of(
                "1010",
                "1011",
                "10101",
            ),
            Arguments.of(
                "10101010010010101001010010100100101",
                "101111111111111111",
                "10101010010010101111010010100100100",
            ),
            Arguments.of(
                "10101010",
                "11001100",
                "101110110",
            ),
            Arguments.of(
                "1111",
                "1111",
                "11110",
            ),
            Arguments.of(
                "1111",
                "1110",
                "11101",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `add binary test`(a: String, b: String, expected: String) {
        measureTime("Add binary a: $a b: $b") {
            val actual = strategy.invoke(a, b)
            assertThat(actual, equalTo(expected))
        }
    }
}

class AddBinaryBitByBitComputationTest :
    AddBinaryTest<AddBinaryBitByBitComputation>(AddBinaryBitByBitComputation())

class AddBinaryBitManipulationTest :
    AddBinaryTest<AddBinaryBitManipulation>(AddBinaryBitManipulation())
