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

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class MaxValue2Test<out T : MaxValue2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 4),
                    intArrayOf(3, 4, 3),
                    intArrayOf(2, 3, 1),
                ),
                2,
                7,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 4),
                    intArrayOf(3, 4, 3),
                    intArrayOf(2, 3, 10),
                ),
                2,
                10,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 1, 1),
                    intArrayOf(2, 2, 2),
                    intArrayOf(3, 3, 3),
                    intArrayOf(4, 4, 4),
                ),
                3,
                9,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `max value test`(events: Array<IntArray>, k: Int, expected: Int) {
        val actual = strategy.invoke(events, k)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class MaxValue2TopDownTest : MaxValue2Test<MaxValue2>(MaxValue2TopDown())
class MaxValue2BottomUpTest : MaxValue2Test<MaxValue2>(MaxValue2BottomUp())
class MaxValue2TopDownBSTest : MaxValue2Test<MaxValue2>(MaxValue2TopDownBS())
class MaxValue2SimpleTopDownTest : MaxValue2Test<MaxValue2>(MaxValue2SimpleTopDown())
