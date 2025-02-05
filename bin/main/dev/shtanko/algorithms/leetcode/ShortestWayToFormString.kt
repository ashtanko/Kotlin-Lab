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

object ShortestWayToFormString {
    /**
     * Function twoPointers calculates the minimum number of subsequences required to form a string `target` from a
     * string `source`.
     * It uses two pointers to traverse the `source` and `target` strings, and counts the number of subsequences.
     *
     * @param source The source string from which subsequences are formed.
     * @param target The target string to be formed from the subsequences.
     * @return The minimum number of subsequences required to form the target string. Returns -1 if it is not possible
     * to form the target string from the source string.
     */
    fun twoPointers(source: String, target: String): Int {
        var targetIndex = 0
        var sequenceCount = 0
        while (targetIndex < target.length) {
            val previousTargetIndex = targetIndex
            for (sourceIndex in source.indices) {
                if (targetIndex < target.length && source[sourceIndex] == target[targetIndex]) {
                    targetIndex++
                }
            }

            if (targetIndex == previousTargetIndex) {
                return -1
            }
            sequenceCount++
        }
        return sequenceCount
    }
}
