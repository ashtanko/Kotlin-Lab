/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.extensions.second
import kotlin.math.max

/**
 * 1288. Remove Covered Intervals
 * @see <a href="https://leetcode.com/problems/remove-covered-intervals/">Source</a>
 */
fun interface RemoveCoveredIntervals {
    operator fun invoke(intervals: Array<IntArray>): Int
}

class RemoveCoveredIntervalsSort : RemoveCoveredIntervals {
    override operator fun invoke(intervals: Array<IntArray>): Int {
        var res = 0
        var left = -1
        var right = -1
        intervals.sortWith { a, b -> a[0] - b[0] }
        for (v in intervals) {
            if (v[0] > left && v[1] > right) {
                left = v[0]
                ++res
            }
            right = max(right, v[1])
        }
        return res
    }
}

class RemoveCoveredIntervalsSortLeft : RemoveCoveredIntervals {
    override operator fun invoke(intervals: Array<IntArray>): Int {
        var res = 0
        var right = 0
        intervals.sortWith { a: IntArray, b: IntArray ->
            if (a.first() != b.first()) {
                a.first() - b.first()
            } else {
                b.second() - a.second()
            }
        }
        for (v: IntArray in intervals) {
            if (v.second() > right) {
                ++res
                right = v.second()
            }
        }
        return res
    }
}
