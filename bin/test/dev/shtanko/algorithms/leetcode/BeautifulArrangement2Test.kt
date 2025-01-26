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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class BeautifulArrangement2Test<out T : BeautifulArrangement2>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                0,
                intArrayOf(),
            ),
            Arguments.of(
                1,
                0,
                intArrayOf(1),
            ),
            Arguments.of(
                100,
                20,
                intArrayOf(
                    1,
                    2,
                    3,
                    4,
                    5,
                    6,
                    7,
                    8,
                    9,
                    10,
                    11,
                    12,
                    13,
                    14,
                    15,
                    16,
                    17,
                    18,
                    19,
                    20,
                    21,
                    22,
                    23,
                    24,
                    25,
                    26,
                    27,
                    28,
                    29,
                    30,
                    31,
                    32,
                    33,
                    34,
                    35,
                    36,
                    37,
                    38,
                    39,
                    40,
                    41,
                    42,
                    43,
                    44,
                    45,
                    46,
                    47,
                    48,
                    49,
                    50,
                    51,
                    52,
                    53,
                    54,
                    55,
                    56,
                    57,
                    58,
                    59,
                    60,
                    61,
                    62,
                    63,
                    64,
                    65,
                    66,
                    67,
                    68,
                    69,
                    70,
                    71,
                    72,
                    73,
                    74,
                    75,
                    76,
                    77,
                    78,
                    79,
                    80,
                    100,
                    81,
                    99,
                    82,
                    98,
                    83,
                    97,
                    84,
                    96,
                    85,
                    95,
                    86,
                    94,
                    87,
                    93,
                    88,
                    92,
                    89,
                    91,
                    90,
                ),
            ),
            Arguments.of(
                3,
                1,
                intArrayOf(1, 2, 3),
            ),
            Arguments.of(
                3,
                2,
                intArrayOf(1, 3, 2),
            ),
            Arguments.of(
                5,
                2,
                intArrayOf(1, 2, 3, 5, 4),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `construct array test`(num: Int, k: Int, expected: IntArray) {
        val actual = strategy.invoke(num, k)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class BA2ConstructionTest : BeautifulArrangement2Test<BA2Construction>(BA2Construction())
