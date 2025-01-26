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

package dev.shtanko.datastructures

import java.util.stream.Stream
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class ImmutableSetTest {

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(3, 1, false),
            Arguments.of(3, 2, false),
            Arguments.of(3, 4, false),
            Arguments.of(3, 5, false),
            Arguments.of(3, 3, true),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `contains test`(num: Int, value: Int, expected: Boolean) {
        val actual = immutableSetOf(num).contains(value)
        assertThat(actual, equalTo(expected))
    }

    @Test
    internal fun `contains all test`() {
        val set = immutableSetOf(*(10 downTo 1).toList().toTypedArray())
        assertTrue(set.containsAll(listOf(9, 8)))
        assertTrue(set.containsAll(listOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)))
        assertFalse(set.containsAll(listOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1)))
        assertFalse(set.containsAll(listOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0)))
    }

    @Test
    internal fun `is empty test`() {
        val set = ImmutableSet(arrayOf<Int>())
        assertTrue(set.isEmpty())
    }

    @Test
    internal fun `is not empty test`() {
        val set = ImmutableSet(arrayOf(3))
        assertFalse(set.isEmpty())
    }

    @Test
    internal fun `same elements test`() {
        val set = ImmutableSet(arrayOf(3, 3, 3, 3))
        assertEquals(set.toList(), listOf(3, 3, 3, 3))
    }

    @Test
    internal fun `contains test 2`() {
        val set = immutableSetOf(*(10 downTo 1).toList().toTypedArray())
        for (v in set) {
            assertTrue(set.contains(v))
        }
        assertEquals(10, set.size)
        assertFalse(set.isEmpty())
        assertFalse(set.contains(42))
        assertFalse(set.contains(-42))
    }

    @Test
    internal fun `contains in ranges test`() {
        val set = immutableSetOf(*(0..100).toList().toTypedArray())
        for (v in -100..-1) {
            assertFalse(set.contains(v))
        }
        for (v in 0..100) {
            assertTrue(set.contains(v))
        }
        for (v in 101..200) {
            assertFalse(set.contains(v))
        }
    }
}
