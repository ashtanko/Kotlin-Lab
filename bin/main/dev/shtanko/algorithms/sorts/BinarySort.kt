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

package dev.shtanko.algorithms.sorts

class BinarySort : AbstractSortStrategy {
    override fun <T : Comparable<T>> perform(arr: Array<T>) {
        val lo = 0
        val hi = arr.size
        val initRunLen = countRunAndMakeAscending(arr, lo, hi)
        helper(arr, lo, hi, lo + initRunLen)
    }

    private fun <T : Comparable<T>> helper(a: Array<T>, lo: Int, hi: Int, start: Int) {
        var start0 = start
        assert(start0 in lo..hi)
        if (start0 == lo) start0++
        while (start0 < hi) {
            val pivot = a[start0]

            // Set left (and right) to the index where a[start] (pivot) belongs
            var left = lo
            var right = start0
            assert(left <= right)
            /*
             * Invariants:
             *   pivot >= all in [lo, left).
             *   pivot <  all in [right, start).
             */
            while (left < right) {
                val mid = left + right ushr 1
                if (pivot < a[mid]) right = mid else left = mid + 1
            }
            assert(left == right)

            /*
             * The invariants still hold: pivot >= all in [lo, left) and
             * pivot < all in [left, start), so pivot belongs at left.  Note
             * that if there are elements equal to pivot, left points to the
             * first slot after them -- that's why this sort is stable.
             * Slide elements over to make room for pivot.
             */
            when (val n = start0 - left) { // The number of elements to move
                2 -> {
                    a[left + 2] = a[left + 1]
                    a[left + 1] = a[left]
                }

                1 -> a[left + 1] = a[left]
                else -> System.arraycopy(a, left, a, left + 1, n)
            }
            a[left] = pivot
            start0++
        }
    }

    private fun <T : Comparable<T>> countRunAndMakeAscending(a: Array<T>, lo: Int, hi: Int): Int {
        if (a.isEmpty()) return 0
        var runHi = lo + 1
        if (runHi == hi) return 1

        // Find end of run, and reverse range if descending
        if (a[runHi++] < a[lo]) { // Descending
            while (runHi < hi && a[runHi] < a[runHi - 1]) {
                runHi++
            }
            reverseRange(a, lo, runHi)
        } else { // Ascending
            while (runHi < hi && a[runHi] >= a[runHi - 1]) {
                runHi++
            }
        }
        return runHi - lo
    }

    private fun <T : Comparable<T>> reverseRange(a: Array<T>, lo: Int, hi: Int) {
        var lo0 = lo
        var hi0 = hi
        hi0--
        while (lo0 < hi0) {
            val t = a[lo0]
            a[lo0++] = a[hi0]
            a[hi0--] = t
        }
    }
}
