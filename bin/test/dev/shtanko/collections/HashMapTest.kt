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

import java.util.stream.Stream
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class HashMapTest {

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(intArrayOf(), listOf<Int>()),
            Arguments.of(intArrayOf(4), listOf(4)),
            Arguments.of(intArrayOf(6, 1, 8, 3, 9, 10, 22, 4), listOf(1, 3, 4, 6, 22, 8, 9, 10)),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `hash map order test`(arr: IntArray, expected: List<Int>) {
        val map: MutableMap<Int, Int> = HashMap()
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
    internal fun `put same values test`() {
        val map: MutableMap<Int, Int> = HashMap()
        map[1] = 100
        map[1] = 200
        assertThat(map[2], equalTo(null))
        assertThat(map[1], equalTo(200))
    }

    @Test
    internal fun `put null test`() {
        val map: MutableMap<Int?, Int?> = HashMap()
        map[null] = 10
        map[1] = 200
        assertThat(map[null], equalTo(10))
        assertThat(map[1], equalTo(200))
    }
}
