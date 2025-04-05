package dev.shtanko.mockk

import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Spies allow you to mix mocks and real objects.
 */
class SpyTest {

    @Test
    fun `test spy example`() {
        val calculator = spyk(Calculator())

        // Call the real method
        assertEquals(5, calculator.add(2, 3))

        // Stub multiply() method
        every { calculator.multiply(2, 3) } returns 100

        // multiply() will return the stubbed value, but add() remains real
        assertEquals(100, calculator.multiply(2, 3))
        assertEquals(5, calculator.add(2, 3))

        // Verify method calls
        verify { calculator.add(2, 3) }
        verify { calculator.multiply(2, 3) }
    }

    private class Calculator {
        fun add(a: Int, b: Int): Int {
            return a + b
        }

        fun multiply(a: Int, b: Int): Int {
            return a * b
        }
    }
}
