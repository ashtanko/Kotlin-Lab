/*
 * Copyright 2024 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.level.Medium
import java.util.TreeSet

/**
 * 729. My Calendar I
 * @see <a href="https://leetcode.com/problems/my-calendar-i/">My Calendar I</a>
 */
@Medium("https://leetcode.com/problems/my-calendar-i")
class MyCalendar {
    private val calendar = TreeSet<Pair<Int, Int>>(compareBy({ it.first }, { it.second }))

    fun book(start: Int, end: Int): Boolean {
        val newEvent = start to end
        val next = calendar.ceiling(newEvent)
        val prev = calendar.floor(newEvent)

        if ((prev != null && prev.second > start) || (next != null && next.first < end)) {
            return false
        }

        calendar.add(newEvent)
        return true
    }
}
