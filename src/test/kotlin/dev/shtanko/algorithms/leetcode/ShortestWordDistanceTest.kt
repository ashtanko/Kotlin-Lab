/*
 * Copyright 2020 Alexey Shtanko
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
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal abstract class ShortestWordDistanceTest<out T : ShortestWordDistanceStrategy>(private val strategy: T) {
    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf("practice", "makes", "perfect", "coding", "makes"),
                "coding",
                "practice",
                3
            ),
            Arguments.of(
                arrayOf("practice", "makes", "perfect", "coding", "makes"),
                "makes",
                "coding",
                1
            )
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `shortest distance test`(words: Array<String>, word1: String, word2: String, expected: Int) {
        val actual = strategy.perform(words, word1, word2)
        assertEquals(expected, actual)
    }
}

internal class ShortestWordDistanceBruteForceTest :
    ShortestWordDistanceTest<ShortestWordDistanceBruteForce>(ShortestWordDistanceBruteForce())

internal class ShortestWordDistanceOnePassTest :
    ShortestWordDistanceTest<ShortestWordDistanceOnePass>(ShortestWordDistanceOnePass())
