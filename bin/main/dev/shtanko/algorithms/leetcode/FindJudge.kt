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
 * Find the Town Judge.
 * @see <a href="https://leetcode.com/problems/find-the-town-judge/">Source</a>
 */
fun interface FindJudge {
    operator fun invoke(num: Int, trust: Array<IntArray>): Int
}

class FindJudgeTwoArrays : FindJudge {
    override operator fun invoke(num: Int, trust: Array<IntArray>): Int {
        if (trust.size < num - 1) {
            return -1
        }
        val inDegrees = IntArray(num + 1)
        val outDegrees = IntArray(num + 1)

        for (relation in trust) {
            outDegrees[relation[0]]++
            inDegrees[relation[1]]++
        }
        for (i in 1..num) {
            if (inDegrees[i] == num - 1 && outDegrees[i] == 0) {
                return i
            }
        }
        return -1
    }
}

class FindJudgeOneArray : FindJudge {
    override operator fun invoke(num: Int, trust: Array<IntArray>): Int {
        if (trust.size < num - 1) {
            return -1
        }
        val trustScores = IntArray(num + 1)
        for (relation in trust) {
            trustScores[relation[0]]--
            trustScores[relation[1]]++
        }

        for (i in 1..num) {
            if (trustScores[i] == num - 1) {
                return i
            }
        }
        return -1
    }
}
