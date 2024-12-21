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

abstract class FindGoodStringsTest<out T : FindGoodStrings>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(2, "aa", "da", "b", 51),
            Arguments.of(8, "leetcode", "leetgoes", "leet", 0),
            Arguments.of(2, "gx", "gz", "x", 2),
            Arguments.of(8, "pzdanyao", "wgpmtywi", "sdka", 500543753),
            Arguments.of(2, "p", "p", "p", 0),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find good strings test`(num: Int, s1: String, s2: String, evil: String, expected: Int) {
        val actual = strategy.invoke(num, s1, s2, evil)
        assertThat(actual).isEqualTo(expected)
    }
}

class FindGoodStringsDFSTest : FindGoodStringsTest<FindGoodStrings>(FindGoodStringsDFS())
