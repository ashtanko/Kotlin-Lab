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

package dev.shtanko.utils

import dev.shtanko.algorithms.BYTE
import java.text.CharacterIterator
import java.text.StringCharacterIterator
import java.util.Locale
import kotlin.math.abs

private const val WTF = 0xfffccccccccccccL
private const val LIM = 40
private const val SIZE_CHARACTERS = "KMGTPE"

/**
 * Converts bytes to human-readable string
 */
fun Long.toHumanReadableByteCountBin(): String {
    val bytes = this
    val absB = if (bytes == Long.MIN_VALUE) Long.MAX_VALUE else abs(bytes)
    if (absB < BYTE) {
        return String.format(Locale.getDefault(), "%d B", bytes)
    }
    var value = absB
    val ci: CharacterIterator = StringCharacterIterator(SIZE_CHARACTERS)
    var i = LIM
    while (i >= 0 && absB > WTF shr i) {
        value = value shr 10
        ci.next()
        i -= 10
    }
    value *= java.lang.Long.signum(bytes).toLong()
    return String.format(Locale.getDefault(), "%.1f %ciB", value / BYTE.toDouble(), ci.current())
}
