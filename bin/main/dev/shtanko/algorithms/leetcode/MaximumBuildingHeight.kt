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

import kotlin.math.max
import kotlin.math.min

/**
 * 1840. Maximum Building Height
 * @see <a href="https://leetcode.com/problems/maximum-building-height/">Source</a>
 */
fun interface MaximumBuildingHeight {
    fun maxBuilding(n: Int, restrictions: Array<IntArray>): Int
}

class MaximumBuildingHeightImpl : MaximumBuildingHeight {
    override fun maxBuilding(n: Int, restrictions: Array<IntArray>): Int {
        // sorting restrictions according to index.
        restrictions.sortWith { a, b -> a[0] - b[0] }
        val len: Int = restrictions.size + 2
        val arr = Array(len) { IntArray(2) }
        arr[0][0] = 1
        arr[0][1] = 0
        arr[len - 1][0] = n
        arr[len - 1][1] = n - 1
        for (i in 1 until len - 1) {
            arr[i] = restrictions[i - 1]
        }

        // looping from left to right
        for (i in 0 until len - 1) {
            arr[i + 1][1] = min(arr[i + 1][1], arr[i][1] + (arr[i + 1][0] - arr[i][0]))
        }

        // looping from right to left
        for (i in len - 1 downTo 1) {
            arr[i - 1][1] = min(arr[i - 1][1], arr[i][1] + (arr[i][0] - arr[i - 1][0]))
        }

        var max = 0

        for (i in 0 until len - 1) {
            var j = arr[i][0]
            val h1 = arr[i][1]
            val k = arr[i + 1][0]
            val h2 = arr[i + 1][1]

            // calculating difference between heights of both buildings.
            val diff = max(h1, h2) - min(h1, h2)
            j += diff

            // calculating maximum height possible between both buildings.
            max = max(max, max(h1, h2) + (k - j) / 2)
        }

        return max
    }
}

class MaximumBuildingHeight2Passes : MaximumBuildingHeight {
    override fun maxBuilding(n: Int, restrictions: Array<IntArray>): Int {
        val newRes = Array<IntArray>(restrictions.size + 2) { intArrayOf() }
        System.arraycopy(restrictions, 0, newRes, 0, restrictions.size)

        // extra restriction for building 1
        newRes[newRes.size - 1] = intArrayOf(1, 0)
        // extra restriction for the last building
        newRes[newRes.size - 2] = intArrayOf(n, n - 1)
        newRes.sortWith { a, b -> a[0] - b[0] }

        // Adjust restrictions from the left
        for (i in 0 until newRes.size - 1) {
            val r1 = newRes[i][1]
            val r2 = newRes[i + 1][1]
            val h = r1 + (newRes[i + 1][0] - newRes[i][0])
            newRes[i + 1][1] = min(h, r2)
        }

        // Adjust restrictions from the right
        for (i in newRes.size - 2 downTo 0) {
            val r1 = newRes[i][1]
            val r2 = newRes[i + 1][1]
            val h = r2 + (newRes[i + 1][0] - newRes[i][0])
            newRes[i][1] = min(h, r1)
        }

        var res = 0
        for (i in 0 until newRes.size - 1) {
            val r1 = newRes[i][1]
            val r2 = newRes[i + 1][1]
            var h = r1 + (newRes[i + 1][0] - newRes[i][0])
            if (h > r2) h = r2 + (h - r2) / 2
            res = max(res, h)
        }

        return res
    }
}
