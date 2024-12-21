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

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class TagValidatorTest<out T : TagValidatorStrategy>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of("<DIV>This is the first line <![CDATA[<div>]]></DIV>", true),
            Arguments.of("<DIV>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>", true),
            Arguments.of("<A>  <B> </A>   </B>", false),
            Arguments.of("<DIV>  div tag is not closed  <DIV>", false),
            Arguments.of("<DIV>  unmatched <  </DIV>", false),
            Arguments.of("<DIV> closed tags with invalid tag name  <b>123</b> </DIV>", false),
            Arguments.of("<DIV> unmatched tags with invalid tag name  </1234567890> and <CDATA[[]]>  </DIV>", false),
            Arguments.of("<DIV>  unmatched start tag <B>  and unmatched end tag </C>  </DIV>", false),
            Arguments.of("<![CDATA[wahaha]]]><![CDATA[]> wahaha]]>", false),
            Arguments.of("<A><A></A></A>", true),
            Arguments.of("<A></A><B></B>", false),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `tag validator test`(str: String, expected: Boolean) {
        val actual = strategy.invoke(str)
        assertEquals(expected, actual)
    }
}

class TagValidatorStackTest : TagValidatorTest<TagValidatorStack>(TagValidatorStack())

class TagValidatorRegexTest : TagValidatorTest<TagValidatorRegex>(TagValidatorRegex())
