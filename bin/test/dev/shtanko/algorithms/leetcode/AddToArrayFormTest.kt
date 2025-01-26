/*
 * Copyright 2023 Oleksii Shtanko
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

abstract class AddToArrayFormTest<out T : AddToArrayForm>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 0, 0),
                34,
                listOf(1, 2, 3, 4),
            ),
            Arguments.of(
                intArrayOf(2, 7, 4),
                181,
                listOf(4, 5, 5),
            ),
            Arguments.of(
                intArrayOf(2, 1, 5),
                806,
                listOf(1, 0, 2, 1),
            ),
            Arguments.of(
                intArrayOf(9, 9, 9, 9, 9, 9, 9, 9, 9, 9),
                1,
                listOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            ),
            Arguments.of(
                intArrayOf(0),
                23,
                listOf(2, 3),
            ),
            Arguments.of(
                intArrayOf(0),
                10000,
                listOf(1, 0, 0, 0, 0),
            ),
            Arguments.of(
                intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                1,
                listOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 1),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `add to array form test`(num: IntArray, k: Int, expected: List<Int>) {
        val actual = strategy.invoke(num, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class AddToArrayFormSimpleTest : AddToArrayFormTest<AddToArrayForm>(AddToArrayFormSimple())
class AddToArrayFormOnePassTest : AddToArrayFormTest<AddToArrayForm>(AddToArrayFormOnePass())
