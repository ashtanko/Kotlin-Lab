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

import kotlin.math.abs

/**
 * Reformat The String
 */
fun String.reformat(): String {
    val str: MutableList<Char> = ArrayList()
    val nums: MutableList<Char> = ArrayList()
    for (c in this.toCharArray()) {
        if (Character.isDigit(c)) {
            nums.add(c)
        } else {
            str.add(c)
        }
    }

    val strLen = str.size
    val numLen = nums.size
    if (abs(strLen - numLen) >= 2) {
        return ""
    }
    return if (strLen < numLen) {
        printStr(nums, str)
    } else {
        printStr(str, nums)
    }
}

private fun printStr(
    l1: List<Char>,
    l2: List<Char>,
): String {
    var idx = 0
    val sb = StringBuilder()
    while (idx < l2.size) {
        sb.append(l1[idx])
        sb.append(l2[idx++])
    }
    while (idx < l1.size) {
        sb.append(l1[idx++])
    }
    return sb.toString()
}
