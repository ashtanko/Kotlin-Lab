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

package dev.shtanko.jmm.obj

class SomeClass

private const val LIMIT = 10000

@Suppress("unused", "UNUSED_VARIABLE")
fun createObjects() {
    for (i in 1..LIMIT) {
        val obj = SomeClass() // Object created
        // obj goes out of scope after each iteration
    }
    // At this point, objects created in the loop are eligible for garbage collection
}
