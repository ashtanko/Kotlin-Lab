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

class BeautifulArrayTest {

    class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                4,
                intArrayOf(1, 3, 2, 4),
            ),
            Arguments.of(
                5,
                intArrayOf(1, 5, 3, 2, 4),
            ),
            Arguments.of(
                1,
                intArrayOf(1),
            ),
            Arguments.of(
                2,
                intArrayOf(1, 2),
            ),
            Arguments.of(
                3,
                intArrayOf(1, 3, 2),
            ),
            Arguments.of(
                6,
                intArrayOf(1, 5, 3, 2, 6, 4),
            ),
            Arguments.of(
                7,
                intArrayOf(1, 3, 5, 7, 2, 4, 6),
            ),
            Arguments.of(
                8,
                intArrayOf(1, 7, 3, 5, 2, 8, 4, 6),
            ),
            Arguments.of(
                9,
                intArrayOf(1, 5, 3, 7, 2, 9, 4, 6, 8),
            ),
            Arguments.of(
                10,
                intArrayOf(1, 9, 5, 3, 7, 2, 10, 4, 6, 8),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `beautiful array test`(num: Int, expected: IntArray) {
        val actual = BeautifulArray().invoke(num)
        assertThat(actual).containsExactlyInAnyOrder(*expected)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `beautiful array divide and conquer test`(num: Int, expected: IntArray) {
        val actual = BeautifulArray().divideAndConquer(num)
        assertThat(actual).containsExactlyInAnyOrder(*expected)
    }
}
