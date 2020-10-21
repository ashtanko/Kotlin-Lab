package dev.shtanko.algorithms.leetcode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class SearchInsertPosition {

    companion object {
        @JvmStatic
        fun dataProvider(): List<Pair<Pair<IntArray, Int>, Int>> {
            return listOf(
                (intArrayOf() to 1) to 0,
                (intArrayOf(1, 3, 5, 6) to 5) to 2,
                (intArrayOf(1, 3, 5, 6) to 2) to 1,
                (intArrayOf(1, 3, 5, 6) to 7) to 4,
                (intArrayOf(1, 3, 5, 6) to 0) to 0
            )
        }
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun `search insert position test`(testCase: Pair<Pair<IntArray, Int>, Int>) {
        val arr = testCase.first.first
        val target = testCase.first.second
        val expected = testCase.second
        val actual = arr.searchInsertPosition(target)
        assertEquals(expected, actual)
    }
}
