package dev.shtanko.algorithms.strings

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal abstract class RemoveAllWhitespaceTest {
    private lateinit var bruteForceApproach: RemoveAllWhitespace

    @BeforeEach
    fun setUp() {
        bruteForceApproach = RemoveAllWhitespaceBruteForce()
    }

    @Nested
    inner class BruteForceApproachTests {

        @Test
        fun `should remove all spaces and tabs from the string`() {
            assertThat(bruteForceApproach("hello world")).isEqualTo("helloworld")
            assertThat(bruteForceApproach("a b  c d")).isEqualTo("abcd")
            assertThat(bruteForceApproach("foo\tbar")).isEqualTo("foobar")
        }

        @Test
        fun `should return the same string if there are no spaces or tabs`() {
            assertThat(bruteForceApproach("helloworld")).isEqualTo("helloworld")
            assertThat(bruteForceApproach("12345")).isEqualTo("12345")
            assertThat(bruteForceApproach("!@#$%^&*()")).isEqualTo("!@#$%^&*()")
        }

        @Test
        fun `should return an empty string when input is only spaces or tabs`() {
            assertThat(bruteForceApproach("     ")).isEqualTo("")
            assertThat(bruteForceApproach("\t\t\t")).isEqualTo("")
            assertThat(bruteForceApproach(" \t \t ")).isEqualTo("")
        }

        @Test
        fun `should handle mixed whitespace and non-whitespace characters`() {
            assertThat(bruteForceApproach("   a  b c ")).isEqualTo("abc")
            assertThat(bruteForceApproach("\tHello \tWorld\t")).isEqualTo("HelloWorld")
        }

        @Test
        fun `should handle empty string`() {
            assertThat(bruteForceApproach("")).isEqualTo("")
        }

        @Test
        fun `should handle only one character input`() {
            assertThat(bruteForceApproach("a")).isEqualTo("a")
            assertThat(bruteForceApproach(" ")).isEqualTo("")
            assertThat(bruteForceApproach("\t")).isEqualTo("")
        }
    }
}
