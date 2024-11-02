/*
 * Copyright 2024 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.level.Easy

/**
 * 2490. Circular Sentence
 * @see <a href="https://leetcode.com/problems/circular-sentence/">Source</a>
 */
@Easy("https://leetcode.com/problems/circular-sentence")
fun interface CircularSentence {
    operator fun invoke(sentence: String): Boolean
}

class CircularSentenceBF : CircularSentence {
    override fun invoke(sentence: String): Boolean {
        val words = sentence.split(" ")
        val firstCondition = words.first().first() == words.last().last()
        val secondCondition = words.zipWithNext().all { it.first.last() == it.second.first() }
        return firstCondition && secondCondition
    }
}

class CircularSentenceSpaceOpt : CircularSentence {
    override fun invoke(sentence: String): Boolean {
        for (i in sentence.indices) {
            if (sentence[i] == ' ' && sentence[i - 1] != sentence[i + 1]) {
                return false
            }
        }
        return sentence.first() == sentence.last()
    }
}
