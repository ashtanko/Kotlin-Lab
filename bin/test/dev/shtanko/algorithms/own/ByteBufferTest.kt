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

internal class ByteBufferTest {

    private val ringBuffer: RingBuffer by lazy {
        RingBuffer()
    }
    private val transformer: StringTransformer by lazy {
        StringTransformer(ringBuffer)
    }

    @Test
    internal fun `one chunked sting test`() {
        val string = "{\"ev\":\"one\"}"
        val byteBuffer = ByteBuffer { message ->
            val actual = String(message)
            assertEquals(actual, string)
        }
        val chunks = transformer.transform(string)
        chunks.forEach { chunk ->
            byteBuffer.execute(chunk)
        }
    }

    @Test
    internal fun `one full chunk and half test`() {
        val string = "{\"ev\":\"Lorem Ipsum\"}"
        val chunks = transformer.transform(string)

        val byteBuffer = ByteBuffer { message ->
            val actual = String(message)
            assertEquals(actual, string)
        }

        chunks.forEach { chunk ->
            byteBuffer.execute(chunk)
        }
    }

    @Test
    internal fun `big string test`() {
        val string =
            "{\"ev\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"}"
        val chunks = transformer.transform(string)

        val byteBuffer = ByteBuffer { message ->
            val actual = String(message)
            assertEquals(actual, string)
        }

        chunks.forEach { chunk ->
            byteBuffer.execute(chunk)
        }
    }

    @Test
    internal fun `different strings test`() {
        val one = "{\"ev\":\"first event\"}"
        val two = "{\"ev\":\"second event\"}"
        val three = "{\"ev\":\"third event\"}"

        val result = mutableListOf<String>()

        val byteBuffer = ByteBuffer { message ->
            val actual = String(message)
            result.add(actual)
        }

        val list = transformer.transform(one).plus(transformer.transform(two)).plus(transformer.transform(three))
        list.forEach { chunk ->
            byteBuffer.execute(chunk)
        }

        val expected = listOf(one, two, three)

        assertEquals(expected, result)
    }

    @Test
    internal fun `combine events test`() {
        val expected = listOf(
            "{\"ev\":\"one big event lorem lorem\"}",
            "{\"ev\":\"You can edit, run, and share this code.\"}",
        )

        val actual = mutableListOf<String>()

        val byteBuffer = ByteBuffer { message ->
            val str = String(message)
            actual.add(str)
        }

        getEvents().forEach {
            byteBuffer.execute(it)
        }

        assertEquals(expected, actual)
    }

    private fun getEvents(): List<ByteArray> {
        return listOf(
            byteArrayOf(0, -128, 123, 34),
            byteArrayOf(0, -128, 101, 118, 34),
            byteArrayOf(0, -128, 58, 34, 89, 111, 117),
            byteArrayOf(0, -127, 123, 34, 101, 118, 34),
            byteArrayOf(0, -128, 32, 99, 97, 110, 32, 101, 100),
            byteArrayOf(0, -128, 105, 116, 44, 32),
            byteArrayOf(0, -128, 114, 117, 110, 44),
            byteArrayOf(0, -127, 58, 34, 111, 110, 101, 32, 98, 105, 103),
            byteArrayOf(0, -128, 32, 97),
            byteArrayOf(0, -128, 110, 100),
            byteArrayOf(0, -127, 32, 101, 118, 101, 110, 116, 32, 108, 111, 114),
            byteArrayOf(0, -128, 32),
            byteArrayOf(0, -128, 115, 104, 97),
            byteArrayOf(0, -127, 101, 109, 32, 108),
            byteArrayOf(0, -128, 114, 101, 32, 116),
            byteArrayOf(0, -128, 104, 105, 115, 32),
            byteArrayOf(1, -127, 111, 114, 101, 109, 34, 125),
            byteArrayOf(0, -128, 99, 111, 100, 101, 46, 34),
            byteArrayOf(1, -128, 125),
        )
    }
}
