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

abstract class ClimbingStairsTest<out T : ClimbingStairs>(private val strategy: T) {
    class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(1, 1),
            Arguments.of(2, 2),
            Arguments.of(3, 3),
            Arguments.of(4, 5),
            Arguments.of(5, 8),
            Arguments.of(6, 13),
            Arguments.of(7, 21),
            Arguments.of(8, 34),
            Arguments.of(9, 55),
            Arguments.of(10, 89),
            Arguments.of(20, 10946),
            Arguments.of(30, 1346269),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `climb stairs test`(num: Int, expected: Int) {
        val actual = strategy.invoke(num)
        assertEquals(expected, actual)
    }
}

class ClimbingStairsBruteForceTest : ClimbingStairsTest<ClimbingStairsBruteForce>(ClimbingStairsBruteForce())
class ClimbingStairsRecursionMemoTest :
    ClimbingStairsTest<ClimbingStairsRecursionMemo>(ClimbingStairsRecursionMemo())

class ClimbingStairsDPTest : ClimbingStairsTest<ClimbingStairsDP>(ClimbingStairsDP())
class ClimbingStairsFibonacciTest : ClimbingStairsTest<ClimbingStairsFibonacci>(ClimbingStairsFibonacci())
class ClimbingStairsBinetsMethodTest :
    ClimbingStairsTest<ClimbingStairsBinetsMethod>(ClimbingStairsBinetsMethod())

class ClimbingStairsFibonacciFormulaTest :
    ClimbingStairsTest<ClimbingStairsFibonacciFormula>(ClimbingStairsFibonacciFormula())
