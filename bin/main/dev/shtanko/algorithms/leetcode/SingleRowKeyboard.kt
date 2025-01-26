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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import kotlin.math.abs

fun interface SingleRowKeyboard {
    operator fun invoke(keyboard: String, word: String): Int
}

class SingleRowKeyboardNaive : SingleRowKeyboard {
    override operator fun invoke(keyboard: String, word: String): Int {
        val map = IntArray(ALPHABET_LETTERS_COUNT)
        for (i in keyboard.indices) {
            map[keyboard[i] - 'a'] = i
        }
        var res = 0
        var prev = 0
        for (w in word) {
            val idx = map[w - 'a']
            res += abs(prev - idx)
            prev = idx
        }

        return res
    }
}
