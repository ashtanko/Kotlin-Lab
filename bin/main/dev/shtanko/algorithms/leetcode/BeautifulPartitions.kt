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

import dev.shtanko.algorithms.MOD
import dev.shtanko.algorithms.annotations.DP
import dev.shtanko.algorithms.annotations.level.Hard
import kotlin.math.max

/**
 * 2478. Number of Beautiful Partitions
 * @see <a href="https://leetcode.com/problems/number-of-beautiful-partitions">Source</a>
 */
@Hard("https://leetcode.com/problems/number-of-beautiful-partitions")
fun interface BeautifulPartitions {
    operator fun invoke(input: String, partitions: Int, minLength: Int): Int
}

@DP
class BeautifulPartitionsDP : BeautifulPartitions {
    override operator fun invoke(input: String, partitions: Int, minLength: Int): Int {
        val charArray: CharArray = input.toCharArray()
        val length = charArray.size
        // make sure the input is valid
        if (!isPrime(charArray[0]) || isPrime(charArray[length - 1])) return 0
        val dp = Array(partitions) { IntArray(length) }
        // base case. If partitions = 1, the only thing required is to check if a character is prime
        var i: Int = length - minLength
        while (0 <= i) {
            dp[0][i] = if (isPrime(charArray[i])) 1 else 0
            --i
        }
        for (partitionIndex in 1 until partitions) {
            // re-use dp[partitions - 1][] and compute the `prefix sum` backwards
            // sum is the number of valid end points
            run {
                var j: Int = length - partitionIndex * minLength
                var sum = 0
                while (0 <= j) {
                    // if dp[][] is 0, store the sum. otherwise, it could be a possible valid end point.
                    if (0 == dp[partitionIndex - 1][j]) {
                        dp[partitionIndex - 1][j] = sum
                    } else if (0 != j && 0 == dp[partitionIndex - 1][j - 1]) {
                        sum = (sum + dp[partitionIndex - 1][j]) % MOD
                    }
                    --j
                }
            }
            // use 2 pointers [j, p] to find a valid substring
            var j = 0
            var p: Int = minLength - 1
            while (j + minLength * partitionIndex < length) {
                if (!isPrime(charArray[j])) {
                    ++j
                    continue
                }
                p = max(p, j + minLength - 1)
                while (isPrime(charArray[p])) p++ // boundary check is skipped as the last character is not prime
                if (0 == dp[partitionIndex - 1][p]) break // early break because there's no valid end points
                dp[partitionIndex][j] = dp[partitionIndex - 1][p]
                ++j
            }
        }
        return dp[partitions - 1][0]
    }

    private fun isPrime(c: Char): Boolean {
        return c == '2' || c == '3' || c == '5' || c == '7'
    }
}
