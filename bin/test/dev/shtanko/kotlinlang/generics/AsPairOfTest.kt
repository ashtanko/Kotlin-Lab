/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.kotlinlang.generics

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AsPairOfTest {

    @Test
    fun `string to something test`() {
        val input = "items" to listOf(1, 2, 3)
        val actual = input.asPairOf<String, Any>()
        assertThat(actual).isNotNull
    }

    @Test
    fun `string to int test`() {
        val input = "items" to listOf(1, 2, 3)
        val actual = input.asPairOf<String, Int>()
        assertThat(actual).isNull()
    }

    @Test
    fun `string to list test`() {
        val input = "items" to listOf(1, 2, 3)
        val actual = input.asPairOf<String, List<*>>()
        assertThat(actual).isNotNull
    }

    @Test
    fun `string to string list test`() {
        val input = "items" to listOf(1, 2, 3)
        val actual = input.asPairOf<String, List<String>>()
        assertThat(actual).isNotNull
    }
}
