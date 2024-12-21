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

import dev.shtanko.algorithms.annotations.level.Hard

/**
 * 466. Count The Repetitions
 * @see <a href="https://leetcode.com/problems/count-the-repetitions/">Source</a>
 */
@Hard("https://leetcode.com/problems/count-the-repetitions")
fun interface CountTheRepetitions {
    operator fun invoke(s1: String, n1: Int, s2: String, n2: Int): Int
}

class CountTheRepetitionsBruteForce : CountTheRepetitions {
    override operator fun invoke(s1: String, n1: Int, s2: String, n2: Int): Int {
        if (n2 == 0) return 0
        var index = 0
        var repeatCount = 0
        val s1Size: Int = s1.length
        val s2Size: Int = s2.length
        for (i in 0 until n1) {
            for (j in 0 until s1Size) {
                if (s1[j] == s2[index]) ++index
                if (index == s2Size) {
                    index = 0
                    ++repeatCount
                }
            }
        }
        return repeatCount / n2
    }
}

class CountTheRepetitionsBetterBruteForce : CountTheRepetitions {

    override operator fun invoke(s1: String, n1: Int, s2: String, n2: Int): Int {
        val reps = IntArray(ARRAY_SIZE)
        val rests = IntArray(ARRAY_SIZE)
        var posRest = 0
        var repTime = 0
        var i = 0
        var k = 0
        if (n1 <= 0) return 0
        while (k == i) {
            i++
            for (j in s1.indices) {
                if (s2[posRest] == s1[j]) {
                    posRest++
                    if (posRest == s2.length) {
                        repTime++
                        posRest = 0
                    }
                }
            }
            if (i >= n1) return repTime / n2
            k = 0
            while (k < i) {
                if (posRest == rests[k]) break
                k++
            }
            reps[i] = repTime
            rests[i] = posRest
        }
        val interval = i - k
        val repeatCount = n1.plus(k) / interval
        val repeatTimes = repeatCount * reps[i].minus(reps[k])
        val remainTimes = reps[n1.minus(k) % interval + k]
        return repeatTimes.plus(remainTimes) / n2
    }

    companion object {
        private const val ARRAY_SIZE = 102
    }
}
