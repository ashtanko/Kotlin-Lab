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

interface UndergroundSystem {
    fun checkIn(id: Int, stationName: String, t: Int)

    fun checkOut(id: Int, stationName: String, t: Int)

    fun getAverageTime(startStation: String, endStation: String): Double
}

class UndergroundSystemImpl : UndergroundSystem {

    private val checkoutMap = hashMapOf<String, Pair<Int, Int>>() // Route - {TotalTime, Count}
    private val checkInMap = hashMapOf<Int, Pair<String, Int>>() // Uid - {StationName, Time}

    override fun checkIn(id: Int, stationName: String, t: Int) {
        checkInMap[id] = stationName to t
    }

    override fun checkOut(id: Int, stationName: String, t: Int) {
        val checkIn = checkInMap[id]
        val route = checkIn?.first + "_" + stationName
        val totalTime = t - checkIn?.second!!
        val checkout = checkoutMap.getOrDefault(route, 0 to 0)
        checkoutMap[route] = checkout.first + totalTime to checkout.second + 1
    }

    override fun getAverageTime(startStation: String, endStation: String): Double {
        val route = startStation + "_" + endStation
        val checkout = checkoutMap[route]
        return checkout?.first?.toDouble()?.div(checkout.second) ?: 0.0
    }
}
