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

package dev.shtanko.github.data.model

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OrderTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `serialize Order ASC`() {
        val order = Order.ASC
        val result = json.encodeToString(Order.serializer(), order)
        assertEquals("\"asc\"", result)
    }

    @Test
    fun `serialize Order DESC`() {
        val order = Order.DESC
        val result = json.encodeToString(Order.serializer(), order)
        assertEquals("\"desc\"", result)
    }

    @Test
    fun `deserialize Order ASC`() {
        val jsonData = "\"asc\""
        val result = json.decodeFromString<Order>(jsonData)
        assertEquals(Order.ASC, result)
    }

    @Test
    fun `deserialize Order DESC`() {
        val jsonData = "\"desc\""
        val result = json.decodeFromString<Order>(jsonData)
        assertEquals(Order.DESC, result)
    }

    @Test
    fun `deserialize invalid Order throws exception`() {
        val jsonData = "\"invalid\""
        val exception = assertThrows<SerializationException>({ "Should throw an Exception" }) {
            json.decodeFromString<Order>(jsonData)
        }
        assertTrue(exception.message?.contains("does not contain element with name 'invalid'") == true)
    }
}
