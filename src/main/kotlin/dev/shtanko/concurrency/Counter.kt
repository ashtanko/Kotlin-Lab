/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.concurrency

import java.util.concurrent.atomic.AtomicInteger

/**
 * A simple counter that can be incremented and read.
 * This class is thread-safe.
 */
class Counter {
    private var value: AtomicInteger = AtomicInteger(0)

    /**
     * Increment the counter by one.
     * This operation is atomic.
     *
     * @return the new value of the counter
     */
    fun inc(): Int = value.getAndIncrement()

    /**
     * Add one to the counter and return the new value.
     * This operation is atomic.
     *
     * @return the new value of the counter
     */
    fun addAndGet(): Int {
        return value.incrementAndGet()
    }

    /**
     * Get the current value of the counter.
     * This operation is atomic.
     *
     * @return the current value of the counter
     */
    fun get(): Int = value.get()
}
