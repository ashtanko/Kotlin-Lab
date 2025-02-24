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

import java.util.TreeMap

class MyCalendar2 {

    private val bookingCount = TreeMap<Int, Int>()
    private val maxOverlappedBooking = 2

    fun book(start: Int, end: Int): Boolean {
        // Increase and decrease the booking count at the start and end respectively.
        bookingCount[start] = bookingCount.getOrDefault(start, 0) + 1
        bookingCount[end] = bookingCount.getOrDefault(end, 0) - 1

        var overlappedBooking = 0

        // Calculate the prefix sum of bookings.
        for (count in bookingCount.values) {
            overlappedBooking += count
            // If the number of overlaps exceeds the allowed limit
            // rollback and return false.
            if (overlappedBooking > maxOverlappedBooking) {
                // Rollback changes.
                bookingCount[start] = bookingCount[start]!! - 1
                bookingCount[end] = bookingCount[end]!! + 1

                // Remove entries if their count becomes zero to clean up the TreeMap.
                if (bookingCount[start] == 0) {
                    bookingCount.remove(start)
                }

                return false
            }
        }

        return true
    }
}
