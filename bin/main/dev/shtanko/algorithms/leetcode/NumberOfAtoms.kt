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

import dev.shtanko.algorithms.annotations.Recursive
import java.util.Stack
import java.util.TreeMap
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * 726. Number of Atoms
 * @see <a href="https://leetcode.com/problems/number-of-atoms/">Source</a>
 */
fun interface NumberOfAtoms {
    operator fun invoke(formula: String): String
}

/**
 * Approach #1: Recursion
 */
@Recursive
class NumberOfAtomsRecursion : NumberOfAtoms {
    var i = 0

    override fun invoke(formula: String): String {
        val ans = StringBuilder()
        i = 0
        val count = parse(formula)
        for (name in count.keys) {
            ans.append(name)
            val multiplicity = count.getOrDefault(name, -1)
            if (multiplicity > 1) {
                ans.append("" + multiplicity)
            }
        }
        return String(ans)
    }

    private fun parse(formula: String): Map<String, Int> {
        val n = formula.length
        val count: MutableMap<String, Int> = TreeMap()
        while (i < n && formula[i] != ')') {
            if (formula[i] == '(') {
                i++
                for ((key, value) in parse(formula)) {
                    count[key] = count.getOrDefault(key, 0) + value
                }
            } else {
                var iStart: Int = i++
                while (i < n && Character.isLowerCase(formula[i])) {
                    i++
                }
                val name = formula.substring(iStart, i)
                iStart = i
                while (i < n && Character.isDigit(formula[i])) {
                    i++
                }
                val multiplicity = if (iStart < i) formula.substring(iStart, i).toInt() else 1
                count[name] = count.getOrDefault(name, 0) + multiplicity
            }
        }
        val iStart: Int = ++i
        while (i < n && Character.isDigit(formula[i])) {
            i++
        }
        if (iStart < i) {
            val multiplicity = formula.substring(iStart, i).toInt()
            for (key in count.keys) {
                count[key] = count[key]!! * multiplicity
            }
        }
        return count
    }
}

/**
 * Approach #2: Stack
 */
class NumberOfAtomsStack : NumberOfAtoms {
    override fun invoke(formula: String): String {
        val n: Int = formula.length
        val stack: Stack<MutableMap<String, Int>> = Stack()
        stack.push(TreeMap())
        var i = 0
        while (i < n) {
            if (formula[i] == '(') {
                stack.push(TreeMap())
                i++
            } else if (formula[i] == ')') {
                val top: Map<String, Int> = stack.pop()
                val iStart = ++i
                var multiplicity = 1
                while (i < n && Character.isDigit(formula[i])) {
                    i++
                }
                if (i > iStart) multiplicity = formula.substring(iStart, i).toInt()
                for (c in top.keys) {
                    val v = top[c]!!
                    stack.peek()[c] = stack.peek().getOrDefault(c, 0) + v * multiplicity
                }
            } else {
                var iStart = i++
                while (i < n && Character.isLowerCase(formula[i])) {
                    i++
                }
                val name = formula.substring(iStart, i)
                iStart = i
                while (i < n && Character.isDigit(formula[i])) {
                    i++
                }
                val multiplicity = if (i > iStart) formula.substring(iStart, i).toInt() else 1
                stack.peek()[name] = stack.peek().getOrDefault(name, 0) + multiplicity
            }
        }
        return format(stack)
    }

    fun format(stack: Stack<MutableMap<String, Int>>): String {
        val ans = StringBuilder()
        for (name in stack.peek().keys) {
            ans.append(name)
            val multiplicity: Int = stack.peek()[name] ?: -1
            if (multiplicity > 1) {
                ans.append("" + multiplicity)
            }
        }
        return ans.toString()
    }
}

/**
 * Approach #3: Regular Expressions
 */
class NumberOfAtomsRegex : NumberOfAtoms {
    override fun invoke(formula: String): String {
        val matcher: Matcher = Pattern.compile("([A-Z][a-z]*)(\\d*)|(\\()|(\\))(\\d*)").matcher(formula)
        val stack: Stack<MutableMap<String, Int>> = Stack()
        stack.push(TreeMap())

        while (matcher.find()) {
            val match: String = matcher.group()
            if (match == "(") {
                stack.push(TreeMap())
            } else if (match.startsWith(")")) {
                val top: Map<String, Int> = stack.pop()
                val multiplicity = if (match.length > 1) match.substring(1, match.length).toInt() else 1
                for (name in top.keys) {
                    stack.peek()[name] = stack.peek().getOrDefault(name, 0) + top.getOrDefault(name, 0) * multiplicity
                }
            } else {
                var i = 1
                while (i < match.length && Character.isLowerCase(match[i])) {
                    i++
                }
                val name = match.substring(0, i)
                val multiplicity = if (i < match.length) match.substring(i, match.length).toInt() else 1
                stack.peek()[name] = stack.peek().getOrDefault(name, 0) + multiplicity
            }
        }

        val ans = StringBuilder()
        for (name in stack.peek().keys) {
            ans.append(name)
            val count = stack.peek()[name] ?: -1
            if (count > 1) ans.append(count.toString())
        }
        return ans.toString()
    }
}
