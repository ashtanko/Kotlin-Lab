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

/**
 * Wildcard Matching
 */
fun Pair<String, String>.isMatch(): Boolean {
    val stringLength = first.length
    val patternLength = second.length
    val stringChars = first.toCharArray()
    val patternChars = second.toCharArray()
    val matchMatrix = Array(stringLength + 1) { BooleanArray(patternLength + 1) }
    matchMatrix[0][0] = true
    for (patternIndex in 1..patternLength) {
        matchMatrix[0][patternIndex] = matchMatrix[0][patternIndex - 1] && patternChars[patternIndex - 1] == '*'
    }
    for (stringIndex in 1..stringLength) {
        matchMatrix[stringIndex][0] = false
    }
    for (stringIdx in 1..stringLength) {
        for (patternIdx in 1..patternLength) {
            if (patternChars[patternIdx - 1] == '?' || stringChars[stringIdx - 1] == patternChars[patternIdx - 1]) {
                matchMatrix[stringIdx][patternIdx] = matchMatrix[stringIdx - 1][patternIdx - 1]
            } else if (patternChars[patternIdx - 1] == '*') {
                matchMatrix[stringIdx][patternIdx] =
                    matchMatrix[stringIdx - 1][patternIdx] || matchMatrix[stringIdx][patternIdx - 1]
            }
        }
    }
    return matchMatrix[stringLength][patternLength]
}
