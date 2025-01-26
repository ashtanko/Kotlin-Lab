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
 * The simple implementation of Circular Buffer
 * @see <a href="https://en.wikipedia.org/wiki/Circular_buffer">Circular_buffer</a>
 * <p>All methods of this class are non-blocking and not synchronized (not thread-safe).
 */
class RingBuffer {

    /**
     * position of first (oldest) data byte within the ring buffer
     * Note: non synchronized
     */
    private var bufferPosition = MIN_VALUE

    /**
     * Just use for prevent increments [bufferPosition] in first [incrementAndGet] method call
     */
    private var isFirstIteration = true

    /**
     * This function increments position of byte except for the first call
     * and when position of byte reach [MAX_VALUE] start over
     *
     * @return position of byte in range [MIN_VALUE] until [MAX_VALUE]
     */
    fun incrementAndGet(): Byte {
        if (bufferPosition == MAX_VALUE) {
            bufferPosition = MIN_VALUE
            isFirstIteration = true
        }
        if (!isFirstIteration) {
            increment()
        }
        isFirstIteration = false
        return bufferPosition
    }

    /**
     * Increments common [bufferPosition] value
     */
    private fun increment() {
        bufferPosition = bufferPosition.inc()
    }

    companion object {
        private const val MIN_VALUE = Byte.MIN_VALUE // -128
        private const val MAX_VALUE = Byte.MAX_VALUE // 127
    }
}
