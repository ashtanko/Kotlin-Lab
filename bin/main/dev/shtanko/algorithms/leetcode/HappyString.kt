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

/**
 * 1415. The k-th Lexicographical String of All Happy Strings of Length n
 * @see <a href="https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/">
 *     Source</a>
 */
fun interface HappyString {
    operator fun invoke(n: Int, k: Int): String
}

class HappyStringDFS : HappyString {
    override operator fun invoke(n: Int, k: Int): String {
        val str = charArrayOf('a', 'b', 'c')
        val kArr = IntArray(1)
        kArr[0] = k
        return dfs(str, kArr, '0', 0, n)
    }

    private fun dfs(str: CharArray, k: IntArray, prev: Char, index: Int, n: Int): String {
        if (index == n) {
            k[0]--
        } else {
            for (i in 0..2) {
                if (str[i] != prev) {
                    val res = dfs(str, k, str[i], index + 1, n)
                    if (k[0] == 0) return str[i].toString() + res
                }
            }
        }
        return ""
    }
}

class HappyStringMath : HappyString {
    override operator fun invoke(n: Int, k: Int): String {
        var kk = k
        var prem = 1 shl n - 1
        if (kk > 3 * prem) {
            return ""
        }
        var ch = ('a' + (kk - 1) / prem).code
        val sb = StringBuilder(Character.toString(ch))
        while (prem > 1) {
            kk = (kk - 1) % prem + 1
            prem = prem shr 1
            val aCode = if (ch == 'a'.code) 1 else 0
            ch = if ((kk - 1) / prem == 0) 'a'.code + aCode else 'b'.code + if (ch != 'c'.code) 1 else 0
            sb.append(ch.toChar())
        }
        return sb.toString()
    }
}
