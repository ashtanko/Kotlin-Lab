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
 * 2551. Put Marbles in Bags
 * @see <a href="https://leetcode.com/problems/put-marbles-in-bags/">Source</a>
 */
fun interface PutMarbles {
    fun putMarblesInBags(weights: IntArray, k: Int): Long
}

class PutMarblesSort : PutMarbles {
    override fun putMarblesInBags(weights: IntArray, k: Int): Long {
        // We collect and sort the value of all n - 1 pairs.
        val n: Int = weights.size
        val pairWeights = IntArray(n - 1)
        for (i in 0 until n - 1) {
            pairWeights[i] = weights[i] + weights[i + 1]
        }
        // We will sort only the first (n - 1) elements of the array.
        pairWeights.sort(0, n - 1)

        // Get the difference between the largest k - 1 values and the
        // smallest k - 1 values.
        var answer = 0L
        for (i in 0 until k - 1) {
            answer += (pairWeights[n - 2 - i] - pairWeights[i]).toLong()
        }

        return answer
    }
}
