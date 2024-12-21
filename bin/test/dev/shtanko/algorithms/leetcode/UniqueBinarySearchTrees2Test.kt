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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class UniqueBinarySearchTrees2Test {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                3,
                listOf(
                    listOf(listOf(3), listOf(2), listOf(1)),
                    listOf(listOf(1, 3), listOf(2)),
                    listOf(listOf(2), listOf(3), listOf(1)),
                    listOf(listOf(2), listOf(1), listOf(3)),
                    listOf(listOf(1), listOf(2), listOf(3)),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `unique binary search trees 2 test`(num: Int, expected: List<List<List<Int>>>) {
        val actual = invoke(num)
        val ordered = actual.map { it.levelOrderBottom() }
        assertEquals(expected, ordered)
    }
}
