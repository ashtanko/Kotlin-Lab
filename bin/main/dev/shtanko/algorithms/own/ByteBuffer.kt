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

class ByteBuffer(private val message: (bytes: ByteArray) -> Unit) {

    private var buffer: MutableMap<Byte, MutableList<Byte>> = hashMapOf()

    /**
     * Consume and store the chunks in [buffer]
     * Empty the [buffer] when chunk is last
     */
    fun execute(chunks: ByteArray) {
        val pair = Pair(chunks[0], chunks[1])

        if (buffer.containsKey(pair.second)) {
            buffer[pair.second]?.addAll(parseChunk(chunks))
            check(pair.second, pair.first)
        } else {
            buffer[pair.second] = parseChunk(chunks)
            check(pair.second, pair.first)
        }
    }

    /**
     * Check the chunk in the [buffer] is last
     * Call the [message] and remove chunks from [buffer]
     */
    private fun check(id: Byte, position: Byte) {
        if (position == 1.toByte()) {
            buffer[id]?.toByteArray()?.let {
                message.invoke(it)
            }
            buffer.remove(id)
        }
    }

    /**
     * Deletes two first bytes of chunk keeping the data plain
     */
    private fun parseChunk(chunk: ByteArray): MutableList<Byte> {
        return chunk.copyOfRange(2, chunk.size).toMutableList()
    }
}
