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

abstract class NthMagicalNumberTest<out T : NthMagicalNumber>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `nth magical number test`(num: Int, a: Int, b: Int, expected: Int) {
        val actual = strategy.invoke(num, a, b)
        assertEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(1, 2, 3, 2),
            Arguments.of(4, 2, 3, 6),
            Arguments.of(5, 2, 4, 10),
            Arguments.of(3, 6, 4, 8),
        )
    }
}

class NthMagicalNumberMathTest : NthMagicalNumberTest<NthMagicalNumberMath>(NthMagicalNumberMath())

class NthMagicalNumberBSTest : NthMagicalNumberTest<NthMagicalNumberBS>(NthMagicalNumberBS())
