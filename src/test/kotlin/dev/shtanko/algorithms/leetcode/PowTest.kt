/*
 * Copyright 2021 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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

abstract class PowTest<out T : Pow>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                2.00000,
                10,
                1024.00000,
            ),
            Arguments.of(
                2.10000,
                3,
                9.261000000000001,
                // 9.26100
            ),
            Arguments.of(
                2.00000,
                -2,
                0.25000,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `pow test`(x: Double, n: Int, expected: Double) {
        val actual = strategy.perform(x, n)
        assertThat(actual).isEqualTo(expected)
    }
}

class PowBruteForceTest : PowTest<PowBruteForce>(PowBruteForce())
class PowFastRecursiveTest : PowTest<PowFastRecursive>(PowFastRecursive())
class PowFastIterativeTest : PowTest<PowFastIterative>(PowFastIterative())
