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

abstract class SqrtTest<out T : SqrtStrategy>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sqrt test`(num: Int, expected: Int) {
        val actual = strategy.invoke(num)
        assertEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = listOf(
            0 to 0,
            1 to 1,
            4 to 2,
            9 to 3,
            16 to 4,
            25 to 5,
            36 to 6,
            49 to 7,
            64 to 8,
            81 to 9,
            100 to 10,
            121 to 11,
            144 to 12,
            169 to 13,
            196 to 14,
            225 to 15,
            256 to 16,
            289 to 17,
            324 to 18,
            361 to 19,
            400 to 20,
            441 to 21,
            484 to 22,
            529 to 23,
            576 to 24,
        ).map { Arguments.of(it.first, it.second) }.stream()
    }
}

class SqrtBSTest : SqrtTest<SqrtStrategy>(SqrtBS())

class SqrtNewtonTest : SqrtTest<SqrtStrategy>(SqrtNewton())

class SqrtBruteForceTest : SqrtTest<SqrtStrategy>(SqrtBruteForce())
