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

abstract class AllStarTest<out T : AllStar>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "hello",
                "h*e*l*l*o",
            ),
            Arguments.of(
                "abc",
                "a*b*c",
            ),
            Arguments.of(
                "ab",
                "a*b",
            ),
            Arguments.of(
                "",
                "",
            ),
            Arguments.of(
                "a",
                "a",
            ),
            Arguments.of(
                "3.14",
                "3*.*1*4",
            ),
            Arguments.of(
                "Chocolate",
                "C*h*o*c*o*l*a*t*e",
            ),
            Arguments.of(
                "1234",
                "1*2*3*4",
            ),
            Arguments.of(
                "leetcode",
                "l*e*e*t*c*o*d*e",
            ),
            Arguments.of(
                "codingbat",
                "c*o*d*i*n*g*b*a*t",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `all star test`(str: String, expected: String) {
        val actual = strategy(str)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class AllStarIterativeTest : AllStarTest<AllStar>(AllStarIterative())
class AllStarRecursiveTest : AllStarTest<AllStar>(AllStarRecursive())
class AllStarMapTest : AllStarTest<AllStar>(AllStarMap())
