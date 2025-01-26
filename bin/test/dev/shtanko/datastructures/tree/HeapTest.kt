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

package dev.shtanko.datastructures.tree

import java.util.PriorityQueue
import java.util.Random
import java.util.stream.IntStream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class HeapTest {

    companion object {
        private val RANDOM = Random()
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 10, 100, 1000, 10000, 100000])
    internal fun `heap test`(size: Int) {
        val priorityQueue = PriorityQueue<Int>()
        val heap = Heap<Int>(naturalOrder())

        IntStream.generate { RANDOM.nextInt() }
            .limit(size.toLong())
            .forEach { priorityQueue.add(it); heap.add(it) }

        assertEquals(size, heap.size())
        for (i in 1..size) {
            assertEquals(priorityQueue.peek(), heap.peek())
            assertEquals(priorityQueue.poll(), heap.poll())
        }
    }
}
