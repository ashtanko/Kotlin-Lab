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

package dev.shtanko.concurrency.coroutines.sequence

import java.util.stream.Stream
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class FibonacciTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                "",
            ),
            Arguments.of(
                1,
                "1",
            ),
            Arguments.of(
                2,
                "1, 1",
            ),
            Arguments.of(
                3,
                "1, 1, 2",
            ),
            Arguments.of(
                10,
                "1, 1, 2, 3, 5, 8, 13, 21, 34, 55",
            ),
            Arguments.of(
                50,
                "1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, -1323752223, 512559680, -811192543, -298632863",
            ),
        )
    }

    @ExperimentalCoroutinesApi
    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `simple test`(num: Int, expected: String) = runTest {
        val actual = fibonacci.take(num).joinToString()
        assertThat(actual).isEqualTo(expected)
    }
}
