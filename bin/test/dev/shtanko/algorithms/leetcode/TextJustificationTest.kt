/*
 * Copyright 2021 Oleksii Shtanko
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

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class TextJustificationTest<out T : TextJustification>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf<String>(),
                1,
                listOf<String>(),
            ),
            Arguments.of(
                arrayOf(
                    "",
                ),
                1,
                listOf(
                    " ",
                ),
            ),
            Arguments.of(
                arrayOf(
                    "q",
                    "q",
                ),
                5,
                listOf(
                    "q q  ",
                ),
            ),
            Arguments.of(
                arrayOf("This", "is", "an", "example", "of", "text", "justification."),
                16,
                listOf(
                    "This    is    an",
                    "example  of text",
                    "justification.  ",
                ),
            ),
            Arguments.of(
                arrayOf("What", "must", "be", "acknowledgment", "shall", "be"),
                16,
                listOf(
                    "What   must   be",
                    "acknowledgment  ",
                    "shall be        ",
                ),
            ),
            Arguments.of(
                arrayOf(
                    "Science",
                    "is",
                    "what",
                    "we",
                    "understand",
                    "well",
                    "enough",
                    "to",
                    "explain",
                    "to",
                    "a",
                    "computer.",
                    "Art",
                    "is",
                    "everything",
                    "else",
                    "we",
                    "do",
                ),
                20,
                listOf(
                    "Science  is  what we",
                    "understand      well",
                    "enough to explain to",
                    "a  computer.  Art is",
                    "everything  else  we",
                    "do                  ",
                ),
            ),
            Arguments.of(
                arrayOf(
                    "Lorem",
                    "Ipsum",
                    "is",
                    "simply",
                    "dummy",
                    "text",
                    "of",
                    "the",
                    "printing",
                    "and",
                    "typesetting",
                    "industry.",
                    "Lorem",
                    "Ipsum",
                    "has",
                    "been",
                    "the",
                    "industry's",
                    "standard",
                    "dummy",
                    "text",
                    "ever",
                    "since",
                    "the",
                    "1500s",
                    "when",
                    "an",
                    "unknown",
                    "printer",
                    "took",
                    "a",
                    "galley",
                    "of",
                    "type",
                    "and",
                    "scrambled",
                    "it",
                    "to",
                    "make",
                    "a",
                    "type",
                    "specimen",
                    "book.",
                    "It",
                    "has",
                    "survived",
                    "not",
                    "only",
                    "five",
                    "centuries",
                    "but",
                    "also",
                    "the",
                    "leap",
                    "into",
                    "electronic",
                    "typesetting,",
                    "remaining",
                    "essentially",
                    "unchanged.",
                    "It",
                    "was",
                    "popularised",
                    "in",
                    "the",
                    "1960s",
                    "with",
                    "the",
                    "release",
                    "of",
                    "Letraset",
                    "sheets",
                    "containing",
                    "Lorem",
                    "Ipsum",
                    "passages,",
                    "and",
                    "more",
                    "recently",
                    "with",
                    "desktop",
                    "publishing",
                    "software",
                    "like",
                    "Aldus",
                    "PageMaker",
                    "including",
                    "versions",
                    "of",
                    "Lorem",
                    "Ipsum .",
                ),
                57,
                listOf(
                    "Lorem  Ipsum  is  simply  dummy  text of the printing and",
                    "typesetting industry. Lorem Ipsum has been the industry's",
                    "standard  dummy text ever since the 1500s when an unknown",
                    "printer  took a galley of type and scrambled it to make a",
                    "type  specimen  book.  It  has  survived  not  only  five",
                    "centuries  but also the leap into electronic typesetting,",
                    "remaining  essentially  unchanged.  It was popularised in",
                    "the  1960s with the release of Letraset sheets containing",
                    "Lorem  Ipsum  passages,  and  more  recently with desktop",
                    "publishing   software   like  Aldus  PageMaker  including",
                    "versions of Lorem Ipsum .                                ",
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `full text justify test`(words: Array<String>, maxWidth: Int, expected: List<String>) {
        val actual = strategy.invoke(words, maxWidth)
        assertThat(actual).containsAll(expected)
    }
}

class TextJustificationImplTest : TextJustificationTest<TextJustificationImpl>(TextJustificationImpl())
class TextJustificationImpl2Test : TextJustificationTest<TextJustificationImpl2>(TextJustificationImpl2())
