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

package dev.shtanko.algorithms.sorts

import java.util.stream.Stream
import kotlin.random.Random
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

@Suppress("ArrayPrimitive")
internal abstract class AbstractSortTest<out T : AbstractSortStrategy>(private val strategy: T) {

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(getRandomArray(), true),
            Arguments.of(arrayOf(1, 2, 2, 1), true),
        )

        private fun getRandomArray(): Array<Int> {
            val arr = Array(10_000) { 0 }
            for (i in 0 until 10_000) {
                arr[i] = Random.nextInt(10_000)
            }
            return arr
        }
    }

    internal class InputStringArrayArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf<String>(),
                emptyArray<String>(),
            ),
            Arguments.of(
                arrayOf("A"),
                arrayOf("A"),
            ),
            Arguments.of(
                arrayOf("A", "B", "C"),
                arrayOf("A", "B", "C"),
            ),
            Arguments.of(
                arrayOf("D", "C", "B", "A"),
                arrayOf("A", "B", "C", "D"),
            ),
            Arguments.of(
                arrayOf("A", "c", "B", "e", "d", "F", "y", "G"),
                arrayOf("A", "B", "F", "G", "c", "d", "e", "y"),
            ),
        )
    }

    internal class InputObjectArrayArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(arrayOf(TestObject.empty()), arrayOf(TestObject.empty())),
            Arguments.of(
                arrayOf(
                    TestObject(0, "Jake"),
                    TestObject(3, "William"),
                ),
                arrayOf(
                    TestObject(0, "Jake"),
                    TestObject(3, "William"),
                ),
            ),
            Arguments.of(
                arrayOf(
                    TestObject(0, "William"),
                    TestObject(3, "Anna"),
                ),
                arrayOf(
                    TestObject(3, "Anna"),
                    TestObject(0, "William"),
                ),
            ),
            Arguments.of(
                arrayOf(
                    TestObject(0, "Jake"),
                    TestObject(1, "Anna"),
                    TestObject(2, "Alex"),
                ),
                arrayOf(
                    TestObject(2, "Alex"),
                    TestObject(1, "Anna"),
                    TestObject(0, "Jake"),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputIntArrayArgumentsProvider::class)
    internal fun `integer array test`(arr: IntArray, expected: IntArray) {
        val actual = arr.toTypedArray()
        strategy.perform(actual)
        assertThat(actual.toIntArray()).isEqualTo(expected)
        assertArrayEquals(expected, actual.toIntArray())
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `is sorted test`(arr: Array<Int>, expected: Boolean) {
        strategy.perform(arr)
        val actual = arr.isSorted()
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @ArgumentsSource(InputStringArrayArgumentsProvider::class)
    internal fun `string array test`(arr: Array<String>, expected: Array<String>) {
        strategy.perform(arr)
        assertArrayEquals(expected, arr)
    }

    @ParameterizedTest
    @ArgumentsSource(InputObjectArrayArgumentsProvider::class)
    internal fun `object test`(arr: Array<TestObject>, expected: Array<TestObject>) {
        strategy.perform(arr)
        assertArrayEquals(expected, arr)
    }
}
