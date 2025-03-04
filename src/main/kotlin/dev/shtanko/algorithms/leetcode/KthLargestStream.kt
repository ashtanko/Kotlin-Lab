/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import java.util.PriorityQueue

/**
 * 703. Kth Largest Element in a Stream
 */
fun interface KthLargestStream {
    fun add(n: Int): Int
}

class KthLargestStreamHeap(val k: Int, nums: IntArray) : KthLargestStream {
    private val heap = PriorityQueue<Int>(k)

    init {
        nums.forEach {
            add(it)
        }
    }

    override fun add(n: Int): Int {
        heap.offer(n)
        if (heap.size > k) heap.poll()
        return heap.peek()
    }
}
