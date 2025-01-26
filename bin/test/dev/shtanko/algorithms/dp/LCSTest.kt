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

package dev.shtanko.algorithms.dp

import dev.shtanko.utils.Quintuple
import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

/**
 * The longest common subsequence test.
 */
abstract class LCSTest<out T : LCS>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> =
            initialArgs.stream().map { (x: String, y: String, m: Int, num: Int, expected: Int) ->
                Arguments.of(
                    x,
                    y,
                    m,
                    num,
                    expected,
                )
            }

        private val initialArgs = listOf(
            Triple("AGGTAB", "GXTXAYB", 4),
            Triple("", "", 0),
            Triple("a", "a", 1),
            Triple("abcd", "ab", 2),
        ).map {
            Quintuple(it.first, it.second, it.first.length, it.second.length, it.third)
        }
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `LCS test`(x: String, y: String, m: Int, num: Int, expected: Int) {
        val actual = strategy.perform(x, y, m, num)
        assertThat(actual).isEqualTo(expected)
    }
}

class LCSRecursiveTest : LCSTest<LCS>(LCSRecursive())
