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

abstract class ThreeSumMultiTest<out T : ThreeSumMulti>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 1, 2, 2, 3, 3, 4, 4, 5, 5),
                8,
                20,
            ),
            Arguments.of(
                intArrayOf(1, 1, 2, 2, 2, 2),
                5,
                12,
            ),
            Arguments.of(
                intArrayOf(),
                0,
                0,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                3,
                0,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                6,
                1,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                7,
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `three sum multi test`(arr: IntArray, target: Int, expected: Int) {
        val actual = strategy.invoke(arr, target)
        assertThat(actual).isEqualTo(expected)
    }
}

class TSMThreePointerTest : ThreeSumMultiTest<TSMThreePointer>(TSMThreePointer())
class TSMCountingCasesTest : ThreeSumMultiTest<TSMCountingCases>(TSMCountingCases())
class TSMAdaptTest : ThreeSumMultiTest<TSMAdapt>(TSMAdapt())
