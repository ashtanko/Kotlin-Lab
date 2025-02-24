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

import dev.shtanko.algorithms.annotations.level.Hard
import java.util.Deque
import java.util.LinkedList

/**
 * 862. Shortest Subarray with Sum at Least K
 * @see <a href="https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/">Source</a>
 */
@Hard("https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/")
fun findShortestSubarray(arr: IntArray, target: Int): Int {
    val size = arr.size
    val prefixSum = LongArray(size + 1)
    for (i in 0 until size) prefixSum[i + 1] = prefixSum[i] + arr[i].toLong()

    var minLength = size + 1
    val deque: Deque<Int> = LinkedList() // opt(y) candidates, as indices of prefixSum
    for (currentIndex in prefixSum.indices) {
        // Want opt(currentIndex) = largest x with prefixSum[x] <= prefixSum[currentIndex] - target;
        while (deque.isNotEmpty() && prefixSum[currentIndex] <= prefixSum[deque.last]) {
            deque.removeLast()
        }
        while (deque.isNotEmpty() && prefixSum[currentIndex] >= prefixSum[deque.first] + target) {
            minLength = minLength.coerceAtMost(currentIndex - deque.removeFirst())
        }
        deque.addLast(currentIndex)
    }
    return if (minLength < size + 1) minLength else -1
}
