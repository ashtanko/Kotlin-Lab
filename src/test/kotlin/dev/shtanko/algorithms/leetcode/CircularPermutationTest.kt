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

abstract class CircularPermutationTest<out T : CircularPermutation>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                2,
                3,
                listOf(3, 2, 0, 1),
            ),
            Arguments.of(
                3,
                2,
                listOf(2, 6, 7, 5, 4, 0, 1, 3),
            ),
            Arguments.of(
                0,
                0,
                listOf<Int>(),
            ),
            Arguments.of(
                1,
                0,
                listOf<Int>(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `circular permutation test`(n: Int, start: Int, expected: List<Int>) {
        val actual = strategy.invoke(n, start)
        assertThat(actual).containsAll(expected)
    }
}

class CircularPermutationGrayCodeTest : CircularPermutationTest<CircularPermutation>(CircularPermutationGrayCode())
class OneBitDiffPermutationTest : CircularPermutationTest<CircularPermutation>(OneBitDiffPermutation())
