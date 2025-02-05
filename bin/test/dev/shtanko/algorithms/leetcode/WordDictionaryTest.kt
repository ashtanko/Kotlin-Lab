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

package dev.shtanko.algorithms.leetcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

abstract class WordDictionaryTest<out T : WordDictionary>(private val strategy: T) {

    @Test
    fun `word Dictionary test`() {
        strategy.addWord("bad")
        strategy.addWord("dad")
        strategy.addWord("mad")
        assertThat(strategy.search("pad")).isFalse
        assertThat(strategy.search("bad")).isTrue
        assertThat(strategy.search(".ad")).isTrue
        assertThat(strategy.search("b..")).isTrue
    }
}

class WordDictionaryImplTest : WordDictionaryTest<WordDictionary>(WordDictionaryImpl())
