/*
 * Copyright 2021 Oleksii Shtanko
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

abstract class EvalRPNTest<out T : EvalRPN>(private val solution: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf("2", "1", "+", "3", "*"),
                9,
            ),
            Arguments.of(
                arrayOf("4", "13", "5", "/", "+"),
                6,
            ),
            Arguments.of(
                arrayOf("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"),
                22,
            ),
            Arguments.of(
                arrayOf("1"),
                1,
            ),
            Arguments.of(
                arrayOf("2", "1", "-", "3", "*"),
                3,
            ),
            Arguments.of(
                arrayOf("4", "13", "5", "/", "-"),
                2,
            ),
            Arguments.of(
                arrayOf("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "-"),
                12,
            ),
            Arguments.of(
                arrayOf("1", "1", "+"),
                2,
            ),
            Arguments.of(
                arrayOf("1", "1", "-"),
                0,
            ),
            Arguments.of(
                arrayOf("1", "1", "*"),
                1,
            ),
            Arguments.of(
                arrayOf("1", "1", "/"),
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `eval RPN test`(tokens: Array<String>, expected: Int) {
        val actual = solution(tokens)
        assertThat(actual).isEqualTo(expected)
    }
}

class RPNInPlaceTest : EvalRPNTest<RPNInPlace>(RPNInPlace())
class RPNStackTest : EvalRPNTest<RPNStack>(RPNStack())
