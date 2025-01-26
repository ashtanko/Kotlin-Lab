/*
 * Copyright 2021 Oleksii Shtanko
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

abstract class BeautifulArrangementTest<out T : BeautifulArrangement>(private val strategy: T) {
    class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(0, 1),
            Arguments.of(1, 1),
            Arguments.of(2, 2),
            Arguments.of(3, 3),
            Arguments.of(4, 8),
            Arguments.of(5, 10),
            Arguments.of(6, 36),
            Arguments.of(7, 41),
            Arguments.of(8, 132),
            Arguments.of(9, 250),
            Arguments.of(10, 700),
            Arguments.of(11, 750),
            Arguments.of(12, 4010),
            Arguments.of(13, 4237),
            Arguments.of(14, 10680),
            Arguments.of(15, 24679),
            Arguments.of(16, 87328),
            Arguments.of(17, 90478),
            Arguments.of(18, 435812),
            Arguments.of(19, 449586),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count arrangement test`(num: Int, expected: Int) {
        val actual = strategy(num)
        assertThat(actual).isEqualTo(expected)
    }
}

class BABruteForceTest : BeautifulArrangementTest<BABruteForce>(BABruteForce())
class BABacktrackingTest : BeautifulArrangementTest<BABacktracking>(BABacktracking())
