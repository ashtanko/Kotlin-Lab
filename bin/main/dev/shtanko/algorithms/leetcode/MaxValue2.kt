/*
 * Copyright 2023 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import java.util.Arrays
import kotlin.math.max

/**
 * 1751. Maximum Number of Events That Can Be Attended II
 * @see <a href="https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/">Source</a>
 */
fun interface MaxValue2 {
    operator fun invoke(events: Array<IntArray>, k: Int): Int
}

/**
 * Approach 1: Top-down Dynamic Programming + Binary Search
 */
class MaxValue2TopDown : MaxValue2 {
    private lateinit var dp: Array<IntArray>

    override operator fun invoke(events: Array<IntArray>, k: Int): Int {
        events.sortWith { a: IntArray, b: IntArray -> a[0] - b[0] }

        val n = events.size
        dp = Array(k + 1) { IntArray(n) }
        for (row in dp) {
            Arrays.fill(row, -1)
        }
        return dfs(0, k, events)
    }

    private fun dfs(curIndex: Int, count: Int, events: Array<IntArray>): Int {
        if (count == 0 || curIndex == events.size) {
            return 0
        }
        if (dp[count][curIndex] != -1) {
            return dp[count][curIndex]
        }
        val nextIndex = bisectRight(events, events[curIndex][1])
        dp[count][curIndex] = max(
            dfs(
                curIndex + 1,
                count,
                events,
            ),
            events[curIndex][2] + dfs(nextIndex, count - 1, events),
        )
        return dp[count][curIndex]
    }
}

/**
 * Approach 2: Bottom-up Dynamic Programming + Binary Search
 */
class MaxValue2BottomUp : MaxValue2 {
    override operator fun invoke(events: Array<IntArray>, k: Int): Int {
        val n: Int = events.size
        val dp = Array(k + 1) { IntArray(n + 1) }
        events.sortWith { a: IntArray, b: IntArray -> a[0] - b[0] }

        for (curIndex in n - 1 downTo 0) {
            for (count in 1..k) {
                val nextIndex = bisectRight(events, events[curIndex][1])
                dp[count][curIndex] = max(dp[count][curIndex + 1], events[curIndex][2] + dp[count - 1][nextIndex])
            }
        }
        return dp[k][0]
    }
}

/**
 * Approach 3: Top-down Dynamic Programming + Cached Binary Search
 */
class MaxValue2TopDownBS : MaxValue2 {
    private lateinit var dp: Array<IntArray>
    private lateinit var nextIndices: IntArray

    override operator fun invoke(events: Array<IntArray>, k: Int): Int {
        events.sortWith { a: IntArray, b: IntArray -> a[0] - b[0] }
        val n = events.size
        nextIndices = IntArray(n)
        for (curIndex in 0 until n) {
            nextIndices[curIndex] = bisectRight(events, events[curIndex][1])
        }
        dp = Array(k + 1) { IntArray(n) }
        for (row in dp) {
            Arrays.fill(row, -1)
        }
        return dfs(0, k, events)
    }

    private fun dfs(curIndex: Int, count: Int, events: Array<IntArray>): Int {
        if (count == 0 || curIndex == events.size) {
            return 0
        }
        if (dp[count][curIndex] != -1) {
            return dp[count][curIndex]
        }
        val nextIndex = nextIndices[curIndex]
        dp[count][curIndex] = max(
            dfs(curIndex + 1, count, events),
            events[curIndex][2] + dfs(nextIndex, count - 1, events),
        )
        return dp[count][curIndex]
    }
}

/**
 * Approach 5: Top-down Dynamic Programming Without Binary Search
 */
class MaxValue2SimpleTopDown : MaxValue2 {
    lateinit var dp: Array<IntArray>

    override operator fun invoke(events: Array<IntArray>, k: Int): Int {
        Arrays.sort(
            events,
        ) { a: IntArray, b: IntArray -> a[0] - b[0] }
        val n = events.size
        dp = Array(k + 1) { IntArray(n) }
        for (row in dp) {
            Arrays.fill(row, -1)
        }
        return dfs(0, 0, -1, events, k)
    }

    private fun dfs(curIndex: Int, count: Int, prevEndingTime: Int, events: Array<IntArray>, k: Int): Int {
        if (curIndex == events.size || count == k) {
            return 0
        }
        if (prevEndingTime >= events[curIndex][0]) {
            return dfs(curIndex + 1, count, prevEndingTime, events, k)
        }
        if (dp[count][curIndex] != -1) {
            return dp[count][curIndex]
        }
        val ans = max(
            dfs(curIndex + 1, count, prevEndingTime, events, k),
            dfs(curIndex + 1, count + 1, events[curIndex][1], events, k) + events[curIndex][2],
        )
        dp[count][curIndex] = ans
        return ans
    }
}

fun bisectRight(events: Array<IntArray>, target: Int): Int {
    var left = 0
    var right = events.size
    while (left < right) {
        val mid = (left + right) / 2
        if (events[mid][0] <= target) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    return left
}
