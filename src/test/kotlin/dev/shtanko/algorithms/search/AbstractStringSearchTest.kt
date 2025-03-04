/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.search

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal abstract class AbstractStringSearchTest<out T : AbstractSearchStrategy<String>>(private val strategy: T) {

    private class InputStringArrayArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(arrayOf<String>(), "A", -1),
            Arguments.of(arrayOf("A"), "A", 0),
            Arguments.of(arrayOf("A"), "B", -1),
            Arguments.of(arrayOf("A", "B"), "A", 0),
            Arguments.of(arrayOf("A", "B"), "B", 1),
            Arguments.of(arrayOf("A", "B"), "C", -1),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputStringArrayArgumentsProvider::class)
    internal fun `string array test`(arr: Array<String>, element: String, expected: Int) {
        val actual = strategy.perform(arr, element)
        assertEquals(expected, actual)
    }
}
