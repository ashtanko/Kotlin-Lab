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

class TwoCitySchedulingTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(10, 20),
                    intArrayOf(30, 200),
                    intArrayOf(400, 50),
                    intArrayOf(30, 20),
                ),
                110,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(259, 770),
                    intArrayOf(448, 54),
                    intArrayOf(926, 667),
                    intArrayOf(184, 139),
                    intArrayOf(840, 118),
                    intArrayOf(577, 469),
                ),
                1859,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(515, 563),
                    intArrayOf(451, 713),
                    intArrayOf(537, 709),
                    intArrayOf(343, 819),
                    intArrayOf(855, 779),
                    intArrayOf(457, 60),
                    intArrayOf(650, 359),
                    intArrayOf(631, 42),
                ),
                3086,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `two city scheduling test`(costs: Array<IntArray>, expected: Int) {
        assertEquals(expected, costs.twoCitySchedCost())
    }
}
