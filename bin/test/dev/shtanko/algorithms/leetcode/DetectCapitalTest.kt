/*
 * Copyright 2021 Oleksii Shtanko
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

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DetectCapitalTest {

    @TestFactory
    fun `detect capital use character by character test`() = testData.map { (input, expected) ->
        DynamicTest.dynamicTest("When $input we get $expected") {
            assertDetectCapitalUse(input, expected, DetectCapitalCharacter())
        }
    }

    @TestFactory
    fun `detect capital use regex test`() = testData.map { (input, expected) ->
        DynamicTest.dynamicTest("When $input we get $expected") {
            assertDetectCapitalUse(input, expected, DetectCapitalRegex())
        }
    }

    private fun assertDetectCapitalUse(input: String, expected: Boolean, strategy: DetectCapital) {
        assertThat(strategy.detectCapitalUse(input)).isEqualTo(expected)
    }

    companion object {
        private val testData = listOf(
            "" to false,
            "USA" to true,
            "FlaG" to false,
            "g" to true,
            "Google" to true,
            "leetcode" to true,
            "Leetcode" to true,
            "LEETCODE" to true,
            "SDKJFGHSDUGFUIESGFIUESGFIUSGEFUISDGFUISDGFUIGEIUSGEYFGDSIUFGSDUIFGHUIDSGFUIDSGFUISDGFUIDSGFIUSD" to true,
            "SDKJFGHSDUGFUIESGFIUESGFIUSGEFUISDGFUISDGFUIGEIUSGEYFGDSIUFGSDUIfGHUIDSGFUIDSGFUISDGFUIDSGFIUSD" to false,
        )
    }
}
