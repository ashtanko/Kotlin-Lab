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

class RichestCustomerWealthTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf<IntArray>(),
                0,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(),
                ),
                0,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(0),
                ),
                0,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 3),
                    intArrayOf(3, 2, 1),
                ),
                6,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 5),
                    intArrayOf(7, 3),
                    intArrayOf(3, 5),
                ),
                10,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 8, 7),
                    intArrayOf(7, 1, 3),
                    intArrayOf(1, 9, 5),
                ),
                17,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `maximum wealth test`(accounts: Array<IntArray>, expected: Int) {
        val actual = RichestCustomerWealth.maximumWealth(accounts)
        assertThat(actual, equalTo(expected))
    }
}
