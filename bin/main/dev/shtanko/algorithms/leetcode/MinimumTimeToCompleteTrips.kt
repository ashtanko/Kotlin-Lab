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

/**
 * 2187. Minimum Time to Complete Trips
 * @see <a href="https://leetcode.com/problems/minimum-time-to-complete-trips/">Source</a>
 */
fun interface MinimumTimeToCompleteTrips {
    fun minimumTime(time: IntArray, totalTrips: Int): Long
}

class MinimumTimeToCompleteTripsBS : MinimumTimeToCompleteTrips {
    override fun minimumTime(time: IntArray, totalTrips: Int): Long {
        var ans = Long.MAX_VALUE
        var l: Long = 0
        var r = ans
        while (l <= r) {
            val mid = l + (r - l) / 2
            //  mid is the time to check if it is possible to complete totalTrips
            if (isPossible(time, totalTrips, mid)) {
                ans = mid
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        return ans
    }

    //  is it possible to complete total trips in given time
    private fun isPossible(a: IntArray, total: Int, time: Long): Boolean {
        var currTrips: Long = 0
        for (timeI in a) {
            currTrips += time / timeI
            if (currTrips >= total) {
                return true
            }
        }
        return false
    }
}

class MinimumTimeToCompleteTripsBS2 : MinimumTimeToCompleteTrips {
    override fun minimumTime(time: IntArray, totalTrips: Int): Long {
        var anstillnow: Long = -1

        var left: Long = 1
        var right = LIMIT

        while (left <= right) {
            val mid = left + (right - left) / 2 // find mid-point like this to avoid overflow
            var currTrips: Long = 0
            for (t in time) {
                currTrips += mid / t
            }
            if (currTrips >= totalTrips) {
                anstillnow = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        return anstillnow
    }

    companion object {
        private const val LIMIT = 100000000000001L
    }
}
