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

/**
 * 2103. Rings and Rods
 * @see <a href="https://leetcode.com/problems/rings-and-rods">Source</a>
 */
fun interface RingsAndRods {
    fun countPoints(rings: String): Int
}

class RingsAndRodsBF : RingsAndRods {
    override fun countPoints(rings: String): Int {
        val map = HashMap<String, MutableList<String>>()
        rings.chunked(2).forEach {
            val a = it[1].toString()
            val b = it.first().toString()
            if (map[a] == null) {
                map[a] = mutableListOf()
                map[a]?.add(b)
            } else {
                map[a]?.add(b)
            }
        }
        var i = 0
        map.forEach { (_, u) ->
            if (u.containsAll(listOf("R", "G", "B"))) {
                i++
            }
        }
        return i
    }
}

class RingsAndRodsBitmask : RingsAndRods {
    override fun countPoints(rings: String): Int {
        val rods = IntArray(10)
        var i = 0
        while (i < rings.length - 1) {
            if (rings[i] == 'R') {
                rods[rings[i + 1] - '0'] = rods[rings[i + 1] - '0'] or (1 shl 0)
            }
            if (rings[i] == 'G') {
                rods[rings[i + 1] - '0'] = rods[rings[i + 1] - '0'] or (1 shl 1)
            }
            if (rings[i] == 'B') {
                rods[rings[i + 1] - '0'] = rods[rings[i + 1] - '0'] or (1 shl 2)
            }
            i += 2
        }
        var total = 0
        for (i0 in rods.indices) {
            if (rods[i0] == 7) total++
        }
        return total
    }
}

class RingsAndRodsArray : RingsAndRods {
    class Ring {
        var hasRead = false
        var hasGreen = false
        var hasBlue = false
    }

    override fun countPoints(rings: String): Int {
        // Initialize
        val arr = arrayOfNulls<Ring>(10)
        for (i in 0..9) {
            arr[i] = Ring()
        }

        // Check every pair of chars
        var index = 0
        val size: Int = rings.length
        while (index < size) {
            val target: Int = java.lang.String.valueOf(rings[index + 1]).toInt()
            when (rings[index]) {
                'R' -> {
                    arr[target]?.hasRead = true
                }

                'G' -> {
                    arr[target]?.hasGreen = true
                }

                else -> {
                    arr[target]?.hasBlue = true
                }
            }
            index += 2
        }

        // Count them
        var count = 0
        for (i in 0..9) {
            if (arr[i]?.hasRead == true && arr[i]?.hasGreen == true && arr[i]?.hasBlue == true) {
                count++
            }
        }
        return count
    }
}
