/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.algorithms.exercises

fun interface StringPermutations {
    fun perform(str: String, ans: String): Array<String>
}

class StringPermutationsRecursive : StringPermutations {
    val list: MutableList<String> = ArrayList()

    override fun perform(str: String, ans: String): Array<String> {
        if (str.isEmpty()) {
            list.add(ans)
            return arrayOf()
        }

        for (i in str.indices) {
            val ch = str[i]
            val ros = str.substring(0, i) + str.substring(i + 1)
            perform(ros, ans + ch)
        }
        return list.toTypedArray()
    }
}

class StringPermutationsIterative : StringPermutations {
    val list: MutableList<String> = ArrayList()

    override fun perform(str: String, ans: String): Array<String> {
        if (str.isEmpty()) {
            list.add(ans)
            return arrayOf()
        }

        val alpha = BooleanArray(ALPHA_SIZE)

        for (i in str.indices) {
            val ch = str[i]
            val ros = str.substring(0, i) + str.substring(i + 1)

            if (!alpha[ch - 'a']) {
                perform(ros, ans + ch)
            }
            alpha[ch - 'a'] = true
        }

        return list.toTypedArray()
    }

    companion object {
        private const val ALPHA_SIZE = 26
    }
}
