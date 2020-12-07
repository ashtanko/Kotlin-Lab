package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class FindClosestPalindromeTest {

    companion object {

        @JvmStatic
        private fun provideStringData(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of("", ""),
                Arguments.of("1", "0"),
                Arguments.of("1", "0"),
                Arguments.of("10", "9"),
                Arguments.of("111", "101"),
                Arguments.of("123", "121"),
                Arguments.of("456", "454"),
                Arguments.of("1000", "999"),
                Arguments.of("1999", "2002")
            )
        }
    }

    @ParameterizedTest
    @MethodSource("provideStringData")
    internal fun `nearest palindromic test`(str: String, expected: String) {
        val actual = str.nearestPalindromic()
        assertEquals(expected, actual)
    }
}
