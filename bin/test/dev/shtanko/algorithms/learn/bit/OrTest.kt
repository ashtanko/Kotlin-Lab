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

package dev.shtanko.algorithms.learn.bit

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class OrTest {

    @TestFactory
    fun `given two integers when or operator then new decimal number`() = listOf(
        1 to 1 to 1,
        1 to 0 to 1,
        0 to 1 to 1,
        0 to 0 to 0,
        2 to 2 to 2,
        12 to 25 to 29,
    ).map { (input, expected) ->
        val (x, y) = input
        DynamicTest.dynamicTest("$x or $y = $expected") {
            assertThat(Or.simpleOr(x, y)).isEqualTo(expected)
        }
    }
}
