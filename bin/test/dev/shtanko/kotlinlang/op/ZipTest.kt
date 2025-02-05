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

package dev.shtanko.kotlinlang.op

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ZipTest {

    @Test
    internal fun `zip names and ages test`() {
        val names = listOf("Sara", "Jake", "Nick", "John")
        val ages = listOf(4, 20, 15, 22)
        val expected = listOf(
            "Sara" to 4,
            "Jake" to 20,
            "Nick" to 15,
            "John" to 22,
        )
        val actual = names.zip(ages)
        assertEquals(expected, actual)
    }
}
