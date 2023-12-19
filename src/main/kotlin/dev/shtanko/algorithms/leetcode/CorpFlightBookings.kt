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

import dev.shtanko.algorithms.extensions.second

/**
 * Corporate Flight Bookings
 */
fun corpFlightBookings(bookings: Array<IntArray>, n: Int): IntArray {
    val res = IntArray(n)
    for (b in bookings) {
        res[b.first() - 1] += b[2]
        if (b.second() < n) res[b[1]] -= b[2]
    }
    for (i in 1 until n) res[i] += res[i - 1]
    return res
}
