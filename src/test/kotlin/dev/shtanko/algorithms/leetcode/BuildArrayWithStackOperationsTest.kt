/*
 * Copyright 2020 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class BuildArrayWithStackOperationsTest {

    class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(intArrayOf(1, 3), 3, listOf("Push", "Push", "Pop", "Push")),
            Arguments.of(intArrayOf(1, 2, 3), 3, listOf("Push", "Push", "Push")),
            Arguments.of(intArrayOf(1, 2), 4, listOf("Push", "Push")),
            Arguments.of(intArrayOf(2, 3, 4), 4, listOf("Push", "Pop", "Push", "Push", "Push")),
            Arguments.of(intArrayOf(), 0, emptyList<String>()),
            Arguments.of(intArrayOf(1), 0, emptyList<String>()),
            Arguments.of(intArrayOf(), 1, emptyList<String>()),
            Arguments.of(intArrayOf(1), 1, listOf("Push")),
        )
    }

    @DisplayName("build an array with stack operations")
    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `build an array with stack operations test`(target: IntArray, n: Int, expected: List<String>) {
        val actual = BuildArrayWithStackOperations().perform(target, n)
        assertEquals(expected, actual)
    }
}
