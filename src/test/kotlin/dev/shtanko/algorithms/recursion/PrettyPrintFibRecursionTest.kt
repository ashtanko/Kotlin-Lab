package dev.shtanko.algorithms.recursion

import dev.shtanko.utils.isContentEqualIgnoringWhitespace
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PrettyPrintFibRecursionTest {

    private lateinit var fib: PrettyPrintFib
    private lateinit var log: StringAppender

    @BeforeEach
    fun setup() {
        fib = PrettyPrintFibRecursion()
        log = StringAppenderImpl()
    }

    @Test
    fun `test fibonacci of 5`() {
        // Act
        val result = fib(5, log = log)

        // Assert the result
        assertThat(result).isEqualTo(5)

        // Assert the log output
        val expectedLog = """
            Call: fib(5)
              Call: fib(4)
                Call: fib(3)
                  Call: fib(2)
                    Call: fib(1)
                    Return: fib(1) = 1
                    Call: fib(0)
                    Return: fib(0) = 0
                    Return: fib(2) = 1
                  Call: fib(1)
                  Return: fib(1) = 1
                  Return: fib(3) = 2
                Call: fib(2)
                  Call: fib(1)
                  Return: fib(1) = 1
                  Call: fib(0)
                  Return: fib(0) = 0
                  Return: fib(2) = 1
                Return: fib(4) = 3
              Call: fib(3)
                Call: fib(2)
                  Call: fib(1)
                  Return: fib(1) = 1
                  Call: fib(0)
                  Return: fib(0) = 0
                  Return: fib(2) = 1
                Call: fib(1)
                Return: fib(1) = 1
                Return: fib(3) = 2
              Return: fib(5) = 5
        """.trimIndent()
        val actual = log.get().isContentEqualIgnoringWhitespace(expectedLog)

        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `test fibonacci of 0`() {
        // Act
        val result = fib(0, log = log)

        // Assert the result
        assertThat(result).isEqualTo(0)

        // Assert the log output
        val expectedLog = """
            Call: fib(0)
            Return: fib(0) = 0
        """.trimIndent()

        assertThat(log.get().trimIndent()).isEqualTo(expectedLog)
    }

    @Test
    fun `test fibonacci of 1`() {
        // Act
        val result = fib(1, log = log)

        // Assert the result
        assertThat(result).isEqualTo(1)

        // Assert the log output
        val expectedLog = """
            Call: fib(1)
            Return: fib(1) = 1
        """.trimIndent()

        assertThat(log.get().trimIndent()).isEqualTo(expectedLog)
    }

    @Test
    fun `test fibonacci and log clear`() {
        // Act
        fib(5, log = log)

        // Assert the log contains the expected content for fib(5)
        assertThat(log.get()).contains("Call: fib(5)")
        assertThat(log.get()).contains("Return: fib(5) = 5")

        // Clear the log
        log.clear()

        // Assert the log is empty after clearing
        assertThat(log.get().trimIndent()).isEmpty()
    }
}

internal class PrettyPrintFibOptimizedTest {

    private lateinit var fib: PrettyPrintFibOptimized
    private lateinit var log: StringAppenderImpl

    @BeforeEach
    fun setUp() {
        fib = PrettyPrintFibOptimized()
        log = StringAppenderImpl()
    }

    @Test
    fun `should calculate fibonacci correctly and log recursive calls`() {
        // Call the Fibonacci function with a specific value
        val result = fib.invoke(5, 0, log)

        // Assert the Fibonacci result
        assertThat(result).isEqualTo(5)

        // Assert the log content (ignoring spaces and indentation)
        val expectedLog = """
            Call: fib(5)
              Call: fib(4)
                Call: fib(3)
                  Call: fib(2)
                    Call: fib(1)
                    Return: fib(1) = 1
                    Call: fib(0)
                    Return: fib(0) = 0
                  Return: fib(2) = 1
                  Call: fib(1)
                  Return: fib(1) = 1
                Return: fib(3) = 2
                Call: fib(2)
                Return: fib(2) = 1
              Return: fib(4) = 3
              Call: fib(3)
              Return: fib(3) = 2
            Return: fib(5) = 5
        """.trimIndent()

        assertThat(log.get().isContentEqualIgnoringWhitespace(expectedLog)).isTrue()
    }

    @Test
    fun `should handle base case correctly for n=0`() {
        val result = fib.invoke(0, 0, log)

        // Assert the Fibonacci result
        assertThat(result).isEqualTo(0)

        // Assert the log content
        val expectedLog = """
            Call: fib(0)
            Return: fib(0) = 0
        """.trimIndent()

        assertThat(log.get().isContentEqualIgnoringWhitespace(expectedLog)).isTrue()
    }

    @Test
    fun `should handle base case correctly for n=1`() {
        val result = fib.invoke(1, 0, log)

        // Assert the Fibonacci result
        assertThat(result).isEqualTo(1)

        // Assert the log content
        val expectedLog = """
            Call: fib(1)
            Return: fib(1) = 1
        """.trimIndent()

        assertThat(log.get().isContentEqualIgnoringWhitespace(expectedLog)).isTrue()
    }

    @Test
    fun `should return cached result for previously calculated fibonacci`() {
        // First call to calculate fib(5)
        fib.invoke(5, 0, log)

        // Reset log before second call
        log.clear()

        // Second call to fib(5), should hit the cache and be faster
        val result = fib.invoke(5, 0, log)

        // Assert the Fibonacci result
        assertThat(result).isEqualTo(5)

        // Assert that no redundant calls to fib(4) and lower are logged
        val expectedLog = """
            Call: fib(5)
            Return: fib(5) = 5
        """.trimIndent()

        assertThat(log.get().isContentEqualIgnoringWhitespace(expectedLog)).isTrue()
    }
}

internal class PrettyPrintFibTailRecTest {

    private lateinit var fib: PrettyPrintFibTailRec
    private lateinit var log: StringAppenderImpl

    @BeforeEach
    fun setUp() {
        fib = PrettyPrintFibTailRec()
        log = StringAppenderImpl()
    }

    @Test
    fun `should calculate fibonacci correctly and log recursive calls`() {
        val result = fib.invoke(5, 0, log)

        // Assert the Fibonacci result
        assertThat(result).isEqualTo(5)

        // Assert the log content (ignoring spaces and indentation)
        val expectedLog = """
            Call: fib(5, prev=0, current=1)
              Call: fib(4, prev=1, current=1)
                Call: fib(3, prev=1, current=2)
                  Call: fib(2, prev=2, current=3)
                    Call: fib(1, prev=3, current=5)
                      Call: fib(0, prev=5, current=8)
                      Return: fib(0) = 5
        """.trimIndent()

        println(log.get())

        assertThat(log.get().isContentEqualIgnoringWhitespace(expectedLog)).isTrue()
    }

    @Test
    fun `should handle base case correctly for n=0`() {
        val result = fib.invoke(0, 0, log)

        // Assert the Fibonacci result
        assertThat(result).isEqualTo(0)

        // Assert the log content
        val expectedLog = """
            Call: fib(0, prev=0, current=1)
            Return: fib(0) = 0
        """.trimIndent()

        assertThat(log.get().isContentEqualIgnoringWhitespace(expectedLog)).isTrue()
    }

    @Test
    fun `should handle base case correctly for n=1`() {
        val result = fib.invoke(1, 0, log)

        // Assert the Fibonacci result
        assertThat(result).isEqualTo(1)

        // Assert the log content
        val expectedLog = """
            Call: fib(1, prev=0, current=1)
              Call: fib(0, prev=1, current=1)
              Return: fib(0) = 1
        """.trimIndent()

        assertThat(log.get().isContentEqualIgnoringWhitespace(expectedLog)).isTrue()
    }
}
