/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class CustomSortStringTest<out T : CustomSortString>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "cba",
                "abcd",
                "cbad",
            ),
            Arguments.of(
                "bcafg",
                "abcd",
                "bcad",
            ),
            Arguments.of(
                "",
                "",
                "",
            ),
            Arguments.of(
                "a",
                "",
                "",
            ),
            Arguments.of(
                "a",
                "b",
                "b",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `custom sort string test`(order: String, str: String, expected: String) {
        val actual = strategy(order, str)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class CustomSortStringComparatorTest : CustomSortStringTest<CustomSortString>(CustomSortStringComparator())
class CustomSortStringFreqTableTest : CustomSortStringTest<CustomSortString>(CustomSortStringFreqTable())
