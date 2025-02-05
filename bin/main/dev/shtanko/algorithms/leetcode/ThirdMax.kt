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

import java.util.PriorityQueue

private const val MAX = 3

/**
 * This function finds the third maximum number in a given array of integers.
 * It uses a priority queue and a set to keep track of the maximum numbers.
 * The function iterates over the array and for each number, it adds it to the set and the priority queue.
 * If the size of the priority queue exceeds 3, it removes the smallest number.
 * Finally, if the size of the priority queue is 2, it removes the smallest number again, leaving the third maximum
 * number at the top of the queue.
 * The function returns the top of the queue, which is the third maximum number.
 *
 * @param nums The array of integers to find the third maximum number in.
 * @return The third maximum number in the array.
 */
fun thirdMax(nums: IntArray): Int {
    val pq: PriorityQueue<Int> = PriorityQueue()
    val set: MutableSet<Int> = HashSet()
    for (n in nums) {
        if (set.add(n)) {
            pq.offer(n)
            if (pq.size > MAX) pq.poll()
        }
    }
    if (pq.size == 2) pq.poll()
    return pq.peek() ?: -1
}
