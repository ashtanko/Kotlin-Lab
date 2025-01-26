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

package dev.shtanko.kotlinlang.functions.grouping

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class GroupingTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf("raccoon", "reindeer", "cow", "camel", "giraffe", "goat"),
                mapOf('c' to "camel", 'g' to "giraffe", 'r' to "reindeer"),
            ),
            Arguments.of(
                listOf("apple", "pineapple", "orange", "strawberry", "banana"),
                mapOf('a' to "apple", 'b' to "banana", 'o' to "orange", 'p' to "pineapple", 's' to "strawberry"),
            ),
        )
    }

    @Test
    fun compareByVowelCountTest() {
        assertThat(compareByVowelCount.compare("raccoon", "a")).isEqualTo(1)
        assertThat(compareByVowelCount.compare("reindeer", "e")).isEqualTo(1)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun maxVowelsTest(list: List<String>, expected: Map<Char, String>) {
        assertThat(maxVowels(list)).isEqualTo(expected)
    }
}
