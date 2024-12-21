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

abstract class BinaryNumberToIntTest<out T : BinaryNumberToIntStrategy>(
    private val strategy: T,
) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                ListNode(1).apply {
                    next = ListNode(0).apply {
                        next = ListNode(1)
                    }
                },
                5,
            ),
            Arguments.of(
                ListNode(0),
                0,
            ),
            Arguments.of(
                ListNode(1),
                1,
            ),
            Arguments.of(
                ListNode(0).apply {
                    next = ListNode(0)
                },
                0,
            ),
            Arguments.of(
                getNode(),
                18880,
            ),
        )

        private fun getNode(): ListNode {
            var node = ListNode(0)
            val arr = listOf(1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0)
            for (value in arr.reversed()) {
                val newNode = ListNode(value)
                newNode.next = node
                node = newNode
            }
            node.prettyPrint()
            return node
        }
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `convert binary number in a linked list to integer test`(head: ListNode, expected: Int) {
        val actual = strategy.invoke(head)
        assertEquals(expected, actual)
    }
}

class BinaryNumberToIntBinaryTest : BinaryNumberToIntTest<BinaryNumberToIntBinary>(BinaryNumberToIntBinary())
class BinaryNumberToIntBitTest : BinaryNumberToIntTest<BinaryNumberToIntBit>(BinaryNumberToIntBit())
