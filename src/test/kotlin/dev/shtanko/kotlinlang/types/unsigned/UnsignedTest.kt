package dev.shtanko.kotlinlang.types.unsigned

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class UnsignedTest {

    @Nested
    inner class UnsignedBitwiseAndTest {
        @Test
        fun `unsignedBitwiseAnd with default value`() {
            val result = unsignedBitwiseAnd()
            assertEquals(0b10100000u.toUByte(), result)
        }

        @Test
        fun `unsignedBitwiseAnd with all ones`() {
            val result = unsignedBitwiseAnd(0b11111111u)
            assertEquals(0b11110000u.toUByte(), result)
        }

        @Test
        fun `unsignedBitwiseAnd with all zeros`() {
            val result = unsignedBitwiseAnd(0b00000000u)
            assertEquals(0b00000000u.toUByte(), result)
        }

        @Test
        fun `unsignedBitwiseAnd with mixed bits`() {
            val result = unsignedBitwiseAnd(0b01010101u)
            assertEquals(0b01010000u.toUByte(), result)
        }

        @Test
        fun `unsignedBitwiseAnd with different mixed bits`() {
            val result = unsignedBitwiseAnd(0b10011001u)
            assertEquals(0b10010000u.toUByte(), result)
        }
    }
}
