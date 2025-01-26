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

/**
 * 2582. Pass the Pillow
 * @see <a href="https://leetcode.com/problems/pass-the-pillow">Source</a>
 */
fun interface PassThePillow {
    operator fun invoke(num: Int, time: Int): Int
}

class PassThePillowSimulation : PassThePillow {
    override fun invoke(num: Int, time: Int): Int {
        var currentPillowPosition = 1
        var currentTime = 0
        var direction = 1
        while (currentTime < time) {
            if (currentPillowPosition + direction in 1..num) {
                currentPillowPosition += direction
                currentTime++
            } else {
                // Reverse the direction if the next position is out of bounds
                direction *= -1
            }
        }
        return currentPillowPosition
    }
}

class PassThePillowMath : PassThePillow {
    override fun invoke(num: Int, time: Int): Int {
        val fullRounds: Int = time / (num - 1)
        val extraTime: Int = time % (num - 1)
        return if (fullRounds % 2 == 0) {
            extraTime + 1
        } else {
            num - extraTime
        }
    }
}
