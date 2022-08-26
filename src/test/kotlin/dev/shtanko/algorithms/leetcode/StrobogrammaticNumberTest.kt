/*
 * Copyright 2021 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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

internal abstract class StrobogrammaticNumberTest<out T : StrobogrammaticNumber>(private val strategy: T) {
    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "",
                true,
            ),
            Arguments.of(
                "1",
                true,
            ),
            Arguments.of(
                "69",
                true,
            ),
            Arguments.of(
                "88",
                true,
            ),
            Arguments.of(
                "962",
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `is strobogrammatic number test`(num: String, expected: Boolean) {
        val actual = strategy.perform(num)
        assertThat(actual).isEqualTo(expected)
    }
}

internal class StrobogrammaticRotatedTest : StrobogrammaticNumberTest<StrobogrammaticRotated>(StrobogrammaticRotated())
internal class StrobogrammaticTwoPointersTest :
    StrobogrammaticNumberTest<StrobogrammaticTwoPointers>(StrobogrammaticTwoPointers())
