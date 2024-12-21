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
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class DoublyListNodeTest {

    private class ReverseListArgs : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(1, 2, 3, 4, 5, 6, 7).toListNode(),
                listOf(1, 2, 3, 4, 5, 6, 7).reversed(),
            ),
            Arguments.of(
                listOf(1).toListNode(),
                listOf(1).reversed(),
            ),
            Arguments.of(
                listOf(1, 2).toListNode(),
                listOf(1, 2).reversed(),
            ),
            Arguments.of(
                listOf<Int>().toListNode(),
                listOf<Int>().reversed(),
            ),
            Arguments.of(
                listOf(-1, -2).toListNode(),
                listOf(-1, -2).reversed(),
            ),
        )
    }

    @ArgumentsSource(ReverseListArgs::class)
    fun `reverse doubly linked list`() {
        // TODO
    }
}
