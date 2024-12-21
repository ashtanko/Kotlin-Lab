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

abstract class DistinctSubseq2Test<out T : DistinctSubseq2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "abc",
                7,
            ),
            Arguments.of(
                "aba",
                6,
            ),
            Arguments.of(
                "aaa",
                3,
            ),
            Arguments.of(
                "a",
                1,
            ),
            Arguments.of(
                "ab",
                3,
            ),
            Arguments.of(
                "aa",
                2,
            ),
            Arguments.of(
                "abab",
                11,
            ),
            Arguments.of(
                "ababab",
                32,
            ),
            Arguments.of(
                "abababab",
                87,
            ),
            Arguments.of(
                "",
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `distinct subseq II test`(str: String, expected: Int) {
        val actual = strategy.invoke(str)
        assertThat(actual).isEqualTo(expected)
    }
}

class DistinctSubseq2DPTest : DistinctSubseq2Test<DistinctSubseq2>(DistinctSubseq2DP())
class DistinctSubseq2DPConstSpaceTest : DistinctSubseq2Test<DistinctSubseq2>(DistinctSubseq2DPConstSpace())
