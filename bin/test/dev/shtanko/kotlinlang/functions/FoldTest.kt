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

package dev.shtanko.kotlinlang.functions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FoldTest {

    @Test
    fun `fold add test`() {
        val items = listOf(1, 2, 3, 4, 5)
        items.map { }
        val actual = items.fold(
            3,
        ) { acc: Int, i: Int ->
            val result = acc + i
            result
        }
        val expected = 18
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `fold multiply test`() {
        val items = listOf(1, 2, 3, 4, 5)
        items.map { }
        val actual = items.fold(
            3,
        ) { acc: Int, i: Int ->
            print("acc = $acc, i = $i, ")
            val result = acc * i
            println("result = $result")
            result
        }
        val expected = 360
        assertThat(actual).isEqualTo(expected)
    }
}
