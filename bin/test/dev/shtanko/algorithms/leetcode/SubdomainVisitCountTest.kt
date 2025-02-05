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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class SubdomainVisitCountTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf("9001 discuss.leetcode.com"),
                listOf(
                    "9001 com",
                    "9001 leetcode.com",
                    "9001 discuss.leetcode.com",
                ),
            ),
            Arguments.of(
                arrayOf("900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"),
                listOf(
                    "951 com",
                    "900 google.mail.com",
                    "1 intel.mail.com",
                    "5 org",
                    "5 wiki.org",
                    "901 mail.com",
                    "50 yahoo.com",
                ),
            ),
            Arguments.of(
                arrayOf("1 a", "1 b", "1 c"),
                listOf(
                    "1 a",
                    "1 b",
                    "1 c",
                ),
            ),
            Arguments.of(
                arrayOf("1 a", "1 b", "1 c", "1 a"),
                listOf(
                    "2 a",
                    "1 b",
                    "1 c",
                ),
            ),
            Arguments.of(
                arrayOf<String>(),
                listOf<String>(),
            ),
            Arguments.of(
                arrayOf("1 a"),
                listOf(
                    "1 a",
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `subdomain visits test`(cpDomains: Array<String>, expected: List<String>) {
        val actual = cpDomains.subdomainVisits()
        assertEquals(expected, actual)
        assertThat(actual).isEqualTo(expected)
    }
}
