/*
 * Copyright 2021 Oleksii Shtanko
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

import java.util.PriorityQueue
import kotlin.math.max

/**
 * 630. Course Schedule III
 * @see <a href="https://leetcode.com/problems/course-schedule-iii/">Source</a>
 */
fun interface ScheduleCourse3 {
    operator fun invoke(courses: Array<IntArray>): Int
}

/**
 * Approach 2: Recursion with Memoization
 */
class ScheduleCourse3Recursion : ScheduleCourse3 {
    override operator fun invoke(courses: Array<IntArray>): Int {
        courses.sortWith { a, b -> a[1] - b[1] }
        val memo = Array(courses.size) {
            arrayOfNulls<Int>(
                courses[courses.size - 1][1] + 1,
            )
        }
        return schedule(courses, 0, 0, memo)
    }

    private fun schedule(courses: Array<IntArray>, i: Int, time: Int, memo: Array<Array<Int?>>): Int {
        if (i == courses.size) return 0
        if (memo[i][time] != null) {
            return memo[i][time]!!
        }
        var taken = 0
        if (time + courses[i][0] <= courses[i][1]) taken = 1 + schedule(courses, i + 1, time + courses[i][0], memo)
        val notTaken = schedule(courses, i + 1, time, memo)
        memo[i][time] = max(taken, notTaken)
        return memo[i][time]!!
    }
}

/**
 * Approach 3: Iterative Solution
 */
class ScheduleCourse3Iterative : ScheduleCourse3 {
    override operator fun invoke(courses: Array<IntArray>): Int {
        courses.sortWith { a: IntArray, b: IntArray ->
            a[1] - b[1]
        }
        var time = 0
        var count = 0
        for (i in courses.indices) {
            if (time + courses[i][0] <= courses[i][1]) {
                time += courses[i][0]
                count++
            } else {
                var maxI = i
                for (j in 0 until i) {
                    if (courses[j][0] > courses[maxI][0]) maxI = j
                }
                if (courses[maxI][0] > courses[i][0]) {
                    time += courses[i][0] - courses[maxI][0]
                }
                courses[maxI][0] = -1
            }
        }
        return count
    }
}

/**
 * Approach 4: Optimized Iterative
 */
class ScheduleCourse3OptimizedIterative : ScheduleCourse3 {
    override operator fun invoke(courses: Array<IntArray>): Int {
        courses.sortWith { a: IntArray, b: IntArray ->
            a[1] - b[1]
        }
        var time = 0
        var count = 0
        for (i in courses.indices) {
            if (time + courses[i][0] <= courses[i][1]) {
                time += courses[i][0]
                courses[count++] = courses[i]
            } else {
                var maxI = i
                for (j in 0 until count) {
                    if (courses[j][0] > courses[maxI][0]) maxI = j
                }
                if (courses[maxI][0] > courses[i][0]) {
                    time += courses[i][0] - courses[maxI][0]
                    courses[maxI] = courses[i]
                }
            }
        }
        return count
    }
}

/**
 * Approach 5: Extra List
 */
class ScheduleCourse3ExtraList : ScheduleCourse3 {
    override operator fun invoke(courses: Array<IntArray>): Int {
        courses.sortWith { a: IntArray, b: IntArray ->
            a[1] - b[1]
        }
        val validList: MutableList<Int> = ArrayList()
        var time = 0
        for (c in courses) {
            if (time + c[0] <= c[1]) {
                validList.add(c[0])
                time += c[0]
            } else {
                var maxI = 0
                for (i in 1 until validList.size) {
                    if (validList[i] > validList[maxI]) maxI = i
                }
                if (validList.isNotEmpty() && validList[maxI] > c[0]) {
                    time += c[0] - validList[maxI]
                    validList[maxI] = c[0]
                }
            }
        }
        return validList.size
    }
}

/**
 * Approach 6: Priority Queue
 * Time complexity : O(n log n).
 * Space complexity : O(n).
 */
class ScheduleCourse3PriorityQueue : ScheduleCourse3 {
    override operator fun invoke(courses: Array<IntArray>): Int {
        courses.sortWith { a: IntArray, b: IntArray ->
            a[1] - b[1]
        }
        val queue: PriorityQueue<Int> = PriorityQueue { a, b -> b - a }
        var time = 0
        for (c in courses) {
            if (time + c[0] <= c[1]) {
                queue.offer(c[0])
                time += c[0]
            } else if (queue.isNotEmpty() && queue.peek() > c[0]) {
                time += c[0] - queue.poll()
                queue.offer(c[0])
            }
        }
        return queue.size
    }
}
