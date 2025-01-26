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

/**
 * Kotlin's standard library provides functions like let, apply, also, and run to help avoid unnecessary object
 * creation and promote more concise code.
 */
@Suppress("unused", "UNUSED_VARIABLE")
fun avoidUnnecessaryObjectCreation(someValue: SomeClass?) {
    val result = someValue?.let { transformedValue ->
        // Use 'transformedValue'
        "Result: $transformedValue"
    } ?: "Default Result"
}
