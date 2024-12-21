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

package dev.shtanko.algorithms.search

/**
 * Linear search is an algorithm which finds the position of a target value within an array (Usually unsorted)
 *
 * Worst-case performance       O(n)
 * Best-case performance        O(1)
 * Average performance          O(n)
 * Worst-case space complexity  O(1)
 */
class LinearSearch<T> : AbstractSearchStrategy<T> {

    override fun perform(arr: Array<T>, element: T): Int {
        for ((i, a) in arr.withIndex()) {
            if (a == element) {
                return i
            }
        }
        return -1
    }
}
