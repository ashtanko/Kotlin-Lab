/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.extensions.getNumberOfLetter

/**
 * 1880. Check if Word Equals Summation of Two Words
 * @see <a href="https://leetcode.com/problems/check-if-word-equals-summation-of-two-words/">Source</a>
 */
fun interface IsSumEqual {
    operator fun invoke(firstWord: String, secondWord: String, targetWord: String): Boolean
}

class IsSumEqualImpl : IsSumEqual {
    override operator fun invoke(firstWord: String, secondWord: String, targetWord: String): Boolean {
        return firstWord.getNumberOfLetter().plus(secondWord.getNumberOfLetter()) == targetWord.getNumberOfLetter()
    }
}
