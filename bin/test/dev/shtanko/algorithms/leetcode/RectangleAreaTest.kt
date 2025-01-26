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

abstract class RectangleAreaTest<out T : RectangleArea>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                -3, 0, 3, 4, 0, -1, 9, 2,
                45,
            ),
            Arguments.of(
                -2, -2, 2, 2, -2, -2, 2, 2,
                16,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `compute area test`(
        ax1: Int,
        ay1: Int,
        ax2: Int,
        ay2: Int,
        bx1: Int,
        by1: Int,
        bx2: Int,
        by2: Int,
        expected: Int,
    ) {
        val actual = strategy.computeArea(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2)
        assertThat(actual).isEqualTo(expected)
    }
}

class MathAndGeometryTest : RectangleAreaTest<RectangleArea>(MathAndGeometry())
