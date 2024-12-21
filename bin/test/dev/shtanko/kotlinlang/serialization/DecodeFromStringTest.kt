/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.kotlinlang.serialization

import dev.shtanko.algorithms.utils.measureTime
import java.util.stream.Stream
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class DecodeFromStringTest {

    @Serializable
    internal data class Data(val a: Int, val b: String)

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                """{"a":42, "b": "str"}""",
                Data(42, "str"),
            ),
            Arguments.of(
                """{"a":481516234, "b": "Lorem Ipsum is simply dummy text of the printing and typesetting industry."}""",
                Data(481516234, "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
            ),
        )
    }

    internal class InputListArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                """[]""",
                emptyList<Data>(),
            ),
            Arguments.of(
                """[{"a":42, "b": "str"}]""",
                listOf(Data(42, "str")),
            ),
            Arguments.of(
                """[{"a":42, "b": "str"},{"a":1, "b": "q"}]""",
                listOf(
                    Data(42, "str"),
                    Data(1, "q"),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `decode single item from json string test`(str: String, expected: Data) {
        measureTime("decode item from string") {
            val actual = Json.decodeFromString<Data>(str)
            assertThat(actual).isEqualTo(expected)
        }
    }

    @ParameterizedTest
    @ArgumentsSource(InputListArgumentsProvider::class)
    internal fun `decode list of items from json string test`(str: String, expected: List<Data>) {
        measureTime("decode items from string") {
            val actual = Json.decodeFromString<List<Data>>(str)
            assertThat(actual).isEqualTo(expected)
        }
    }
}
