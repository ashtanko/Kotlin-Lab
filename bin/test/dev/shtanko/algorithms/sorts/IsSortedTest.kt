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

package dev.shtanko.algorithms.sorts

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class IsSortedTest {
    class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                true,
            ),
            Arguments.of(
                intArrayOf(0),
                true,
            ),
            Arguments.of(
                intArrayOf(0, 1),
                true,
            ),
            Arguments.of(
                intArrayOf(-1),
                true,
            ),
            Arguments.of(
                intArrayOf(-2, -1),
                true,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                true,
            ),
            Arguments.of(
                intArrayOf(2, 1),
                false,
            ),
            Arguments.of(
                intArrayOf(3, 2, 1),
                false,
            ),
            Arguments.of(
                intArrayOf(3, 6, 9, 1),
                false,
            ),
            Arguments.of(
                intArrayOf(-3, -2, -7, -1),
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `is sorted test`(arr: IntArray, expected: Boolean) {
        val actual = arr.isSorted()
        assertThat(actual).isEqualTo(expected)
    }
}
