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

import java.util.Collections
import java.util.PriorityQueue

/**
 * Construct Target Array With Multiple Sums
 * @see <a href="https://leetcode.com/problems/construct-target-array-with-multiple-sums/">Source</a>
 */
fun interface IsPossible {
    operator fun invoke(target: IntArray): Boolean
}

/**
 * Approach 1: Working Backward
 * Time Complexity : O(n + k⋅log n).
 * Space Complexity : O(n).
 */
class IPWorkingBackward : IsPossible {
    override operator fun invoke(target: IntArray): Boolean {
        // Handle the n = 1 case.
        if (target.size == 1) {
            return target[0] == 1
        }

        var totalSum: Int = target.sum()

        val pq: PriorityQueue<Int> = PriorityQueue<Int>(Collections.reverseOrder())
        for (num in target) {
            pq.add(num)
        }

        while (pq.element() > 1) {
            val largest: Int = pq.remove()
            val x = largest - (totalSum - largest)
            if (x < 1) return false
            pq.add(x)
            totalSum = totalSum - largest + x
        }
        return true
    }
}

/**
 * Approach 2: Working Backward with Optimizations
 * Time Complexity : O(n+log k⋅log n).
 * Space Complexity : O(n).
 */
class IPWorkingBackwardOptmz : IsPossible {
    override operator fun invoke(target: IntArray): Boolean {
        // Handle the n = 1 case.
        if (target.size == 1) {
            return target[0] == 1
        }
        var totalSum: Int = target.sum()

        val pq: PriorityQueue<Int> = PriorityQueue<Int>(Collections.reverseOrder())
        for (num in target) {
            pq.add(num)
        }

        while (pq.element() > 1) {
            val largest: Int = pq.remove()
            val rest = totalSum - largest

            // This will only occur if n = 2.
            if (rest == 1) {
                return true
            }
            val x = largest % rest

            // If x is now 0 (invalid) or didn't
            // change, then we know this is impossible.
            if (x == 0 || x == largest) {
                return false
            }
            pq.add(x)
            totalSum = totalSum - largest + x
        }
        return true
    }
}
