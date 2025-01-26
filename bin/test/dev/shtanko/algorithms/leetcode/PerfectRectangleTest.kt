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
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class PerfectRectangleTest<out T : PerfectRectangle>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 1, 3, 3),
                    intArrayOf(3, 1, 4, 2),
                    intArrayOf(3, 2, 4, 4),
                    intArrayOf(1, 3, 2, 4),
                    intArrayOf(2, 3, 3, 4),
                ),
                true,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 1, 2, 3),
                    intArrayOf(1, 3, 2, 4),
                    intArrayOf(3, 1, 4, 2),
                    intArrayOf(3, 2, 4, 4),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 1, 3, 3),
                    intArrayOf(3, 1, 4, 2),
                    intArrayOf(1, 3, 2, 4),
                    intArrayOf(2, 2, 4, 4),
                ),
                false,
            ),
            Arguments.of(
                arrayOf<IntArray>(),
                false,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(),
                ),
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `is rectangle cover test`(rectangles: Array<IntArray>, expected: Boolean) {
        val actual = strategy.isRectangleCover(rectangles)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `events are equal test`() {
        val ev1 = PerfectRectangleSweepLine.Event(0, intArrayOf(0))
        val ev2 = PerfectRectangleSweepLine.Event(0, intArrayOf(0))
        assertThat(ev1).isEqualTo(ev2)
    }

    @Test
    fun `events arent equal test`() {
        val ev1 = PerfectRectangleSweepLine.Event(0, intArrayOf(0))
        val ev2 = PerfectRectangleSweepLine.Event(0, intArrayOf(1))
        assertThat(ev1).isNotEqualTo(ev2)
    }
}

class PerfectRectangleSweepLineTest : PerfectRectangleTest<PerfectRectangle>(PerfectRectangleSweepLine())
class PerfectRectangleEasyTest : PerfectRectangleTest<PerfectRectangle>(PerfectRectangleEasy())
