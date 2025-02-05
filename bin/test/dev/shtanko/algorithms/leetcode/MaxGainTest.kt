/*
 * Copyright 2024 Oleksii Shtanko
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

abstract class MaxGainTest<out T : MaxGain>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "cdbcbbaaabab",
                4,
                5,
                19,
            ),
            Arguments.of(
                "aabbaaxybbaabb",
                5,
                4,
                20,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun test(str: String, x: Int, y: Int, expected: Int) {
        val actual = strategy(str, x, y)
        assertThat(actual).isEqualTo(expected)
    }
}

class MaxGainStackTest : MaxGainTest<MaxGain>(MaxGainStack())
class MaxGainGreedyTest : MaxGainTest<MaxGain>(MaxGainGreedy())
class MaxGainCountingTest : MaxGainTest<MaxGain>(MaxGainCounting())
