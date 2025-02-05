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

import dev.shtanko.algorithms.ALPHABET_LETTERS_COUNT
import kotlin.math.max
import kotlin.math.min

/**
 * 691. Stickers to Spell Word
 * @see <a href="https://leetcode.com/problems/stickers-to-spell-word/">Source</a>
 */
fun interface StickersToSpellWord {
    operator fun invoke(stickers: Array<String>, target: String): Int
}

/**
 * DP + Memoization with optimization
 */
class StickersToSpellWordDP : StickersToSpellWord {

    /**
     * This function calculates the minimum number of stickers required to spell the target.
     * It uses a dynamic programming approach.
     * @param stickers The array of available stickers.
     * @param target The target string to spell.
     * @return The minimum number of stickers required to spell the target.
     */
    override fun invoke(stickers: Array<String>, target: String): Int {
        val stickerCount = stickers.size
        val stickerChars = Array(stickerCount) { IntArray(ALPHABET_LETTERS_COUNT) }
        val dp: MutableMap<String, Int> = HashMap()
        stickers.forEachIndexed { i, sticker -> sticker.forEach { char -> stickerChars[i][char - 'a']++ } }
        dp[""] = 0
        return calculateMinimumStickers(dp, stickerChars, target)
    }

    /**
     * This function calculates the minimum number of stickers required to spell the target for a given state of the
     * problem.
     * It uses a dynamic programming approach.
     * @param dp The dynamic programming table.
     * @param stickerChars The character counts of the available stickers.
     * @param target The target string to spell.
     * @return The minimum number of stickers required to spell the target for the given state of the problem.
     */
    private fun calculateMinimumStickers(
        dp: MutableMap<String, Int>,
        stickerChars: Array<IntArray>,
        target: String,
    ): Int {
        dp[target]?.let { return it }
        var minStickers = Int.MAX_VALUE
        val targetChars = IntArray(ALPHABET_LETTERS_COUNT)
        target.forEach { char -> targetChars[char - 'a']++ }
        stickerChars.indices.filter { stickerChars[it][target[0] - 'a'] > 0 }.forEach { i ->
            val remainingTarget = StringBuilder()
            targetChars.indices.forEach { j ->
                if (targetChars[j] > 0) {
                    repeat(max(0, targetChars[j] - stickerChars[i][j])) {
                        remainingTarget.append(('a'.code + j).toChar())
                    }
                }
            }
            calculateMinimumStickers(dp, stickerChars, remainingTarget.toString()).let { remainingStickers ->
                if (remainingStickers != -1) minStickers = min(minStickers, 1 + remainingStickers)
            }
        }
        dp[target] = if (minStickers == Int.MAX_VALUE) -1 else minStickers
        return dp[target] ?: -1
    }
}
