/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.algorithms.mocks.interview

/**
 * Given a list of words and a substitution map transform the given list of words with the given substitution map.
 * The substitution should be idempotent. In other words multiple calls of the function should produce the same output.
 */
fun wordSubstitution(wordList: List<String>, substituteMap: Map<String, String>): List<String> {
    val compressedMap = mutableMapOf<String, String>()

    for ((key, _) in substituteMap) {
        compressSubstituteMap(key, substituteMap, compressedMap)
    }

    return wordList.map { word ->
        if (word in compressedMap) compressedMap[word]!! else word
    }
}

fun compressSubstituteMap(
    key: String,
    substituteMap: Map<String, String>,
    compressedMap: MutableMap<String, String>,
): String {
    if (key in compressedMap) {
        return compressedMap[key]!!
    }

    if (key !in substituteMap) return key

    val compressedValue = compressSubstituteMap(substituteMap[key]!!, substituteMap, compressedMap)
    compressedMap[key] = compressedValue

    return compressedValue
}
