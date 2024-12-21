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

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class BinaryGapTest<out T : BinaryGap>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                0,
            ),
            Arguments.of(
                1,
                0,
            ),
            Arguments.of(
                Int.MAX_VALUE,
                1,
            ),
            Arguments.of(
                22,
                2,
            ),
            Arguments.of(
                5,
                2,
            ),
            Arguments.of(
                6,
                1,
            ),
            Arguments.of(
                8,
                0,
            ),
            Arguments.of(
                4,
                0,
            ),
            Arguments.of(
                15,
                1,
            ),
            Arguments.of(
                7,
                1,
            ),
            Arguments.of(
                16,
                0,
            ),
            Arguments.of(
                23,
                2,
            ),
            Arguments.of(
                42,
                2,
            ),
            Arguments.of(
                56,
                1,
            ),
            Arguments.of(
                114,
                3,
            ),
            Arguments.of(
                2345,
                3,
            ),
            Arguments.of(
                3456,
                2,
            ),
            Arguments.of(
                6543,
                4,
            ),
            Arguments.of(
                12345,
                7,
            ),
            Arguments.of(
                123456,
                4,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `binary gap test`(num: Int, expected: Int) {
        val actual = strategy.invoke(num)
        assertThat(actual).isEqualTo(expected)
    }
}

class BGStoreIndexesTest : BinaryGapTest<BGStoreIndexes>(BGStoreIndexes())

class BGOnePassTest : BinaryGapTest<BGOnePass>(BGOnePass())

class BGOtherTest : BinaryGapTest<BGOther>(BGOther())
