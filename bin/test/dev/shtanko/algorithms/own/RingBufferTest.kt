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

package dev.shtanko.algorithms.own

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RingBufferTest {

    private val ringBuffer: RingBuffer by lazy {
        RingBuffer()
    }

    @Test
    internal fun `first element test`() {
        val firstElement = ringBuffer.incrementAndGet()
        val expected = Byte.MIN_VALUE
        assertEquals(expected, firstElement)
    }

    @Test
    internal fun `second element test`() {
        ringBuffer.incrementAndGet()
        val secondElement: Byte = ringBuffer.incrementAndGet()
        val expected = (-127).toByte()
        assertEquals(expected, secondElement)
    }

    @Test
    internal fun `last element test`() {
        var lastElement: Byte = Byte.MIN_VALUE
        for (i in 0 until 256) {
            lastElement = ringBuffer.incrementAndGet()
        }
        val expected = Byte.MAX_VALUE
        assertEquals(expected, lastElement)
    }

    @Test
    internal fun `one lap test`() {
        var lastElement: Byte = Byte.MIN_VALUE
        for (i in 0 until 512) {
            lastElement = ringBuffer.incrementAndGet()
        }
        val expected = Byte.MAX_VALUE
        assertEquals(expected, lastElement)
    }
}
