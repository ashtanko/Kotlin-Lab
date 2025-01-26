/*
 * Copyright 2022 Oleksii Shtanko
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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class TopKFrequentTest<out T : TopKFrequent>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf("i", "love", "leetcode", "i", "love", "coding"),
                2,
                listOf("i", "love"),
            ),
            Arguments.of(
                arrayOf("the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"),
                4,
                listOf("the", "is", "sunny", "day"),
            ),
            Arguments.of(
                arrayOf<String>(),
                0,
                emptyList<String>(),
            ),
            Arguments.of(
                arrayOf("a", "b"),
                0,
                emptyList<String>(),
            ),
            Arguments.of(
                arrayOf("a", "b"),
                1,
                listOf("a"),
            ),
            Arguments.of(
                arrayOf("a", "b"),
                2,
                listOf("a", "b"),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `top K frequent test`(words: Array<String>, k: Int, expected: List<String>) {
        val actual = strategy.invoke(words, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class TopKFrequentMapTest : TopKFrequentTest<TopKFrequent>(TopKFrequentMap())
class TopKFrequentSortingTest : TopKFrequentTest<TopKFrequent>(TopKFrequentSorting())
class TopKFrequentFurthestBuildingMinHeapTest : TopKFrequentTest<TopKFrequent>(TopKFrequentMinHeap())
class TopKFrequentTrieTest : TopKFrequentTest<TopKFrequent>(TopKFrequentTrie())
class TopKFrequentBucketSortTest : TopKFrequentTest<TopKFrequent>(TopKFrequentBucketSort())
