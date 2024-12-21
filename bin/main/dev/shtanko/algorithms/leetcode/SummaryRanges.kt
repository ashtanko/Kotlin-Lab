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

import java.util.LinkedList

/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 */
fun IntArray.getSummaryRanges(): List<String> {
    val summaryRanges: MutableList<String> = LinkedList()
    var index = 0
    while (index < size) {
        val start = this[index]
        while (index + 1 < size && this[index + 1] - this[index] == 1) index++
        summaryRanges.add(start.toString() + if (start == this[index]) "" else "->" + this[index])
        index++
    }
    return summaryRanges
}
