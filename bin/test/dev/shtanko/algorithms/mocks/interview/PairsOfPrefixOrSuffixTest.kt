/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.algorithms.mocks.interview

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PairsOfPrefixOrSuffixTest {
    @Test
    fun `find prefix or suffix pairs test`() {
        val testCase = listOf(
            "an",
            "ana",
            "anar",
            "anarchy",
            "robolectic",
            "robo",
            "ro",
            "row",
            "dim",
            "dima",
            "duma",
            "dumas",
            "xenial",
            "rock",
            "rockey",
            "chunk",
            "chadder",
            "adder",
            "der",
            "uma",
            "im",
            "o",
            "ow",
            "archy",
            "ar",
        )
        val actual = findPrefixOrSuffixPairs(testCase)
        val expected = listOf(
            "anar" to "anarchy",
            "ana" to "anarchy",
            "ana" to "anar",
            "an" to "anarchy",
            "an" to "anar",
            "an" to "ana",
            "ar" to "archy",
            "robo" to "robolectic",
            "rock" to "rockey",
            "ro" to "robolectic",
            "ro" to "robo",
            "ro" to "row",
            "ro" to "rockey",
            "ro" to "rock",
            "dim" to "dima",
            "duma" to "dumas",
            "o" to "ow",
            "uma" to "duma",
            "ar" to "anar",
            "adder" to "chadder",
            "der" to "chadder",
            "der" to "adder",
            "archy" to "anarchy",
            "o" to "robo",
            "o" to "ro",
            "ow" to "row",
            "im" to "dim",
        )
        assertThat(actual).isEqualTo(expected)
    }
}
