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

abstract class JumpGame5Test<out T : JumpGame5Strategy>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                0,
            ),
            Arguments.of(
                intArrayOf(100, -23, -23, 404, 100, 23, 23, 23, 3, 404),
                3,
            ),
            Arguments.of(
                intArrayOf(7),
                0,
            ),
            Arguments.of(
                intArrayOf(7, 6, 9, 6, 9, 6, 9, 7),
                1,
            ),
            Arguments.of(
                intArrayOf(6, 1, 9),
                2,
            ),
            Arguments.of(
                intArrayOf(11, 22, 7, 7, 7, 7, 7, 7, 7, 22, 13),
                3,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `jump game test`(arr: IntArray, expected: Int) {
        val actual = strategy.invoke(arr)
        assertEquals(expected, actual)
    }
}

class JP5BreadthFirstSearchTest : JumpGame5Test<JP5BreadthFirstSearch>(JP5BreadthFirstSearch())

class JP5BidirectionalBFSTest : JumpGame5Test<JP5BidirectionalBFS>(JP5BidirectionalBFS())

class JP5BidirectionalBFS2Test : JumpGame5Test<JP5BidirectionalBFS2>(JP5BidirectionalBFS2())
