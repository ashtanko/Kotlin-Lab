/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class FourKeysKeyboardTest<out T : FourKeysKeyboard>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(3, 3),
            Arguments.of(7, 9),
            Arguments.of(11, 27),
            Arguments.of(20, 324),
            Arguments.of(50, 1327104),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `max A test`(num: Int, expected: Int) {
        val actual = strategy.invoke(num)
        assertThat(actual, equalTo(expected))
    }
}

class FourKeysKeyboardDPTest : FourKeysKeyboardTest<FourKeysKeyboardDP>(FourKeysKeyboardDP())
class FourKeysKeyboardMathTest : FourKeysKeyboardTest<FourKeysKeyboardMath>(FourKeysKeyboardMath())
