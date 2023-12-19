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

class StampingSequenceTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "abc",
                "ababc",
                intArrayOf(1, 0, 2),
            ),
            Arguments.of(
                "abca",
                "aabcaca",
                intArrayOf(2, 3, 0, 1),
            ),
            Arguments.of(
                "",
                "aabcaca",
                intArrayOf(),
            ),
            Arguments.of(
                "",
                "",
                intArrayOf(0),
            ),
            Arguments.of(
                "abca",
                "",
                intArrayOf(),
            ),
            Arguments.of(
                "q",
                "q",
                intArrayOf(0),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `moves to stamp test`(stamp: String, target: String, expected: IntArray) {
        val source = StampingSequence()
        val actual = source.movesToStamp(stamp, target)
        assertThat(actual).containsExactlyInAnyOrder(*expected)
    }
}
