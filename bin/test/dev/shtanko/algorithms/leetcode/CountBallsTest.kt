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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class CountBallsTest<out T : CountBalls>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                1,
                10,
                2,
            ),
            Arguments.of(
                5,
                15,
                2,
            ),
            Arguments.of(
                19,
                28,
                2,
            ),
            Arguments.of(
                1,
                100000,
                6000,
            ),
            Arguments.of(
                5,
                15,
                2,
            ),
            Arguments.of(
                4,
                7,
                1,
            ),
        )
    }

    @ArgumentsSource(InputArgumentsProvider::class)
    @ParameterizedTest
    fun `count balls test`(lowLimit: Int, highLimit: Int, expected: Int) {
        val actual = strategy.invoke(lowLimit, highLimit)
        assertThat(actual).isEqualTo(expected)
    }
}

class CountBallsBruteforceTest : CountBallsTest<CountBalls>(CountBallsBruteforce())
class CountBalls2Test : CountBallsTest<CountBalls>(CountBalls2())
