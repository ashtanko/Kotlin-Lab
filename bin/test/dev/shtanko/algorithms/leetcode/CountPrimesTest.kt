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

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

abstract class CountPrimesTest<out T : CountPrimesStrategy>(private val strategy: T) {

    @TestFactory
    fun `count primes test`() = listOf(
        0 to 0,
        1 to 0,
        3 to 1,
        4 to 2,
        5 to 2,
        7 to 3,
        11 to 4,
        10 to 4,
        13 to 5,
        1_000_000 to 78498,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("count primes of $input then get $expected") {
            val actual = strategy.invoke(input)
            Assertions.assertThat(actual).isEqualTo(expected)
        }
    }
}

class CountPrimesBrutForceTest : CountPrimesTest<CountPrimesBrutForce>(CountPrimesBrutForce())

class CountPrimesSpaceComplexityTest : CountPrimesTest<CountPrimesTimeComplexity>(CountPrimesTimeComplexity())
