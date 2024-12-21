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

abstract class FindDuplicateTest<out T : FindDuplicate>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    "root/a 1.txt(abcd) 2.txt(efgh)",
                    "root/c 3.txt(abcd)",
                    "root/c/d 4.txt(efgh)",
                    "root 4.txt(efgh)",
                ),
                listOf(
                    listOf("root/a/2.txt", "root/c/d/4.txt", "root/4.txt"),
                    listOf("root/a/1.txt", "root/c/3.txt"),
                ),
            ),
            Arguments.of(
                arrayOf("root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)"),
                listOf(
                    listOf("root/a/2.txt", "root/c/d/4.txt"),
                    listOf("root/a/1.txt", "root/c/3.txt"),
                ),
            ),
            Arguments.of(
                arrayOf("root/a 1.txt(abcd)", "root/c 3.txt(abcd)"),
                listOf(
                    listOf("root/a/1.txt"),
                    listOf("root/c/3.txt"),
                ),
            ),
            Arguments.of(
                arrayOf("root/a 1.txt(abcd)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)"),
                listOf(
                    listOf("root/a/1.txt"),
                    listOf("root/c/3.txt"),
                ),
            ),
            Arguments.of(
                arrayOf<String>(),
                listOf<String>(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find duplicate test`(paths: Array<String>, expected: List<List<String>>) {
        val actual = strategy.invoke(paths).flatten().sorted()
        assertThat(actual).containsAll(expected.flatten().sorted())
    }
}

class FindDuplicateBruteForceTest : FindDuplicateTest<FindDuplicateBruteForce>(FindDuplicateBruteForce())
class FindDuplicateHashMapTest : FindDuplicateTest<FindDuplicateHashMap>(FindDuplicateHashMap())
