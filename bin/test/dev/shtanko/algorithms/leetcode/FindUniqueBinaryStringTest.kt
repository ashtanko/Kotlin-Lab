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

abstract class FindUniqueBinaryStringTest<out T : FindUniqueBinaryString>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf("01", "10"),
                "11",
            ),
            Arguments.of(
                arrayOf("00", "01"),
                "10",
            ),
            Arguments.of(
                arrayOf("111", "011", "001"),
                "000",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find different binary string test`(nums: Array<String>, expected: String) {
        val actual = strategy.invoke(nums)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class FindUniqueBinaryStringImplTest : FindUniqueBinaryStringTest<FindUniqueBinaryString>(FindUniqueBinaryStringImpl())

class FindUniqueBinaryStringStreamTest :
    FindUniqueBinaryStringTest<FindUniqueBinaryString>(FindUniqueBinaryStringStream())

class FindUniqueBinaryStringOneLineTest :
    FindUniqueBinaryStringTest<FindUniqueBinaryString>(FindUniqueBinaryStringOneLine())
