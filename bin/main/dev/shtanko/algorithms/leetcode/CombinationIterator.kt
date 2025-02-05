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

import java.util.Stack

interface CombinationIterator {
    fun next(): String

    fun hasNext(): Boolean
}

class CombinationIteratorImpl(characters: String, combinationLength: Int) : CombinationIterator {

    private val st = Stack<Char>()
    private val ch2Idx = hashMapOf<Char, Int>()
    private var result = ""
    private val str = characters
    private val combLength = combinationLength

    init {
        createCombination(characters, combinationLength)
    }

    override fun next(): String {
        val currResult = result
        var idx = str.length - 1
        while (st.isNotEmpty() && ch2Idx[st.peek()] == idx) {
            st.pop()
            idx--
            result = result.substring(0, result.length - 1)
        }
        if (st.isEmpty()) return currResult
        idx = ch2Idx[st.pop()] ?: 0
        result = result.substring(0, result.length - 1)
        while (st.size != combLength) {
            val temp = str[++idx]
            result += temp
            st.push(temp)
        }
        return currResult
    }

    override fun hasNext(): Boolean = st.isNotEmpty()

    private fun createCombination(characters: String, combinationLength: Int) {
        for (ch in characters) {
            st.push(ch)
            result += ch
            if (st.size == combinationLength) break
        }
        var idx = 0
        for (ch in characters) {
            ch2Idx[ch] = idx++
        }
    }
}
