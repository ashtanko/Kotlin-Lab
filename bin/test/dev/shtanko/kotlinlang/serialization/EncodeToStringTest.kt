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
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

@OptIn(ExperimentalSerializationApi::class)
internal class EncodeToStringTest {

    private val json = Json.Default

    @Serializable
    internal data class Data(val name: String, val lang: String)

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                Data(name = "kotlinx.serialization", lang = "Kotlin"),
                """{"name":"kotlinx.serialization","lang":"Kotlin"}""",
            ),
        )
    }

    internal class InputListArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                emptyList<Data>(),
                """[]""",
            ),
            Arguments.of(
                listOf(Data(name = "kotlinx.serialization", lang = "Kotlin")),
                """[{"name":"kotlinx.serialization","lang":"Kotlin"}]""",
            ),
            Arguments.of(
                listOf(
                    Data(name = "kotlinx.serialization", lang = "Kotlin"),
                    Data(name = "go", lang = "Go"),
                    Data(name = "Vec", lang = "Rust"),
                ),
                """
                    [{"name":"kotlinx.serialization","lang":"Kotlin"},{"name":"go","lang":"Go"},{"name":"Vec","lang":"Rust"}]
                """.trimIndent(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `encode single item to string test`(data: Data, expected: String) =
        measureTime("encode item to string") {
            val actual = json.encodeToString(data)
            assertThat(actual).isEqualTo(expected)
        }

    @ParameterizedTest
    @ArgumentsSource(InputListArgumentsProvider::class)
    internal fun `encode items to string test`(data: List<Data>, expected: String) =
        measureTime("encode items to string") {
            val actual = json.encodeToString(data)
            assertThat(actual).isEqualTo(expected)
        }
}
