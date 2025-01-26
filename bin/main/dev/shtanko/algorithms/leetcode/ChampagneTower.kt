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

import kotlin.math.min

/**
 * 799. Champagne Tower
 * https://leetcode.com/problems/champagne-tower/
 */
fun interface ChampagneTower {
    operator fun invoke(poured: Int, queryRow: Int, queryGlass: Int): Double
}

class ChampagneTowerSimulation : ChampagneTower {
    override operator fun invoke(poured: Int, queryRow: Int, queryGlass: Int): Double {
        val arr = Array(SIZE) { DoubleArray(SIZE) }
        arr[0][0] = poured.toDouble()
        for (r in 0..queryRow) {
            for (c in 0..r) {
                val q = (arr[r][c] - 1.0) / 2.0
                if (q > 0) {
                    arr[r + 1][c] += q
                    arr[r + 1][c + 1] += q
                }
            }
        }

        return min(1.0, arr[queryRow][queryGlass])
    }

    companion object {
        private const val SIZE = 102
    }
}
