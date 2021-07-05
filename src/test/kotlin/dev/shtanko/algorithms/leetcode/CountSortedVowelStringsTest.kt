/*
 * Copyright 2020 Alexey Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal abstract class CountSortedVowelStringsTest<out T : CountSortedVowelStrings>(private val strategy: T) {

    @TestFactory
    internal fun `count vowel strings`() = listOf(
        1 to 5,
        2 to 15,
        33 to 66045
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("count vowel strings of $input then get $expected") {
            val actual = strategy.perform(input)
            assertThat(actual).isEqualTo(expected)
        }
    }
}

internal class CountSortedVowelBruteForceTest :
    CountSortedVowelStringsTest<CountSortedVowelBruteForce>(CountSortedVowelBruteForce())

internal class CountSortedVowelRecursionTest :
    CountSortedVowelStringsTest<CountSortedVowelRecursion>(CountSortedVowelRecursion())

internal class CountSortedVowelTopDynamicTest :
    CountSortedVowelStringsTest<CountSortedVowelTopDynamic>(CountSortedVowelTopDynamic())

internal class CountSortedVowelBottomUpTest :
    CountSortedVowelStringsTest<CountSortedVowelBottomUp>(CountSortedVowelBottomUp())

internal class CountSortedVowelMathTest :
    CountSortedVowelStringsTest<CountSortedVowelMath>(CountSortedVowelMath())
