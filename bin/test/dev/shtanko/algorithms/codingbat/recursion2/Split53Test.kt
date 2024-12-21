/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.algorithms.codingbat.recursion2

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class Split53Test<out T : Split53>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 1),
                true,
            ),
            Arguments.of(
                intArrayOf(1, 1, 1),
                false,
            ),
            Arguments.of(
                intArrayOf(2, 4, 2),
                true,
            ),
            Arguments.of(
                intArrayOf(2, 2, 2, 1),
                false,
            ),
            Arguments.of(
                intArrayOf(3, 3, 5, 1),
                true,
            ),
            Arguments.of(
                intArrayOf(3, 5, 8),
                false,
            ),
            Arguments.of(
                intArrayOf(2, 4, 6),
                true,
            ),
            Arguments.of(
                intArrayOf(3, 5, 6, 10, 3, 3),
                true,
            ),
            Arguments.of(
                intArrayOf(),
                true,
            ),
            Arguments.of(
                intArrayOf(1),
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `split53 test`(nums: IntArray, expected: Boolean) {
        val actual = strategy(nums)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class Split53IterativeTest : Split53Test<Split53>(Split53Iterative())
class Split53RecursiveTest : Split53Test<Split53>(Split53Recursive())
