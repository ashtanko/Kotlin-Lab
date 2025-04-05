package dev.shtanko.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StringExtensionsTest {

    @Test
    fun `should return true when strings are equal ignoring spaces and newlines`() {
        // Given
        val actual = """
            Call: fib(0)
            Return: fib(0) = 0
        """.trimIndent()

        val expected = """
            Call: fib(0)
            Return: fib(0) = 0
        """.trimIndent()

        // When
        val result = actual.isContentEqualIgnoringWhitespace(expected)

        // Then
        assertThat(result).isTrue()
    }

    @Test
    fun `should return true when strings are equal with different amounts of spaces`() {
        // Given
        val actual = "Call:  fib(0)   Return: fib(0) =   0"
        val expected = "Call: fib(0) Return: fib(0) = 0"

        // When
        val result = actual.isContentEqualIgnoringWhitespace(expected)

        // Then
        assertThat(result).isTrue()
    }

    @Test
    fun `should return true when strings have extra newlines between them`() {
        // Given
        val actual = """
            Call: fib(0)

            Return: fib(0) = 0
        """.trimIndent()

        val expected = """
            Call: fib(0)
            Return: fib(0) = 0
        """.trimIndent()

        // When
        val result = actual.isContentEqualIgnoringWhitespace(expected)

        // Then
        assertThat(result).isTrue()
    }

    @Test
    fun `should return false when strings have different content`() {
        // Given
        val actual = """
            Call: fib(0)
            Return: fib(0) = 1
        """.trimIndent()

        val expected = """
            Call: fib(0)
            Return: fib(0) = 0
        """.trimIndent()

        // When
        val result = actual.isContentEqualIgnoringWhitespace(expected)

        // Then
        assertThat(result).isFalse()
    }

    @Test
    fun `should return false when strings have different content even with same formatting`() {
        // Given
        val actual = "Call: fib(0) Return: fib(0) = 1"
        val expected = "Call: fib(0) Return: fib(0) = 0"

        // When
        val result = actual.isContentEqualIgnoringWhitespace(expected)

        // Then
        assertThat(result).isFalse()
    }

    @Test
    fun `should return true for empty strings`() {
        // Given
        val actual = ""
        val expected = ""

        // When
        val result = actual.isContentEqualIgnoringWhitespace(expected)

        // Then
        assertThat(result).isTrue()
    }

    @Test
    fun `should return false when one string is empty and the other is not`() {
        // Given
        val actual = "Some content"
        val expected = ""

        // When
        val result = actual.isContentEqualIgnoringWhitespace(expected)

        // Then
        assertThat(result).isFalse()
    }
}
