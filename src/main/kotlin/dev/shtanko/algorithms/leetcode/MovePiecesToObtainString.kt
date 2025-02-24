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

import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 2337. Move Pieces to Obtain a String
 * @see <a href="https://leetcode.com/problems/move-pieces-to-obtain-a-string/">Source</a>
 */
@Medium("https://leetcode.com/problems/move-pieces-to-obtain-a-string/")
sealed interface MovePiecesToObtainString {
    operator fun invoke(start: String, target: String): Boolean

    @dev.shtanko.algorithms.annotations.TwoPointers
    data object TwoPointers : MovePiecesToObtainString {
        override fun invoke(start: String, target: String): Boolean {
            val length = start.length
            var startIndex = 0
            var targetIndex = 0

            while (startIndex < length || targetIndex < length) {
                // Skip underscores in the start string
                while (startIndex < length && start[startIndex] == '_') {
                    startIndex++
                }
                // Skip underscores in the target string
                while (targetIndex < length && target[targetIndex] == '_') {
                    targetIndex++
                }

                // If one string is exhausted, both should be exhausted
                if (startIndex == length || targetIndex == length) {
                    return startIndex == length && targetIndex == length
                }

                // Check if characters match and follow movement rules
                if (
                    start[startIndex] != target[targetIndex] ||
                    (start[startIndex] == 'L' && startIndex < targetIndex) ||
                    (start[startIndex] == 'R' && startIndex > targetIndex)
                ) {
                    return false
                }

                startIndex++
                targetIndex++
            }

            // If all conditions are satisfied, return true
            return true
        }
    }

    data object Queue : MovePiecesToObtainString {
        override fun invoke(start: String, target: String): Boolean {
            // Queues to store characters and indices from both strings
            val startQueue: MutableList<Pair<Char, Int>> = mutableListOf()
            val targetQueue: MutableList<Pair<Char, Int>> = mutableListOf()

            // Record non-underscore characters and their indices
            for (i in start.indices) {
                if (start[i] != '_') {
                    startQueue.add(start[i] to i)
                }
                if (target[i] != '_') {
                    targetQueue.add(target[i] to i)
                }
            }

            // If the number of pieces don't match, return false
            if (startQueue.size != targetQueue.size) return false

            // Compare each piece's type and position
            for (i in startQueue.indices) {
                val (startChar, startIndex) = startQueue[i]
                val (targetChar, targetIndex) = targetQueue[i]

                // Check character match and movement rules
                if (
                    startChar != targetChar ||
                    (startChar == 'L' && startIndex < targetIndex) ||
                    (startChar == 'R' && startIndex > targetIndex)
                ) {
                    return false
                }
            }

            return true
        }
    }
}
