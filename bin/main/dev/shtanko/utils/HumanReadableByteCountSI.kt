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

import java.text.CharacterIterator
import java.text.StringCharacterIterator
import java.util.Locale

private const val WTF_IN = -999950
private const val WTF_OUT = 999950
private const val SIZE_CHARACTERS = "kMGTPE"
private const val ONE_T = 1000

/**
 * Converts bytes to human-readable string
 */
fun Long.toHumanReadableByteCountSI(): String {
    var bytes = this
    if (-ONE_T < bytes && bytes < ONE_T) {
        return "$bytes B"
    }
    val ci: CharacterIterator = StringCharacterIterator(SIZE_CHARACTERS)
    while (bytes <= WTF_IN || bytes >= WTF_OUT) {
        bytes /= ONE_T
        ci.next()
    }
    return String.format(Locale.getDefault(), "%.1f %cB", bytes / ONE_T.toDouble(), ci.current())
}
