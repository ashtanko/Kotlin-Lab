/*
 * Copyright 2023 Oleksii Shtanko
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
 * 2038. Remove Colored Pieces if Both Neighbors are the Same Color
 * @see <a href="https://leetcode.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color">Source</a>
 */
fun interface WinnerOfGame {
    operator fun invoke(colors: String): Boolean
}

class WinnerOfGameCount : WinnerOfGame {
    override fun invoke(colors: String): Boolean {
        var alice = 0
        var bob = 0

        for (i in 1 until colors.length - 1) {
            if (colors[i - 1] == colors[i] && colors[i] == colors[i + 1]) {
                if (colors[i] == 'A') {
                    alice++
                } else {
                    bob++
                }
            }
        }

        return alice - bob >= 1
    }
}
