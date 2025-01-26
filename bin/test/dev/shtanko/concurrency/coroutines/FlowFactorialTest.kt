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

package dev.shtanko.concurrency.coroutines

import dev.shtanko.concurrency.TestBase
import dev.shtanko.utils.measureMemFormatted
import java.util.stream.Stream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class FlowFactorialTest : TestBase() {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                1,
                "1",
            ),
            Arguments.of(
                2,
                "2",
            ),
            Arguments.of(
                3,
                "6",
            ),
            Arguments.of(
                4,
                "24",
            ),
            Arguments.of(
                5,
                "120",
            ),
            Arguments.of(
                6,
                "720",
            ),
            Arguments.of(
                7,
                "5040",
            ),
            Arguments.of(
                8,
                "40320",
            ),
            Arguments.of(
                9,
                "362880",
            ),
            Arguments.of(
                10,
                "3628800",
            ),
            Arguments.of(
                100,
                "93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `flow factorial test`(num: Int, expected: String) = runTest {
        val task = flowFactorial(num)
        val (msg, res) = measureMemFormatted("flow factorial") {
            withContext(Dispatchers.Default) { task }.toString()
        }
        println(msg)
        assertThat(res).isEqualTo(expected)
    }
}
