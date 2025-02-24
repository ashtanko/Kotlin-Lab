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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class AddingSpacesTest<out T : AddingSpaces>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "LeetcodeHelpsMeLearn",
                intArrayOf(8, 13, 15),
                "Leetcode Helps Me Learn",
            ),
            Arguments.of(
                "icodeinpython",
                intArrayOf(1, 5, 7, 9),
                "i code in py thon",
            ),
            Arguments.of(
                "spacing",
                intArrayOf(0, 1, 2, 3, 4, 5, 6),
                " s p a c i n g",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun addSpacesTest(s: String, spaces: IntArray, expected: String) {
        val actual = strategy(s, spaces)
        assertThat(actual).isEqualTo(expected)
    }
}

class AddingSpacesStringBuilderTest : AddingSpacesTest<AddingSpaces>(AddingSpaces.StringBuilder)
class AddingSpacesTwoPointersTest : AddingSpacesTest<AddingSpaces>(AddingSpaces.TwoPointers)
