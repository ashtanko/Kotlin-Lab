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

abstract class FreedomTrailTest<out T : FreedomTrail>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "godding",
                "gd",
                4,
            ),
            Arguments.of(
                "godding",
                "godding",
                13,
            ),
            Arguments.of(
                "godding",
                "god",
                5,
            ),
            Arguments.of(
                "godding",
                "godding",
                13,
            ),
            Arguments.of(
                "godding",
                "god",
                5,
            ),
            Arguments.of(
                "",
                "",
                0,
            ),
            Arguments.of(
                "",
                "a",
                0,
            ),
            Arguments.of(
                "a",
                "a",
                1,
            ),
            Arguments.of(
                "a",
                "b",
                1,
            ),
            Arguments.of(
                "a",
                "aa",
                2,
            ),
        )
    }

    @ArgumentsSource(InputArgumentsProvider::class)
    @ParameterizedTest
    fun `find rotate steps test`(ring: String, key: String, expected: Int) {
        val actual = strategy.invoke(ring, key)
        assertThat(actual).isEqualTo(expected)
    }
}

class FreedomTrailDPTest : FreedomTrailTest<FreedomTrail>(FreedomTrailDP())
