/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.concurrency

import java.lang.reflect.Field
import sun.misc.Unsafe

class MyAtomicInteger(initialValue: Int) {
    // Volatile Field: Uses a @Volatile field to ensure visibility across threads.
    @Volatile
    private var value = initialValue

    // Unsafe Class: Uses the Unsafe class to perform low-level operations.
    private val unsafe: Unsafe
    private val valueOffset: Long

    init {
        val theUnsafeField: Field = Unsafe::class.java.getDeclaredField("theUnsafe")
        theUnsafeField.isAccessible = true
        unsafe = theUnsafeField.get(null) as Unsafe
        valueOffset = unsafe.objectFieldOffset(MyAtomicInteger::class.java.getDeclaredField("value"))
    }

    fun incrementAndGet(): Int {
        while (true) {
            val current = value
            val next = current + 1
            // CAS Loop: Uses a CAS loop to atomically increment the value.
            // The compareAndSwapInt method ensures that the value is only updated if it matches the expected current
            // value, retrying if another thread has updated it in the meantime.
            if (unsafe.compareAndSwapInt(this, valueOffset, current, next)) {
                return next
            }
        }
    }

    fun get(): Int {
        return value
    }
}
