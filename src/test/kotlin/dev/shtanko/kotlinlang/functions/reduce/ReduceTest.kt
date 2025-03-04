/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.kotlinlang.functions.reduce

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class ReduceTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                300,
            ),
            Arguments.of(
                listOf(1, 2),
                20,
            ),
            Arguments.of(
                listOf(2, 2),
                40,
            ),
            Arguments.of(
                listOf(4, 4),
                80,
            ),
            Arguments.of(
                listOf(400, 400),
                8000,
            ),
        )
    }

    @DisplayName("reduce a list test")
    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun reduceListTest(list: List<Int>, expected: Int) {
        val actual = reduceList(list)
        assertThat(actual).isEqualTo(expected)
    }
}
