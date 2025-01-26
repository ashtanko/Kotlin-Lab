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

/**
 * 1964. Find the Longest Valid Obstacle Course at Each Position
 * @see <a href="https://leetcode.com/problems/find-the-longest-valid-obstacle-course-at-each-position">
 *     Source</a>
 */
fun interface LongestObstacleCourse {
    operator fun invoke(obstacles: IntArray): IntArray
}

class LongestObstacleCourseGreedyBS : LongestObstacleCourse {
    override operator fun invoke(obstacles: IntArray): IntArray {
        val n = obstacles.size
        var lisLength = 0
        // lis[i] records the lowest increasing sequence of length i + 1.
        val answer = IntArray(n)
        val lis = IntArray(n)
        for (i in 0 until n) {
            val height = obstacles[i]

            // Find the rightmost insertion position idx.
            val idx = bisectRight(lis, height, lisLength)
            if (idx == lisLength) lisLength++
            lis[idx] = height
            answer[i] = idx + 1
        }
        return answer
    }

    var answer: List<Int>? = null

    // Find the rightmost insertion position. We use a fixed-length array and a changeable right boundary
    // to represent an arraylist of dynamic size.
    private fun bisectRight(a: IntArray, target: Int, right: Int): Int {
        var r = right
        if (r == 0) return 0
        var left = 0
        while (left < r) {
            val mid = left + (r - left) / 2
            if (a[mid] <= target) left = mid + 1 else r = mid
        }
        return left
    }
}
