/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.algorithms.codingbat.recursion1

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class ChangeXYTest<out T : ChangeXY>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "codex",
                "codey",
            ),
            Arguments.of(
                "xxhixx",
                "yyhiyy",
            ),
            Arguments.of(
                "xhixhix",
                "yhiyhiy",
            ),
            Arguments.of(
                "x",
                "y",
            ),
            Arguments.of(
                "y",
                "y",
            ),
            Arguments.of(
                "hiy",
                "hiy",
            ),
            Arguments.of(
                "",
                "",
            ),
            Arguments.of(
                "xxx",
                "yyy",
            ),
            Arguments.of(
                "yyhxyi",
                "yyhyyi",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `change XY test`(str: String, expected: String) {
        val actual = strategy(str)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class ChangeXYIterativeTest : ChangeXYTest<ChangeXY>(ChangeXYIterative())
class ChangeXYIterative2Test : ChangeXYTest<ChangeXY>(ChangeXYIterative2())
class ChangeXYRecursiveTest : ChangeXYTest<ChangeXY>(ChangeXYRecursive())
