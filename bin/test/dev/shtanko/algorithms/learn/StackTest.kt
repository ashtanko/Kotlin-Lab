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

package dev.shtanko.algorithms.learn

import java.util.Stack
import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class StackTest {

    @Test
    internal fun `sort values in stack`() {
        val stack = Stack<Int>().apply {
            push(4)
            push(1)
            push(5)
            push(2)
            push(9)
            push(34)
            push(0)
        }
        assertEquals(listOf(4, 1, 5, 2, 9, 34, 0), stack.toList())
        val sorted = stack.sorted()
        assertEquals(listOf(0, 1, 2, 4, 5, 9, 34), sorted.toList())
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `reverse string using stack`(str: String, expected: String) {
        val actual = str.reversed()
        assertEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "rap",
                "par",
            ),
            Arguments.of(
                "car",
                "rac",
            ),
            Arguments.of(
                "tenet",
                "tenet",
            ),
            Arguments.of(
                "",
                "",
            ),
        )
    }
}
