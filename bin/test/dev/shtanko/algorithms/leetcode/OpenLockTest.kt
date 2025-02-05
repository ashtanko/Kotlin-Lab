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

abstract class OpenLockTest<out T : OpenLock>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf("0201", "0101", "0102", "1212", "2002"),
                "0202",
                6,
            ),
            Arguments.of(
                arrayOf("8888"),
                "0009",
                1,
            ),
            Arguments.of(
                arrayOf("8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"),
                "8888",
                -1,
            ),
            Arguments.of(
                arrayOf("0000"),
                "8888",
                -1,
            ),
            Arguments.of(
                arrayOf<String>(),
                "",
                -1,
            ),
            Arguments.of(
                arrayOf("0000"),
                "",
                -1,
            ),
            Arguments.of(
                arrayOf("0000"),
                "0000",
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `open lock test`(deadEnds: Array<String>, target: String, expected: Int) {
        val actual = strategy.invoke(deadEnds, target)
        assertThat(actual).isEqualTo(expected)
    }
}

class LockOpenerBFSTest : OpenLockTest<OpenLock>(LockOpenerBFS())
