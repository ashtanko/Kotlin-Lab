package dev.shtanko.algorithms.strings

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CheckAnagramsTest {
    private lateinit var sortApproach: CheckAnagrams
    private lateinit var bruteForceApproach: CheckAnagrams

    @BeforeEach
    fun setUp() {
        sortApproach = CheckAnagramsSort()
        bruteForceApproach = CheckAnagramsBruteForce()
    }

    @Nested
    inner class SortApproachTests {
        @Test
        fun `should return true for anagrams`() {
            assertThat(sortApproach("listen", "silent")).isTrue()
            assertThat(sortApproach("Triangle", "Integral")).isTrue()
            assertThat(sortApproach("evil", "vile")).isTrue()
        }

        @Test
        fun `should return false for non-anagrams`() {
            assertThat(sortApproach("hello", "world")).isFalse()
            assertThat(sortApproach("test", "tset1")).isFalse()
        }

        @Test
        fun `should return true for identical words`() {
            assertThat(sortApproach("same", "same")).isTrue()
        }

        @Test
        fun `should return false for different lengths`() {
            assertThat(sortApproach("abc", "ab")).isFalse()
        }

        @Test
        fun `should return true for empty strings`() {
            assertThat(sortApproach("", "")).isTrue()
        }
    }

    @Nested
    inner class BruteForceApproachTests {
        @Test
        fun `should return true for anagrams`() {
            assertThat(bruteForceApproach("listen", "silent")).isTrue()
            assertThat(bruteForceApproach("Triangle", "Integral")).isTrue()
            assertThat(bruteForceApproach("evil", "vile")).isTrue()
        }

        @Test
        fun `should return false for non-anagrams`() {
            assertThat(bruteForceApproach("hello", "world")).isFalse()
            assertThat(bruteForceApproach("test", "tset1")).isFalse()
        }

        @Test
        fun `should return true for identical words`() {
            assertThat(bruteForceApproach("same", "same")).isTrue()
        }

        @Test
        fun `should return false for different lengths`() {
            assertThat(bruteForceApproach("abc", "ab")).isFalse()
        }

        @Test
        fun `should return true for empty strings`() {
            assertThat(bruteForceApproach("", "")).isTrue()
        }
    }
}
