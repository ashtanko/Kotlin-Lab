/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.extensions

import java.util.stream.Stream
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class IntArrayTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                0,
                0,
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(1),
                0,
                0,
                intArrayOf(1),
            ),
            Arguments.of(
                intArrayOf(1, 3),
                0,
                2,
                intArrayOf(3, 1),
            ),
            Arguments.of(
                intArrayOf(4, 8, 15, 16, 23, 42),
                0,
                6,
                intArrayOf(42, 23, 16, 15, 8, 4),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
                5,
                9,
                intArrayOf(1, 2, 3, 4, 5, 9, 8, 7, 6),
            ),
        )
    }

    private class SubListInputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                1,
                4,
                listOf(2, 3, 4),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                4,
                1,
                emptyList<Int>(),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                2,
                2,
                emptyList<Int>(),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                0,
                3,
                listOf(1, 2, 3),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                2,
                5,
                listOf(3, 4, 5),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                0,
                0,
                emptyList<Int>(),
            ),
            Arguments.of(
                intArrayOf(),
                2,
                5,
                emptyList<Int>(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `reverse int array test`(arr: IntArray, start: Int, end: Int, expected: IntArray) {
        arr.reverse(start, end)
        assertThat(arr, equalTo(expected))
    }

    @ParameterizedTest
    @ArgumentsSource(SubListInputArgumentsProvider::class)
    internal fun `subList test`(arr: IntArray, startIndex: Int, endIndex: Int, expected: List<Int>) {
        val actual = arr.subList(startIndex, endIndex)
        assertThat(actual, equalTo(expected))
    }
}
