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
 * 1894. Find the Student that Will Replace the Chalk
 * @see <a href="https://leetcode.com/problems/find-the-student-that-will-replace-the-chalk/">Source</a>
 */
fun interface ChalkReplacer {
    operator fun invoke(chalkUsage: IntArray, remainingChalk: Int): Int
}

val chalkReplacerPrefixSum = ChalkReplacer { chalkUsage, remainingChalk ->
    var totalChalk = 0
    for (chalk in chalkUsage) {
        totalChalk += chalk
        if (totalChalk > remainingChalk) {
            break
        }
    }
    var chalkNeeded = remainingChalk % totalChalk
    for ((index, chalk) in chalkUsage.withIndex()) {
        if (chalkNeeded < chalk) {
            return@ChalkReplacer index
        }
        chalkNeeded -= chalk
    }
    return@ChalkReplacer 0
}

val chalkReplacerBs = ChalkReplacer { chalkUsage, remainingChalk ->
    val n = chalkUsage.size

    val prefixSum = IntArray(n)
    prefixSum[0] = chalkUsage[0]
    for (i in 1 until n) {
        prefixSum[i] = prefixSum[i - 1] + chalkUsage[i]
    }

    val totalChalk = prefixSum[n - 1]
    val chalkNeeded = remainingChalk % totalChalk

    prefixSum.binarySearch(chalkNeeded).let {
        return@ChalkReplacer if (it >= 0) {
            it
        } else {
            -it - 1
        }
    }
}
