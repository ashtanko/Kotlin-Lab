/*
 * Copyright 2022 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.Iterative
import kotlin.math.abs

/**
 * 2116. Check if a Parentheses String Can Be Valid
 * @see <a href="https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/">Source</a>
 */
fun interface CanBeValid {
    operator fun invoke(s: String, locked: String): Boolean
}

@Iterative
class CanBeValidLeftRight : CanBeValid {
    override operator fun invoke(s: String, locked: String): Boolean {
        return s.length % 2 == 0 && validate(s, locked, '(') && validate(s, locked, ')')
    }

    private fun validate(s: String, locked: String, op: Char): Boolean {
        var bal = 0
        var wild = 0
        val sz = s.length
        val start = if (op == '(') 0 else sz - 1
        val dir = if (op == '(') 1 else -1
        var i = start
        while (i in 0 until sz && wild + bal >= 0) {
            if (locked[i] == '1') bal += if (s[i] == op) 1 else -1 else ++wild
            i += dir
        }
        return abs(bal) <= wild
    }
}

@Iterative
class CanBeValidCountingBrackets : CanBeValid {
    override operator fun invoke(s: String, locked: String): Boolean {
        if (s.length % 2 == 1) return false
        var total = 0
        var open = 0
        var closed = 0
        for (i in s.length - 1 downTo 0) {
            when {
                locked[i] == '0' -> total += 1
                s[i] == '(' -> open += 1
                s[i] == ')' -> closed += 1
            }
            if (total - open + closed < 0) return false
        }
        total = 0.also { closed = it }.also { open = it }
        for (i in s.indices) {
            when {
                locked[i] == '0' -> total += 1
                s[i] == '(' -> open += 1
                s[i] == ')' -> closed += 1
            }
            if (total + open - closed < 0) return false
        }
        return true
    }
}
