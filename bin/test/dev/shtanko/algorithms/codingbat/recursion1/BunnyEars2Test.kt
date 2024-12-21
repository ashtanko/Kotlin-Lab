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

abstract class BunnyEars2Test<out T : BunnyEars2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                0,
            ),
            Arguments.of(
                1,
                2,
            ),
            Arguments.of(
                2,
                5,
            ),
            Arguments.of(
                3,
                7,
            ),
            Arguments.of(
                4,
                10,
            ),

            Arguments.of(
                5,
                12,
            ),
            Arguments.of(
                6,
                15,
            ),
            Arguments.of(
                7,
                17,
            ),
            Arguments.of(
                8,
                20,
            ),
            Arguments.of(
                9,
                22,
            ),
            Arguments.of(
                10,
                25,
            ),
            Arguments.of(
                11,
                27,
            ),
            Arguments.of(
                12,
                30,
            ),
            Arguments.of(
                13,
                32,
            ),
            Arguments.of(
                14,
                35,
            ),
            Arguments.of(
                15,
                37,
            ),
            Arguments.of(
                16,
                40,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `bunnies 2 test`(bunnies: Int, expected: Int) {
        val actual = strategy(bunnies)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class BunnyEars2IterativeTest : BunnyEars2Test<BunnyEars2>(BunnyEars2Iterative())
class BunnyEars2RecursiveTest : BunnyEars2Test<BunnyEars2>(BunnyEars2Recursive())
class BunnyEars2MemoTest : BunnyEars2Test<BunnyEars2>(BunnyEars2Memo())
class BunnyEars2BottomUpTest : BunnyEars2Test<BunnyEars2>(BunnyEars2BottomUp())
class BunnyEars2TopDownTest : BunnyEars2Test<BunnyEars2>(BunnyEars2TopDown())
