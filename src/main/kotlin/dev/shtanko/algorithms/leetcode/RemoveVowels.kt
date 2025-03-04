/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.extensions.isVowel

fun interface RemoveVowels {
    operator fun invoke(s: String): String
}

/**
 * Time complexity: O(n).
 * Space complexity: O(1).
 */
class RemoveVowelsBruteForce : RemoveVowels {
    override operator fun invoke(s: String): String {
        val vowels = charArrayOf('a', 'e', 'i', 'o', 'u')
        return s.filter { !vowels.contains(it) }
    }
}

class RemoveVowelsStringBuffer : RemoveVowels {
    override operator fun invoke(s: String): String {
        val sb = StringBuffer(s.length)
        s.filter {
            it.isVowel().not()
        }.forEach {
            sb.append(it)
        }
        return sb.toString()
    }
}

class RemoveVowelsFilter : RemoveVowels {
    override operator fun invoke(s: String): String = s.filter { !it.isVowel() }
}

class RemoveVowelsReplace : RemoveVowels {
    override operator fun invoke(s: String): String {
        return s.replace("[aeiou]".toRegex(), "")
    }
}
