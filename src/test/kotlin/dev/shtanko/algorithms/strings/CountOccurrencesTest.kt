package dev.shtanko.algorithms.strings

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal abstract class CountOccurrencesTest {
    private lateinit var recursiveApproach: CountOccurrences

    @BeforeEach
    fun setUp() {
        recursiveApproach = CountOccurrencesRecursive()
    }

    @Nested
    inner class RecursiveApproachTests {

        @Test
        fun `should return correct count for character in string`() {
            assertThat(recursiveApproach("hello", 'l', 0, 0)).isEqualTo(2)
            assertThat(recursiveApproach("mississippi", 's', 0, 0)).isEqualTo(4)
            assertThat(recursiveApproach("banana", 'a', 0, 0)).isEqualTo(3)
        }

        @Test
        fun `should return 0 if character is not in string`() {
            assertThat(recursiveApproach("hello", 'z', 0, 0)).isEqualTo(0)
            assertThat(recursiveApproach("world", 'x', 0, 0)).isEqualTo(0)
        }

        @Test
        fun `should return 0 for empty string`() {
            assertThat(recursiveApproach("", 'a', 0, 0)).isEqualTo(0)
        }

        @Test
        fun `should count occurrences of special characters`() {
            assertThat(recursiveApproach("!@!@!@", '@', 0, 0)).isEqualTo(3)
            assertThat(recursiveApproach("123123123", '3', 0, 0)).isEqualTo(3)
        }

        @Test
        fun `should handle case sensitivity`() {
            assertThat(recursiveApproach("Hello", 'h', 0, 0)).isEqualTo(0) // 'H' vs 'h'
            assertThat(recursiveApproach("Hello", 'H', 0, 0)).isEqualTo(1)
        }

        @Test
        fun `should return correct count for single character input`() {
            assertThat(recursiveApproach("aaaa", 'a', 0, 0)).isEqualTo(4)
            assertThat(recursiveApproach("aaaa", 'b', 0, 0)).isEqualTo(0)
        }
    }
}
