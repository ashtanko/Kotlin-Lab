/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.kotlinlang.types.nothing

import java.lang.IllegalArgumentException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class Example1Test {
    @Test
    fun describeNumber_returnsOneFor1() {
        assertEquals("One", describeNumber(1))
    }

    @Test
    fun describeNumber_returnsTwoFor2() {
        assertEquals("Two", describeNumber(2))
    }

    @Test
    fun describeNumber_throwsIllegalArgumentExceptionForInvalidNumber() {
        assertThrows(IllegalArgumentException::class.java) {
            describeNumber(3)
        }
    }

    @Test
    fun fail_throwsIllegalArgumentExceptionWithMessage() {
        val message = "Test exception"
        val exception = assertThrows(IllegalArgumentException::class.java) {
            fail(message)
        }
        assertEquals(message, exception.message)
    }
}
