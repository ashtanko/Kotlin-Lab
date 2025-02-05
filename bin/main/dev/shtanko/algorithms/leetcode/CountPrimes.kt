/*
 * Copyright 2020 Oleksii Shtanko
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

fun interface CountPrimesStrategy {
    operator fun invoke(n: Int): Int
}

class CountPrimesBrutForce : CountPrimesStrategy {
    override operator fun invoke(n: Int): Int {
        val notPrime = BooleanArray(n)
        var count = 0
        for (i in 2 until n) {
            if (!notPrime[i]) {
                count++
                var j = 2
                while (i * j < n) {
                    notPrime[i * j] = true
                    j++
                }
            }
        }
        return count
    }
}

class CountPrimesTimeComplexity : CountPrimesStrategy {
    override operator fun invoke(n: Int): Int {
        if (n < 2) return 0
        val nonPrime = BooleanArray(n)
        nonPrime[1] = true
        var numNonPrimes = 1
        for (i in 2 until n) { // Complexity: O(n)
            if (nonPrime[i]) continue
            var j = i * 2
            // Complexity: O(log(log(n)))
            while (j < n) {
                if (!nonPrime[j]) {
                    nonPrime[j] = true
                    numNonPrimes++
                }
                j += i
            }
        }
        return n - 1 - numNonPrimes
    }
}
