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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class ReformatTheStringTest {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `reformat string test`(str: String, expected: String) {
        val actual = str.reformat()
        assertEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "a0b1c2",
                "a0b1c2",
            ),
            Arguments.of(
                "leetcode",
                "",
            ),
            Arguments.of(
                "1229857369",
                "",
            ),
            Arguments.of(
                "covid2019",
                "c2o0v1i9d",
            ),
            Arguments.of(
                "ab123",
                "1a2b3",
            ),
            Arguments.of(
                "",
                "",
            ),
            Arguments.of(
                "q",
                "q",
            ),
        )
    }
}
