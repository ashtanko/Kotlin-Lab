package dev.shtanko.algorithms.leetcode

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class FinalPricesTest {

    companion object {

        @JvmStatic
        private fun provideNumbers(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(intArrayOf(8, 4, 6, 2, 3), intArrayOf(4, 2, 4, 2, 3)),
            )
        }
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    fun `final prices test`(arr: IntArray, expected: IntArray) {
        val actual = arr.finalPrices()
        assertArrayEquals(expected, actual)
    }
}
