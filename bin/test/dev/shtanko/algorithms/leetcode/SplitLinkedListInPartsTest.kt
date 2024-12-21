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

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class SplitLinkedListInPartsTest<out T : SplitLinkedListInParts>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(1, 2, 3).toNullableListNode(),
                5,
                arrayOf(
                    listOf(1).toNullableListNode(),
                    listOf(2).toNullableListNode(),
                    listOf(3).toNullableListNode(),
                    listOf<Int>().toNullableListNode(),
                    listOf<Int>().toNullableListNode(),
                ),
            ),
            Arguments.of(
                listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).toNullableListNode(),
                3,
                arrayOf(
                    listOf(1, 2, 3, 4).toNullableListNode(),
                    listOf(5, 6, 7).toNullableListNode(),
                    listOf(8, 9, 10).toNullableListNode(),
                ),
            ),
            Arguments.of(
                listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).toNullableListNode(),
                7,
                arrayOf(
                    listOf(1, 2).toNullableListNode(),
                    listOf(3, 4).toNullableListNode(),
                    listOf(5, 6).toNullableListNode(),
                    listOf(7).toNullableListNode(),
                    listOf(8).toNullableListNode(),
                    listOf(9).toNullableListNode(),
                    listOf(10).toNullableListNode(),
                ),
            ),
            Arguments.of(
                listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).toNullableListNode(),
                1,
                arrayOf(
                    listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).toNullableListNode(),
                ),
            ),
            Arguments.of(
                listOf<Int>().toNullableListNode(),
                3,
                arrayOf(
                    listOf<Int>().toNullableListNode(),
                    listOf<Int>().toNullableListNode(),
                    listOf<Int>().toNullableListNode(),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `split list to parts test`(head: NullableListNode?, k: Int, expected: Array<NullableListNode?>) {
        val actual = strategy(head, k).map { it.toListOrEmpty() }
        Assertions.assertThat(actual).isEqualTo(expected.map { it.toListOrEmpty() })
    }
}

class SplitLinkedListInPartsCopyTest : SplitLinkedListInPartsTest<SplitLinkedListInParts>(SplitLinkedListInPartsCopy())
class SplitLinkedListInPartsInputTest :
    SplitLinkedListInPartsTest<SplitLinkedListInParts>(SplitLinkedListInPartsInput())
