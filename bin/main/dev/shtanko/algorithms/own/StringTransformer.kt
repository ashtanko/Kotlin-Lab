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

/**
 * Transforms [String] to bytes array(chunks) and marks every
 * chunk with same unique id using [RingBuffer]
 */
class StringTransformer(private val ringBuffer: RingBuffer) {

    /**
     * This function separates input string to chunks if the string length more than [CHUNK_SIZE]
     * Marks the first two bytes every chunk and gather them
     *
     * @param string input value
     * @return @List of chunks
     *
     *
     *      the first chunk    the last chunk
     *
     *         message id           [CHUNK_SIZE]
     *             ↓                     ↓
     *       [0, -128, n]  ... [1, -128, n]
     *        ^                 ^
     *        |                 |
     *      start             last
     *
     * For example:
     * The first chunk:
     * [0, id, n1…n[CHUNK_SIZE]]
     * The second chunk:
     * [0, id, n1…n[CHUNK_SIZE]]
     * The last one:
     * [1, id, n1…n[CHUNK_SIZE]]
     *
     * returns:
     * [[0, id, n1…n[CHUNK_SIZE]], [0, id, n1…n[CHUNK_SIZE]], [1, id, n1…n[CHUNK_SIZE]]]
     *
     */
    fun transform(string: String): List<ByteArray> {
        val id = ringBuffer.incrementAndGet()
        val bytes = string.toByteArray()
        val newArray: MutableList<ByteArray> = mutableListOf()

        val chunks = bytes.toList().chunked(CHUNK_SIZE)

        for (i in chunks.indices) {
            val chunk = chunks[i].toByteArray()
            val chunkList = mutableListOf<Byte>()
            if (i == chunks.size - 1) {
                chunkList.add(0, id)
                chunkList.add(0, LAST_ELEMENT_MARK)
                chunk.forEach {
                    chunkList.add(it)
                }
            } else {
                chunkList.add(0, id)
                chunkList.add(0, ELEMENT_MARK)
                chunk.forEach {
                    chunkList.add(it)
                }
            }

            newArray.add(chunkList.toByteArray())
        }
        return newArray
    }

    companion object {
        /**
         * The chunk size
         */
        private const val CHUNK_SIZE = 18

        /**
         * First byte marker of all chunks until the last chunk
         * [[ELEMENT_MARK], id, n1, n2 ... n[CHUNK_SIZE]]
         */
        private const val ELEMENT_MARK = 0.toByte()

        /**
         * First byte marker of the last chunk
         * [[LAST_ELEMENT_MARK], id, n1, n2 ... n[CHUNK_SIZE]]
         */
        private const val LAST_ELEMENT_MARK = 1.toByte()
    }
}
