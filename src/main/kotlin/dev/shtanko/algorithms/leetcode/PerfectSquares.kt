/*
 * Copyright 2022 Oleksii Shtanko
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

import dev.shtanko.algorithms.math.sqrt
import dev.shtanko.extensions.isSquare
import java.util.LinkedList
import java.util.Queue
import kotlin.math.min

/**
 * 279. Perfect Squares
 * @see <a href="https://leetcode.com/problems/perfect-squares/">Source</a>
 */
fun interface PerfectSquares {
    operator fun invoke(num: Int): Int
}

/**
 * Dynamic Programming
 */
class PerfectSquaresDP : PerfectSquares {
    override fun invoke(num: Int): Int {
        val list: MutableList<Int> = ArrayList()
        var temp = 1
        while (temp * temp <= num) {
            val square = temp * temp
            if (num == square) {
                return 1
            }
            list.add(square)
            temp += 1
        }

        val dp = IntArray(num + 1)
        dp[0] = 0
        for (i in 1 until dp.size) {
            dp[i] = Int.MAX_VALUE
            for (j in 0 until list.size) {
                if (i - list[j] >= 0 && dp[i - list[j]] != Int.MAX_VALUE && dp[i] > dp[i - list[j]] + 1) {
                    dp[i] = dp[i - list[j]] + 1
                }
            }
        }

        return if (dp[num] == Int.MAX_VALUE) -1 else dp[num]
    }
}

/**
 * Static Dynamic Programming
 */
class PerfectSquaresStaticDP : PerfectSquares {
    override fun invoke(num: Int): Int {
        val result: MutableList<Int> = ArrayList()
        if (result.isEmpty()) {
            result.add(0)
        }
        while (result.size <= num) {
            val m: Int = result.size
            var tempMin = Int.MAX_VALUE
            var j = 1
            while (j * j <= m) {
                tempMin = min(tempMin, result[m - j * j] + 1)
                j++
            }
            result.add(tempMin)
        }
        return result[num]
    }
}

/**
 * Mathematical Solution
 */
class PerfectSquaresMath : PerfectSquares {
    override fun invoke(num: Int): Int {
        var remainingNumber = num
        if (remainingNumber.isSquare()) return 1

        // The result is 4 if and only if n can be written in the
        // form of 4^k*(8*m + 7). Please refer to
        // Legendre's three-square theorem.
        while (remainingNumber and 3 == 0) {
            remainingNumber = remainingNumber shr 2
        }
        if (remainingNumber and 7 == 7) {
            return 4
        }

        // Check whether 2 is the result.
        val sqrtRemainingNumber = sqrt(remainingNumber).toInt()
        for (i in 1..sqrtRemainingNumber) {
            if ((remainingNumber - i * i).isSquare()) {
                return 2
            }
        }

        return 3
    }
}

/**
 * Breadth-First Search
 */
class PerfectSquaresBFS : PerfectSquares {
    override fun invoke(num: Int): Int {
        val queue: Queue<Int> = LinkedList()
        val visited: MutableSet<Int> = HashSet()
        queue.offer(0)
        visited.add(0)
        var depth = 0

        while (queue.isNotEmpty()) {
            depth++
            val levelSize = queue.size
            processLevel(queue, visited, levelSize, num, depth)?.let { return it }
        }
        return depth
    }

    private fun processLevel(queue: Queue<Int>, visited: MutableSet<Int>, levelSize: Int, num: Int, depth: Int): Int? {
        repeat(levelSize) {
            val current = queue.poll()
            var i = 1
            while (i * i <= num) {
                val next = current + i * i
                if (next == num) {
                    return depth
                }
                if (next > num) {
                    break
                }
                if (!visited.contains(next)) {
                    queue.offer(next)
                    visited.add(next)
                }
                i++
            }
        }
        return null
    }
}
