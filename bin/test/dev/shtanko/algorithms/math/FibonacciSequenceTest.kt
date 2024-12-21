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

package dev.shtanko.algorithms.math

import java.util.stream.Stream
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class FibonacciSequenceTest {

    internal class InputSimpleArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(0, 0),
            Arguments.of(1, 1),
            Arguments.of(2, 1),
            Arguments.of(3, 2),
            Arguments.of(4, 3),
            Arguments.of(5, 5),
            Arguments.of(6, 8),
            Arguments.of(7, 13),
            Arguments.of(8, 21),
            Arguments.of(9, 34),
            Arguments.of(10, 55),
        )
    }

    internal class InputIterativeArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(0, 0),
            Arguments.of(1, 1),
            Arguments.of(2, 1),
            Arguments.of(11, 89),
            Arguments.of(12, 144),
            Arguments.of(13, 233),
            Arguments.of(14, 377),
            Arguments.of(15, 610),
        )
    }

    internal class InputRecursiveArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(0, 0),
            Arguments.of(1, 1),
            Arguments.of(2, 1),
            Arguments.of(16, 987),
            Arguments.of(17, 1597),
            Arguments.of(18, 2584),
            Arguments.of(19, 4181),
            Arguments.of(20, 6765),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputSimpleArgumentsProvider::class)
    internal fun `simple test`(num: Int, expected: Int) {
        val actual = num.toFibonacciSequence()
        assertThat(actual, equalTo(expected))
    }

    @ParameterizedTest
    @ArgumentsSource(InputIterativeArgumentsProvider::class)
    internal fun `iterative test`(num: Long, expected: Long) {
        val actual = Fibonacci.ITERATIVE.invoke(num)
        assertThat(actual, equalTo(expected))
    }

    @ParameterizedTest
    @ArgumentsSource(InputRecursiveArgumentsProvider::class)
    internal fun `recursive test`(num: Long, expected: Long) {
        val actual = Fibonacci.RECURSIVE.invoke(num)
        assertThat(actual, equalTo(expected))
    }
}
