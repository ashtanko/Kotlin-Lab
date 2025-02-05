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

import dev.shtanko.kotlinlang.inline.each
import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class HappyNumberTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(19, true),
            Arguments.of(16, false),
            Arguments.of(23, true),
        )
    }

    class InputListArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(0, listOf<Int>()),
            Arguments.of(5, listOf(1)),
            Arguments.of(25, listOf(1, 7, 10, 13, 19, 23)),
            Arguments.of(50, listOf(1, 7, 10, 13, 19, 23, 28, 31, 32, 44, 49)),
            Arguments.of(100, listOf(1, 7, 10, 13, 19, 23, 28, 31, 32, 44, 49, 68, 70, 79, 82, 86, 91, 94, 97)),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `happy number test`(num: Int, expected: Boolean) {
        val actual = num.isHappy()
        assertEquals(expected, actual)
    }

    @Test
    fun `lost list test`() {
        val happyList = mutableListOf<Int>()
        listOf(4, 8, 15, 16, 23, 42).each {
            if (it.isHappy()) {
                happyList.add(it)
            }
        }
        assertEquals(23, happyList.first())
    }

    @ParameterizedTest
    @ArgumentsSource(InputListArgumentsProvider::class)
    fun `happy number list test`(times: Int, expected: List<Int>) {
        val happyList = mutableListOf<Int>()
        repeat(times) {
            if (it.isHappy()) {
                happyList.add(it)
            }
        }
        assertEquals(expected, happyList)
    }
}
