package dev.shtanko.algorithms.strings

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CompressStringTest {
    private lateinit var bruteForceApproach: CompressString

    @BeforeEach
    fun setUp() {
        bruteForceApproach = CompressStringBruteForce()
    }

    @Nested
    inner class BruteForceApproachTests {

        @Test
        fun `should compress a string with consecutive characters`() {
            assertThat(bruteForceApproach("aaabbc")).isEqualTo("a3b2c1")
            assertThat(bruteForceApproach("aabcccccaaa")).isEqualTo("a2b1c5a3")
        }

        @Test
        fun `should return same character count for unique characters`() {
            assertThat(bruteForceApproach("abcdef")).isEqualTo("a1b1c1d1e1f1")
        }

        @Test
        fun `should compress a string with single repeated character`() {
            assertThat(bruteForceApproach("aaaaaa")).isEqualTo("a6")
        }

        @Test
        fun `should return empty string for empty input`() {
            assertThat(bruteForceApproach("")).isEqualTo("")
        }

        @Test
        fun `should compress a string with mixed cases`() {
            assertThat(bruteForceApproach("aAaAAaa")).isEqualTo("a1A1a1A2a2")
        }

        @Test
        fun `should compress a string with special characters`() {
            assertThat(bruteForceApproach("$$%%%^^^^")).isEqualTo($$"$2%3^4")
        }

        @Test
        fun `should handle single character input`() {
            assertThat(bruteForceApproach("x")).isEqualTo("x1")
        }
    }
}
