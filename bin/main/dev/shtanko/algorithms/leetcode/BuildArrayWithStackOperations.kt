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

/**
 * 1441. Build an Array With Stack Operations
 * @see <a href="https://leetcode.com/problems/build-an-array-with-stack-operations">Source</a>
 */
class BuildArrayWithStackOperations {

    operator fun invoke(target: IntArray, n: Int): MutableList<String> {
        val result: MutableList<String> = ArrayList()
        var j = 0
        var i = 1
        while (i <= n && j < target.size) {
            result.add(PUSH)
            if (target[j] == i) {
                j++
            } else {
                result.add(POP)
            }
            i++
        }
        return result
    }

    companion object {
        private const val PUSH = "Push"
        private const val POP = "Pop"
    }
}
