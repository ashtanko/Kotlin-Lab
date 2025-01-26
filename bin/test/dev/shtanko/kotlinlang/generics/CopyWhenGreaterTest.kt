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

package dev.shtanko.kotlinlang.generics

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class CopyWhenGreaterTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf("A", "B", "C"),
                "B",
                listOf("C"),
            ),
            Arguments.of(
                listOf("A", "B", "C"),
                "A",
                listOf("B", "C"),
            ),
            Arguments.of(
                listOf("A", "B", "C"),
                "",
                listOf("A", "B", "C"),
            ),
            Arguments.of(
                listOf("A", "B", "C"),
                "C",
                emptyList<String>(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `clone when greater test`(list: List<String>, threshold: String, expected: List<String>) {
        val actual = copyWhenGreater(list, threshold)
        assertThat(actual).isEqualTo(expected)
    }
}
