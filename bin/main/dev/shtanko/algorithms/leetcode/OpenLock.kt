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
 * 752. Open the Lock
 * @see <a href="https://leetcode.com/problems/open-the-lock/">Source</a>
 */
fun interface OpenLock {
    operator fun invoke(deadEnds: Array<String>, target: String): Int
}

class LockOpenerBFS : OpenLock {
    private var targetPositions: MutableSet<String> = HashSet()

    override fun invoke(deadEnds: Array<String>, target: String): Int {
        if (deadEnds.isEmpty() || target.isEmpty()) {
            return DEFAULT_RESULT
        }
        val deadEndsSet: MutableSet<String> = HashSet(deadEnds.toList())
        targetPositions.add(target)
        var level = 0
        var nextPositions: MutableSet<String>
        while (startPositions.isNotEmpty() && targetPositions.isNotEmpty()) {
            if (startPositions.size > targetPositions.size) {
                nextPositions = startPositions
                startPositions = targetPositions
                targetPositions = nextPositions
            }
            nextPositions = HashSet()
            for (currentPosition in startPositions) {
                if (targetPositions.contains(currentPosition)) {
                    return level
                }
                if (deadEndsSet.contains(currentPosition)) {
                    continue
                }
                deadEndsSet.add(currentPosition)
                val positionBuilder = StringBuilder(currentPosition)
                for (i in 0 until LOCK_SIZE) {
                    val c = positionBuilder[i]
                    val nextPosition1 = positionBuilder.substring(
                        0,
                        i,
                    ) + (if (c == '9') 0 else c - '0' + 1) + positionBuilder.substring(
                        i + 1,
                    )
                    val nextPosition2 = positionBuilder.substring(
                        0,
                        i,
                    ) + (if (c == '0') 9 else c - '0' - 1) + positionBuilder.substring(
                        i + 1,
                    )
                    if (!deadEndsSet.contains(nextPosition1)) {
                        nextPositions.add(nextPosition1)
                    }
                    if (!deadEndsSet.contains(nextPosition2)) {
                        nextPositions.add(nextPosition2)
                    }
                }
            }
            level++
            startPositions = nextPositions
        }
        return DEFAULT_RESULT
    }

    private var startPositions: MutableSet<String> = HashSet<String>().apply {
        add(START_LOCK_POSITION)
    }

    companion object {
        private const val START_LOCK_POSITION = "0000"
        private const val LOCK_SIZE = 4
        private const val DEFAULT_RESULT = -1
    }
}
