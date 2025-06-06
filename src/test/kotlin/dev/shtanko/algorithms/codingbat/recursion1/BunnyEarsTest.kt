/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class BunnyEarsTest<out T : BunnyEars>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
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
                4,
            ),
            Arguments.of(
                3,
                6,
            ),
            Arguments.of(
                4,
                8,
            ),
            Arguments.of(
                5,
                10,
            ),
            Arguments.of(
                12,
                24,
            ),
            Arguments.of(
                50,
                100,
            ),
            Arguments.of(
                234,
                468,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `bunnies test`(bunnies: Int, expected: Int) {
        val actual = strategy(bunnies)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class BunnyEarsIterativeTest : BunnyEarsTest<BunnyEars>(BunnyEarsIterative())
class BunnyEarsRecursiveTest : BunnyEarsTest<BunnyEars>(BunnyEarsRecursive())
class BunnyEarsMemoTest : BunnyEarsTest<BunnyEars>(BunnyEarsMemo())
class BunnyEarsTopDownTest : BunnyEarsTest<BunnyEars>(BunnyEarsTopDown())
