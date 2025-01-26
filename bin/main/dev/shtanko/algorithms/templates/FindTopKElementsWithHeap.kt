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

package dev.shtanko.algorithms.templates

import java.util.PriorityQueue

private fun fn(arr: IntArray, k: Int): IntArray {
    val criteria = 0
    val heap: PriorityQueue<Int> = PriorityQueue(criteria)
    for (num in arr) {
        heap.add(num)
        if (heap.size > k) {
            heap.remove()
        }
    }
    val ans = IntArray(k)
    for (i in 0 until k) {
        ans[i] = heap.remove()
    }
    return ans
}
