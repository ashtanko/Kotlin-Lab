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

package dev.shtanko.algorithms.sorts

import dev.shtanko.extensions.swap

/**
 * Implementation of Shell Sort
 * @see <a href="https://en.wikipedia.org/wiki/Shellsort">leetcode page</a>
 * Best complexity: O(n log n)
 * Average complexity: O(n^4/3)
 * Worst complexity: O(n^4/3)
 * Space Complexity: O(1)
 * Stable: No
 */
class ShellSort : AbstractSortStrategy {

    override fun <T : Comparable<T>> perform(arr: Array<T>) {
        val n = arr.size
        var h = 1
        while (h < n / GAP) {
            h = h * GAP + 1
        }

        while (h >= 1) {
            for (i in h until n) {
                for (j in i downTo h step h) {
                    if (arr[j - h] < arr[j]) break
                    arr.swap(j, j - h)
                }
            }
            h /= GAP
        }
    }

    companion object {
        const val GAP = 3
    }
}
