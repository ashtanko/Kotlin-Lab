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

package dev.shtanko.collections

import java.util.TreeMap
import java.util.stream.Stream
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class TreeMapTest {

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(intArrayOf(), listOf<Int>()),
            Arguments.of(intArrayOf(1), listOf(1)),
            Arguments.of(intArrayOf(1, 2), listOf(1, 2)),
            Arguments.of(intArrayOf(1, 3, 2), listOf(1, 2, 3)),
            Arguments.of(intArrayOf(3, 2, 1), listOf(1, 2, 3)),
            Arguments.of(intArrayOf(6, 1, 88, 3, 5, 2, 44), listOf(1, 2, 3, 5, 6, 44, 88)),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `tree map order test`(arr: IntArray, expected: List<Int>) {
        val map: MutableMap<Int, Int> = TreeMap()
        val result: MutableList<Int> = ArrayList(arr.size)
        for (i in arr.indices) {
            map[arr[i]] = arr[i]
        }
        for ((k, _) in map) {
            result.add(k)
        }
        assertThat(result, equalTo(expected))
    }

    @Test
    internal fun `put null test`() {
        val map: MutableMap<Int?, Int?> = TreeMap()
        assertThrows<NullPointerException> {
            map[null] = 10
        }
    }
}
