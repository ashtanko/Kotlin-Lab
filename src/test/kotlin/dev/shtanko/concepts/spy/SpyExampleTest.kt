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

package dev.shtanko.concepts.spy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify

class SpyExampleTest {
    class Calculator {
        fun add(a: Int, b: Int): Int = a + b
        fun multiply(a: Int, b: Int): Int = a * b
    }

    @Test
    fun `spy example with real object behavior`() {
        // Create a real object
        val realCalculator = Calculator()

        // Create a spy wrapping the real object
        val spyCalculator = spy(realCalculator)

        // Stub a method to override its behavior
        `when`(spyCalculator.add(2, 3)).thenReturn(100)

        // Call the stubbed method
        val resultAdd = spyCalculator.add(2, 3)

        // Verify that the stubbed method was called
        verify(spyCalculator).add(2, 3)

        // Call a real method
        val resultMultiply = spyCalculator.multiply(4, 5)

        // Assert the results
        assertEquals(100, resultAdd) // Stubbed behavior
        assertEquals(20, resultMultiply) // Real method behavior
    }
}
