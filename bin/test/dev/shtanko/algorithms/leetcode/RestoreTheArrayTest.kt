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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class RestoreTheArrayTest<out T : RestoreTheArray>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "1000",
                10000,
                1,
            ),
            Arguments.of(
                "1000",
                10,
                0,
            ),
            Arguments.of(
                "1317",
                2000,
                8,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `number of arrays test`(s: String, k: Int, expected: Int) {
        val actual = strategy.numberOfArrays(s, k)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class RestoreTheArrayMemoizationTest : RestoreTheArrayTest<RestoreTheArray>(RestoreTheArrayMemoization())
class RestoreTheArrayDPTest : RestoreTheArrayTest<RestoreTheArray>(RestoreTheArrayDP())
class RestoreTheArrayTopDownTest : RestoreTheArrayTest<RestoreTheArray>(RestoreTheArrayTopDown())
class RestoreTheArrayBottomUpTest : RestoreTheArrayTest<RestoreTheArray>(RestoreTheArrayBottomUp())
