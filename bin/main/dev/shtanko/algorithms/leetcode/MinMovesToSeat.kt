/*
 * Copyright 2022 Oleksii Shtanko
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

import kotlin.math.abs

/**
 * 2037. Minimum Number of Moves to Seat Everyone
 * @see <a href="https://leetcode.com/problems/minimum-number-of-moves-to-seat-everyone/">Source</a>
 */
fun interface MinMovesToSeat {
    operator fun invoke(seatPositions: IntArray, studentPositions: IntArray): Int
}

class MinMovesToSeatBruteForce : MinMovesToSeat {
    override operator fun invoke(seatPositions: IntArray, studentPositions: IntArray): Int {
        var totalMoves = 0
        seatPositions.sort()
        studentPositions.sort()
        for (index in seatPositions.indices) {
            val seatPosition = seatPositions[index]
            val studentPosition = studentPositions[index]
            if (seatPosition > studentPosition) {
                totalMoves += seatPosition.minus(studentPosition)
            } else if (studentPosition > seatPosition) {
                totalMoves += studentPosition.minus(seatPosition)
            }
        }
        return totalMoves
    }
}

class MinMovesToSeatMath : MinMovesToSeat {
    override operator fun invoke(seatPositions: IntArray, studentPositions: IntArray): Int {
        var totalMoves = 0
        seatPositions.sort()
        studentPositions.sort()
        for (index in seatPositions.indices) {
            val seatPosition = seatPositions[index]
            val studentPosition = studentPositions[index]
            totalMoves += abs(seatPosition - studentPosition)
        }
        return totalMoves
    }
}
