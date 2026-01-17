/*
 * Designed and developed by 2025 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.junit

import dev.shtanko.junit.TestData.User
import kotlin.test.junit.JUnitAsserter.assertNotSame
import kotlin.test.junit.JUnitAsserter.assertSame
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class AssertionsTest {

    @Test
    fun `example of assertEquals(expected, actual)`() {
        val actual = 2 + 2
        val expected = 4
        assertEquals(expected, actual, "2 + 2 should be 4")
    }

    @Test
    fun `example of assertNotEquals(unexpected, actual)`() {
        val actual = 2 + 2
        val unexpected = 5
        assertNotEquals(unexpected, actual, "2 + 2 should not be 5")
    }

    @Test
    fun `example of assertTrue(condition)`() {
        val condition = true
        assertTrue(condition, "condition should be true")
    }

    @Test
    fun `example of assertFalse(condition)`() {
        val condition = false
        assertFalse(condition, "condition should be false")
    }

    @Test
    fun `example of assertTrue with lazy message`() {
        val condition = true
        assertTrue(condition) { "condition should be true" }
    }

    @Test
    fun `example of assertNull(object)`() {
        val obj: Any? = null
        assertNull(obj, "object should be null")
    }

    @Test
    fun `example of assertNotNull(object)`() {
        val obj: Any = "not null"
        assertNotNull(obj, "object should not be null")
    }

    // Asserts that two object references point to the same object in memory
    @Test
    fun `example of assertSame(expected, actual)`() {
        val arr = intArrayOf(1)
        val actual = arr.first()
        val expected = arr.first()
        assertSame("expected and actual should be the same objects in memory", expected, actual)
    }

    @Test
    fun `example of assertNotSame(message, unexpected, actual)`() {
        val users = listOf(
            User(1, "Alice"),
            User(2, "Bob"),
            User(3, "Charlie"),
        )

        for (i in 0 until users.size - 1) {
            for (j in i + 1 until users.size) {
                assertNotSame("unexpected and actual should not be the same objects in memory", users[i], users[j])
            }
        }
    }

    @Test
    fun `example of assertArrayEquals(expected, actual)`() {
        val arr1 = intArrayOf(1, 2, 3)
        val arr2 = intArrayOf(1, 2, 3)
        assertArrayEquals(arr1, arr2, "arrays should be equal")
    }

    @Test
    fun `example of assertThrows(exceptionClass, executable)`() {
        val exception = assertThrows<IllegalArgumentException>({ "Should throw an Exception" }) {
            throw IllegalArgumentException("Talk to a duck")
        }
        assertEquals("Talk to a duck", exception.message)
    }
}
