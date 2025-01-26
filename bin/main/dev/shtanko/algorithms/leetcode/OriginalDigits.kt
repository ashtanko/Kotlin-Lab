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

private const val OUT_ARRAY_SIZE = 10

fun originalDigits(s: String): String {
    val count = CharArray(ALPHABET_LETTERS_COUNT + 'a'.code)
    for (letter in s.toCharArray()) {
        count[letter.code]++
    }
    val out = IntArray(OUT_ARRAY_SIZE)
    out[0] = count['z'.code].code
    out[2] = count['w'.code].code
    out[4] = count['u'.code].code
    out[6] = count['x'.code].code
    out[8] = count['g'.code].code
    out[3] = (count['h'.code] - out[8]).code
    out[5] = (count['f'.code] - out[4]).code
    out[7] = (count['s'.code] - out[6]).code
    out[9] = (count['i'.code] - out[5] - out[6] - out[8]).code
    out[1] = (count['n'.code] - out[7] - 2 * out[9]).code

    // building output string

    // building output string
    val output = StringBuilder()
    for (i in 0..9) for (j in 0 until out[i]) output.append(i)
    return output.toString()
}
