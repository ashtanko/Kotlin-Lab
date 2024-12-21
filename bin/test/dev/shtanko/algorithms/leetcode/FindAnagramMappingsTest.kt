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

class FindAnagramMappingsTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(12, 28, 46, 32, 50),
                intArrayOf(50, 12, 32, 46, 28),
                intArrayOf(1, 4, 3, 2, 0),
            ),
            Arguments.of(
                intArrayOf(84, 8, 84, 84, 79),
                intArrayOf(84, 79, 8, 84, 84),
                intArrayOf(4, 2, 4, 4, 1),
            ),
            Arguments.of(
                intArrayOf(84, 47),
                intArrayOf(84, 47),
                intArrayOf(0, 1),
            ),
            Arguments.of(
                intArrayOf(84, 47),
                intArrayOf(47, 84),
                intArrayOf(1, 0),
            ),
            Arguments.of(
                intArrayOf(84, 47, 84),
                intArrayOf(47, 84, 84),
                intArrayOf(2, 0, 2),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find anagram mappings test`(a: IntArray, b: IntArray, expected: IntArray) {
        val actual = FindAnagramMappings.invoke(a, b)
        assertThat(actual).containsExactlyInAnyOrder(*expected)
    }
}
