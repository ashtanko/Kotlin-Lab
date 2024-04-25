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
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class ArrangingCoinsTest<out T : ArrangingCoinsStrategy>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(0, 0),
            Arguments.of(11, 4),
            Arguments.of(1, 1),
            Arguments.of(999, 44),
            Arguments.of(5, 2),
            Arguments.of(8, 3),
            Arguments.of(3, 2),
            Arguments.of(2, 1),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `arrange coins test`(coins: Int, expected: Int) {
        val actual = strategy.invoke(coins)
        assertThat(actual, equalTo(expected))
    }
}

class ArrangingCoinsMathTest : ArrangingCoinsTest<ArrangingCoinsMath>(ArrangingCoinsMath())

class ArrangingCoinsBSTest : ArrangingCoinsTest<ArrangingCoinsBS>(ArrangingCoinsBS())
