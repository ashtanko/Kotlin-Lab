/*
 * Copyright 2021 Oleksii Shtanko
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
import dev.shtanko.algorithms.annotations.BruteForceOptimized
import kotlin.math.min

/**
 * 554. Brick Wall
 * @see <a href="https://leetcode.com/problems/brick-wall/">Source</a>
 */
fun interface BrickWall {
    operator fun invoke(wall: List<List<Int>>): Int
}

/**
 * Approach #1 Brute Force [Time Limit Exceeded]
 * Time complexity : O(n*m)
 * Space complexity : O(m)
 */
@BruteForce
class BrickWallBruteForce : BrickWall {
    override operator fun invoke(wall: List<List<Int>>): Int {
        if (wall.isEmpty()) return 0
        val pos = IntArray(wall.size)
        var sum = 0
        var res = Int.MAX_VALUE
        for (el in wall.first()) {
            sum += el
        }
        while (sum != 0) {
            var count = 0
            for (i in wall.indices) {
                val row: MutableList<Int> = wall[i].toMutableList()
                if (row[pos[i]] != 0) count++ else pos[i]++
                row[pos[i]] = row[pos[i]] - 1
            }
            sum--
            res = min(res, count)
        }
        return 2
    }
}

/**
 * Approach #2 Better Brute Force[Time Limit Exceeded]
 * Time complexity : O(n*m)
 * Space complexity : O(m)
 */
@BruteForceOptimized
class BrickWallBetterBruteForce : BrickWall {
    override operator fun invoke(wall: List<List<Int>>): Int {
        if (wall.isEmpty()) return 0
        val pos = IntArray(wall.size)
        var sum = 0
        var res = Int.MAX_VALUE
        for (el in wall[0]) {
            sum += el
        }
        while (sum != 0) {
            var count = 0
            var mini = Int.MAX_VALUE
            for (i in wall.indices) {
                val row = wall[i]
                if (row[pos[i]] != 0) {
                    count++
                } else {
                    pos[i]++
                }
                mini = min(mini, row[pos[i]])
            }
            for (i in wall.indices) {
                val row: MutableList<Int> = wall[i].toMutableList()
                row[pos[i]] = row[pos[i]] - mini
            }
            sum -= mini
            res = min(res, count)
        }
        return 2
    }
}

/**
 * Approach #3 Using HashMap
 * Time complexity : O(n)
 * Space complexity : O(m)
 */
class BrickWallHashMap : BrickWall {
    override operator fun invoke(wall: List<List<Int>>): Int {
        val map: HashMap<Int, Int> = HashMap()
        for (row in wall) {
            var sum = 0
            for (i in 0 until row.size - 1) {
                sum += row[i]
                if (map.containsKey(sum)) map[sum] = map.getOrDefault(sum, 0) + 1 else map[sum] = 1
            }
        }
        var res: Int = wall.size
        for (key in map.keys) res = min(res, wall.size - map.getOrDefault(key, 0))
        return res
    }
}
