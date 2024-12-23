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

package dev.shtanko.datastructures.maps.hashmaps

@Suppress("MagicNumber")
data object HashMapExamples {
    @JvmStatic
    fun main(args: Array<String>) {
        val immutableMap = mapOf("one" to 1, "two" to 2, "three" to 3)
        println(immutableMap["one"]) // Output: 1

        val emptyImmutableMap = emptyMap<String, Int>()
        println(emptyImmutableMap.isEmpty()) // Output: true

        val mutableMap = mutableMapOf("one" to 1, "two" to 2)
        mutableMap["three"] = 3 // Add new key-value pair
        mutableMap["two"] = 22 // Update an existing value
        println(mutableMap) // Output: {one=1, two=22, three=3}

        val hashMap = hashMapOf("apple" to 1, "banana" to 2)
        hashMap["cherry"] = 3
        println(hashMap) // Output: {apple=1, banana=2, cherry=3}
    }
}
