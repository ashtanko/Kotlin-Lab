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

import dev.shtanko.algorithms.annotations.BruteForce
import dev.shtanko.algorithms.annotations.Iterative
import dev.shtanko.algorithms.annotations.Math
import kotlin.math.max

/**
 * 135. Candy
 * @see <a href="https://leetcode.com/problems/candy">Source</a>
 */
fun interface Candy {
    operator fun invoke(ratings: IntArray): Int
}

@BruteForce
class CandyBruteForce : Candy {
    override operator fun invoke(ratings: IntArray): Int {
        val candies = IntArray(ratings.size) { 1 }
        var flag = true
        var sum = 0
        while (flag) {
            flag = false
            for (i in ratings.indices) {
                if (i != ratings.size - 1 && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1
                    flag = true
                }
                if (i > 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i] = candies[i - 1] + 1
                    flag = true
                }
            }
        }
        for (candy in candies) {
            sum += candy
        }
        return sum
    }
}

@Iterative
class Candy2Arrays : Candy {
    override operator fun invoke(ratings: IntArray): Int {
        var sum = 0
        val left2right = IntArray(ratings.size) { 1 }
        val right2left = IntArray(ratings.size) { 1 }
        for (i in 1 until ratings.size) {
            if (ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1
            }
        }
        for (i in ratings.size - 2 downTo 0) {
            if (ratings[i] > ratings[i + 1]) {
                right2left[i] = right2left[i + 1] + 1
            }
        }
        for (i in ratings.indices) {
            sum += max(left2right[i], right2left[i])
        }
        return sum
    }
}

class CandyArray : Candy {
    override operator fun invoke(ratings: IntArray): Int {
        if (ratings.isEmpty()) return 0
        val candies = IntArray(ratings.size) { 1 }
        for (i in 1 until ratings.size) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1
            }
        }
        var sum = candies[ratings.size - 1]
        for (i in ratings.size - 2 downTo 0) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = max(candies[i], candies[i + 1] + 1)
            }
            sum += candies[i]
        }
        return sum
    }
}

@Math
class CandyMath : Candy {

    override operator fun invoke(ratings: IntArray): Int {
        fun count(n: Int) = n * n.plus(1) / 2

        if (ratings.size <= 1) {
            return ratings.size
        }
        var candies = 0
        var up = 0
        var down = 0
        var oldSlope = 0
        for (i in 1 until ratings.size) {
            val newSlope = if (ratings[i] > ratings[i - 1]) 1 else if (ratings[i] < ratings[i - 1]) -1 else 0
            val equalPredicate = oldSlope > 0 && newSlope == 0
            val greeterPredicate = oldSlope < 0 && newSlope >= 0

            if (equalPredicate || greeterPredicate) {
                candies += count(up) + count(down) + max(up, down)
                up = 0
                down = 0
            }
            if (newSlope > 0) up++
            if (newSlope < 0) down++
            if (newSlope == 0) candies++
            oldSlope = newSlope
        }
        candies += count(up) + count(down) + max(up, down) + 1
        return candies
    }
}
