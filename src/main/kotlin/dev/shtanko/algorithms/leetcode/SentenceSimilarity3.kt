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

import java.util.Deque

/**
 * 1813. Sentence Similarity III
 * @see <a href="https://leetcode.com/problems/sentence-similarity-iii/">Source</a>
 */
fun interface SentenceSimilarity3 {
    operator fun invoke(sentence1: String, sentence2: String): Boolean
}

class SentenceSimilarity3Deque : SentenceSimilarity3 {
    override fun invoke(sentence1: String, sentence2: String): Boolean {
        val deque1: Deque<String> = java.util.ArrayDeque(sentence1.split(" "))
        val deque2: Deque<String> = java.util.ArrayDeque(sentence2.split(" "))

        // Compare the prefixes or beginning of the strings.
        while (deque1.isNotEmpty() && deque2.isNotEmpty() && deque1.peek() == deque2.peek()) {
            deque1.poll()
            deque2.poll()
        }

        // Compare the suffixes or ending of the strings.
        while (deque1.isNotEmpty() && deque2.isNotEmpty() && deque1.peekLast() == deque2.peekLast()) {
            deque1.pollLast()
            deque2.pollLast()
        }

        return deque1.isEmpty() || deque2.isEmpty()
    }
}
