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
 * 2125. Number of Laser Beams in a Bank
 * @see <a href="https://leetcode.com/problems/number-of-laser-beams-in-a-bank">Source</a>
 */
fun interface NumOfBeams {
    operator fun invoke(bank: Array<String>): Int
}

internal val mumOfBeamsSolution = NumOfBeams { sequence ->
    sequence.foldIndexed(Pair(0, 0)) { _, acc, beam ->
        val count = beam.count { it == '1' }
        if (count > 0) Pair((acc.first + acc.second * count), count) else acc
    }.first
}
