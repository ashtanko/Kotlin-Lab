package dev.shtanko.algorithms.leetcode

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class ClosestDivisorsTest {

    companion object {
        @JvmStatic
        fun dataProvider(): List<Pair<Int, IntArray>> {
            return listOf(
                8 to intArrayOf(3, 3),
                123 to intArrayOf(5, 25),
                999 to intArrayOf(25, 40)
            )
        }
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun `closest divisor test`(testCase: Pair<Int, IntArray>) {
        val num = testCase.first
        val expected = testCase.second
        val actual = closestDivisors(num)
        assertArrayEquals(expected, actual)
    }
}
