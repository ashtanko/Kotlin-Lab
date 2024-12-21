/*
 * Copyright 2023 Oleksii Shtanko
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

import dev.shtanko.algorithms.leetcode.GarbageCollectionStrategy.HashMap
import dev.shtanko.algorithms.leetcode.GarbageCollectionStrategy.HashMapInPlace
import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class GarbageCollectionTest<out T : GarbageCollection>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf("G", "P", "GP", "GG"),
                intArrayOf(2, 4, 3),
                21,
            ),
            Arguments.of(
                arrayOf("MMM", "PGM", "GP"),
                intArrayOf(3, 10),
                37,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `garbage collection test`(garbage: Array<String>, travel: IntArray, expected: Int) {
        val actual = strategy(garbage, travel)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class GarbageCollectionHashMapTest : GarbageCollectionTest<GarbageCollection>(HashMap)
class GarbageCollectionHashMapInPlaceTest : GarbageCollectionTest<GarbageCollection>(HashMapInPlace)
