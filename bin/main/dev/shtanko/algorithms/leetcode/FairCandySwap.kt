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
 * Return an integer array ans where ans[0] is the size of the candy bar that Alice must exchange,
 * and ans[1] is the size of the candy bar that Bob must exchange. If there are multiple answers,
 * you may return any one of them.  It is guaranteed an answer exists.
 */
fun Pair<IntArray, IntArray>.fairCandySwap(): IntArray {
    val difSum = first.sum() - second.sum()
    val dif = difSum / 2
    val set: MutableSet<Int> = HashSet()
    for (a in first) {
        set.add(a)
    }
    for (b in second) {
        if (set.contains(b + dif)) return intArrayOf(b + dif, b)
    }
    return intArrayOf()
}
