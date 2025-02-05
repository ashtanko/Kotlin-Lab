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

import java.util.Deque
import java.util.LinkedList

/**
 * Approach 1: Greedy
 * Time Complexity: O(N log N), where N is the length of a and b.
 * Space Complexity: O(N).
 */
fun advantageCount(a: IntArray, b: IntArray): IntArray {
    val sortedA: IntArray = a.clone(); sortedA.sort()
    val sortedB: IntArray = b.clone(); sortedB.sort()

    val assigned: MutableMap<Int, Deque<Int>> = HashMap()
    for (bb in b) assigned[bb] = LinkedList()

    val remaining: Deque<Int> = LinkedList()

    var j = 0
    for (aa in sortedA) {
        if (aa > sortedB[j]) {
            assigned[sortedB[j++]]?.add(aa)
        } else {
            remaining.add(aa)
        }
    }

    val ans = IntArray(b.size)
    for (i in b.indices) {
        val bDeque = assigned.getOrDefault(b[i], LinkedList())
        if (bDeque.isNotEmpty()) {
            ans[i] = bDeque.pop()
        } else {
            ans[i] = remaining.pop()
        }
    }
    return ans
}
