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

abstract class MinimumDeleteSumTest<out T : MinimumDeleteSum>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "sea",
                "eat",
                231,
            ),
            Arguments.of(
                "delete",
                "leet",
                403,
            ),
            Arguments.of(
                "",
                "leet",
                426,
            ),
            Arguments.of(
                "delete",
                "",
                627,
            ),
            Arguments.of(
                "",
                "",
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `minimum delete sum test`(s1: String, s2: String, expected: Int) {
        val actual = strategy.invoke(s1, s2)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class MinimumDeleteSumRecursionTest : MinimumDeleteSumTest<MinimumDeleteSum>(MinimumDeleteSumRecursion())
class MinimumDeleteSumTopDownTest : MinimumDeleteSumTest<MinimumDeleteSum>(MinimumDeleteSumTopDown())
class MinimumDeleteSumBottomUpTest : MinimumDeleteSumTest<MinimumDeleteSum>(MinimumDeleteSumBottomUp())
class MinimumDeleteSumBottomUpOtpTest : MinimumDeleteSumTest<MinimumDeleteSum>(MinimumDeleteSumBottomUpOtp())
