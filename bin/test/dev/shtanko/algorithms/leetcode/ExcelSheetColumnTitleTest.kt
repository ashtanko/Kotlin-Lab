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

abstract class ExcelSheetColumnTitleTest<out T : ExcelSheetColumnTitle>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                1,
                "A",
            ),
            Arguments.of(
                28,
                "AB",
            ),
            Arguments.of(
                701,
                "ZY",
            ),
            Arguments.of(
                2147483647,
                "FXSHRXW",
            ),
            Arguments.of(
                52,
                "AZ",
            ),
            Arguments.of(
                703,
                "AAA",
            ),
            Arguments.of(
                2147483647,
                "FXSHRXW",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `convert to title test`(columnNumber: Int, expected: String) {
        val actual = strategy.invoke(columnNumber)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class ExcelSheetColumnTitleSBTest : ExcelSheetColumnTitleTest<ExcelSheetColumnTitle>(ExcelSheetColumnTitleSB())
