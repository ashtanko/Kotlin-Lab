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

package dev.shtanko.jmm.memory

import dev.shtanko.jmm.obj.SomeClass

private const val LIMIT = 10000

/**
 * Just like in Java, improper handling of references can lead to memory leaks. Make sure to release resources
 * when they are no longer needed.
 */
fun createMemoryLeak() {
    val list = mutableListOf<SomeClass>()
    for (i in 1..LIMIT) {
        val obj = SomeClass()
        list.add(obj) // Keeping references to objects
    }
    // 'list' holds references to objects even after the loop, causing a memory leak
}
