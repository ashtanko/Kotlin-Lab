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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class BalancedStringSplitTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of("RLRRLLRLRL", 4),
            Arguments.of("RLLLLRRRLR", 3),
            Arguments.of("LLLLRRRR", 1),
            Arguments.of("RLRRRLLRLL", 2),
            Arguments.of("", 0),
            Arguments.of("R", 0),
            Arguments.of("L", 0),
            Arguments.of("RL", 1),
            Arguments.of("RRR", 0),
            Arguments.of("RRRLLL", 1),
            Arguments.of("RRRRRRLLL", 0),
            Arguments.of("A", 0),
            Arguments.of("AB", 0),
            Arguments.of("ABAB", 0),
            Arguments.of("ABABAB", 0),
            Arguments.of("ABABABAB", 0),
            Arguments.of("ABABABABAB", 0),
            Arguments.of("ABABABABABAB", 0),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `balanced string split test`(str: String, expected: Int) {
        val actual = str.balancedStringSplit()
        assertEquals(expected, actual)
    }
}
