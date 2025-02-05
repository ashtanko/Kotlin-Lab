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

import kotlin.math.max

/**
 * 1601. Maximum Number of Achievable Transfer Requests
 * @see <a href="https://leetcode.com/problems/maximum-number-of-achievable-transfer-requests/">Source</a>
 */
fun interface MaxRequests {
    fun maximumRequests(n: Int, requests: Array<IntArray>): Int
}

/**
 * Approach 1: Backtracking
 */
class MaxRequestsBacktracking : MaxRequests {

    private var answer = 0

    override fun maximumRequests(n: Int, requests: Array<IntArray>): Int {
        val inDegree = IntArray(n)
        maxRequest(requests, inDegree, n, 0, 0)
        return answer
    }

    private fun maxRequest(requests: Array<IntArray>, inDegree: IntArray, n: Int, index: Int, count: Int) {
        if (index == requests.size) {
            // Check if all buildings have an in-degree of 0.
            for (i in 0 until n) {
                if (inDegree[i] != 0) {
                    return
                }
            }
            answer = max(answer, count)
            return
        }

        // Consider this request, increment and decrement for the buildings involved.
        inDegree[requests[index][0]]--
        inDegree[requests[index][1]]++
        // Move on to the next request and also increment the count of requests.
        maxRequest(requests, inDegree, n, index + 1, count + 1)
        // Backtrack to the previous values to move back to the original state before the second recursion.
        inDegree[requests[index][0]]++
        inDegree[requests[index][1]]--

        // Ignore this request and move on to the next request without incrementing the count.
        maxRequest(requests, inDegree, n, index + 1, count)
    }
}

/**
 * Approach 2: Bitmasking
 */
class MaxRequestsBitmasking : MaxRequests {
    override fun maximumRequests(n: Int, requests: Array<IntArray>): Int {
        var answer = 0
        for (mask in 0 until (1 shl requests.size)) {
            val inDegree = IntArray(n)
            var pos: Int = requests.size - 1
            // Number of set bits representing the requests we will consider.
            val bitCount = Integer.bitCount(mask)

            // If the request count we're going to consider is less than the maximum request
            // We have considered without violating the constraints; then we can return it cannot be the answer.
            if (bitCount <= answer) {
                continue
            }

            // For all the 1's in the number, update the array in degree for the building it involves.
            var curr = mask
            while (curr > 0) {
                if (curr and 1 == 1) {
                    inDegree[requests[pos][0]]--
                    inDegree[requests[pos][1]]++
                }
                curr = curr shr 1
                pos--
            }
            var flag = true
            // Check if it doesn't violate the constraints
            for (i in 0 until n) {
                if (inDegree[i] != 0) {
                    flag = false
                    break
                }
            }
            if (flag) {
                answer = bitCount
            }
        }

        return answer
    }
}
