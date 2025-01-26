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

class WordSubstitutionTest {

    @Test
    fun `word substitution test`() {
        val substitutions = mapOf(
            "is" to "was",
            "was" to "then",
            "then" to "be",
            "be" to "but",
            "this" to "that",
            "that" to "them",
            "them" to "it",
            "it" to "their",
            "soft" to "hard",
            "hard" to "rock",
            "rock" to "mountain",
            "true" to "false",
            "false" to "falsy",
        )
        val testList = listOf(
            "is",
            "this",
            "them",
            "it",
            "soft",
            "rock",
            "mountain",
            "false",
            "true",
            "was",
            "be",
            "that",
            "hard",
            "falsy",
            "because",
        )

        val actual = wordSubstitution(testList, substitutions)
        val expected = listOf(
            "but",
            "their",
            "their",
            "their",
            "mountain",
            "mountain",
            "mountain",
            "falsy",
            "falsy",
            "but",
            "but",
            "their",
            "mountain",
            "falsy",
            "because",
        )
        assertThat(actual).isEqualTo(expected)
    }
}
