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

import dev.shtanko.algorithms.annotations.DFS
import kotlin.math.max

/**
 * 140. Word Break II
 * @see <a href="https://leetcode.com/problems/word-break-ii/">Source</a>
 */
fun interface WordBreak2 {
    operator fun invoke(str: String, wordDict: List<String>): List<String>
}

@DFS
class WordBreak2DPDFS : WordBreak2 {
    override operator fun invoke(str: String, wordDict: List<String>): List<String> {
        val wordStartIndices: Array<MutableList<Int>?> = arrayOfNulls(str.length + 1)

        wordStartIndices[0] = ArrayList()

        val maxWordLength = getMaxWordLength(wordDict)
        for (i in 1..str.length) {
            var j = i - 1
            while (j >= i - maxWordLength && j >= 0) {
                if (wordStartIndices[j] == null) {
                    j--
                    continue
                }
                val word = str.substring(j, i)
                if (wordDict.contains(word)) {
                    if (wordStartIndices[i] == null) {
                        wordStartIndices[i] = ArrayList()
                    }
                    wordStartIndices[i]?.add(j)
                }
                j--
            }
        }

        val results: MutableList<String> = ArrayList()
        if (wordStartIndices[str.length] == null) {
            return results
        }

        depthFirstSearch(results, "", str, wordStartIndices, str.length)
        return results
    }

    private fun depthFirstSearch(
        results: MutableList<String>,
        path: String,
        inputString: String,
        wordStartIndices: Array<MutableList<Int>?>,
        end: Int,
    ) {
        if (end == 0) {
            results.add(path.substring(1))
            return
        }
        for (start in wordStartIndices[end] ?: emptyList()) {
            val word = inputString.substring(start, end)
            depthFirstSearch(results, " $word$path", inputString, wordStartIndices, start)
        }
    }

    private fun getMaxWordLength(wordDictionary: List<String>): Int {
        var max = 0
        for (word in wordDictionary) {
            max = max(max, word.length)
        }
        return max
    }
}

/**
 * Method 3: DP Prunning + Backtracking
 */
class WordBreak2Backtracking : WordBreak2 {
    override operator fun invoke(str: String, wordDict: List<String>): List<String> {
        val results: MutableList<String> = ArrayList()
        val canBreak = BooleanArray(str.length) { true }
        val stringBuilder = StringBuilder()
        depthFirstSearch(results, stringBuilder, str, wordDict, canBreak, 0)
        return results
    }

    private fun depthFirstSearch(
        results: MutableList<String>,
        stringBuilder: StringBuilder,
        inputString: String,
        wordDictionary: List<String>,
        canBreak: BooleanArray,
        startIndex: Int,
    ) {
        if (startIndex == inputString.length) {
            results.add(stringBuilder.substring(1))
            return
        }
        if (!canBreak[startIndex]) {
            return
        }
        for (i in startIndex + 1..inputString.length) {
            val word = inputString.substring(startIndex, i)
            if (!wordDictionary.contains(word)) {
                continue
            }
            val stringBuilderLengthBeforeAdd = stringBuilder.length
            stringBuilder.append(" $word")
            val resultsSizeBeforeDFS = results.size
            depthFirstSearch(results, stringBuilder, inputString, wordDictionary, canBreak, i)
            if (results.size == resultsSizeBeforeDFS) {
                canBreak[i] = false
            }
            stringBuilder.delete(stringBuilderLengthBeforeAdd, stringBuilder.length)
        }
    }
}

@DFS
class WordBreak2DFS : WordBreak2 {
    override operator fun invoke(str: String, wordDict: List<String>): List<String> {
        return depthFirstSearch(str, wordDict, HashMap())
    }

    // backtrack returns an array including all substrings derived from str.
    private fun depthFirstSearch(
        inputString: String,
        wordDictionary: List<String>,
        memoizationMap: MutableMap<String, List<String>>,
    ): List<String> {
        if (memoizationMap.containsKey(inputString)) return memoizationMap[inputString] ?: emptyList()
        val results: MutableList<String> = ArrayList()
        for (word in wordDictionary) if (inputString.startsWith(word)) {
            val remainingString = inputString.substring(word.length)
            if (remainingString.isEmpty()) {
                results.add(word)
            } else {
                for (substring in depthFirstSearch(remainingString, wordDictionary, memoizationMap)) {
                    results.add("$word $substring")
                }
            }
        }
        memoizationMap[inputString] = results
        return results
    }
}
