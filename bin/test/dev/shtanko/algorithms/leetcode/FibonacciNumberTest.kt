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

abstract class FibonacciNumberTest<out T : FibonacciStrategy>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> =
            numberProvider().stream().map { (num: Int, expected: Long) ->
                Arguments.of(num, expected)
            }

        private fun numberProvider(): List<Pair<Int, Long>> = listOf(
            0 to 0L,
            1 to 1L,
            2 to 1L,
            3 to 2L,
            4 to 3L,
            5 to 5L,
            6 to 8L,
            7 to 13L,
            8 to 21L,
            9 to 34L,
            10 to 55L,
            11 to 89L,
            12 to 144L,
            13 to 233L,
            14 to 377L,
            15 to 610L,
            16 to 987L,
            17 to 1597L,
            18 to 2584L,
            19 to 4181L,
            20 to 6765L,
            21 to 10946L,
            22 to 17711L,
            23 to 28657L,
            24 to 46368L,
            25 to 75025L,
            26 to 121393L,
            27 to 196418L,
            28 to 317811L,
            29 to 514229L,
            30 to 832040L,
            31 to 1346269L,
            32 to 2178309L,
            33 to 3524578L,
            34 to 5702887L,
            35 to 9227465L,
            36 to 14930352L,
            37 to 24157817L,
            38 to 39088169L,
            39 to 63245986L,
            // 40 to 102334155L,
            // 41 to 165580141L,
            // 42 to 267914296L,
            // 43 to 433494437L,
            // 44 to 701408733L,
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `fibonacci test`(num: Int, expected: Long) {
        val actual = strategy.invoke(num)
        assertEquals(expected, actual)
    }
}

class FibonacciIterativeTest : FibonacciNumberTest<FibonacciStrategy>(FibonacciIterative())
class FibonacciRecursionTest : FibonacciNumberTest<FibonacciStrategy>(FibonacciRecursion())
class FibonacciOptimizedRecursionTest : FibonacciNumberTest<FibonacciStrategy>(FibonacciOptimizedRecursion())
class FibonacciBottomUpTest : FibonacciNumberTest<FibonacciStrategy>(FibonacciBottomUp())
class FibonacciTopDownTest : FibonacciNumberTest<FibonacciStrategy>(FibonacciTopDown())
class FibonacciIterativeTopDownTest : FibonacciNumberTest<FibonacciStrategy>(FibonacciIterativeTopDown())
class FibonacciMatrixExponentiationTest : FibonacciNumberTest<FibonacciStrategy>(FibonacciMatrixExponentiation())
class FibonacciMathTest : FibonacciNumberTest<FibonacciStrategy>(FibonacciMath())
