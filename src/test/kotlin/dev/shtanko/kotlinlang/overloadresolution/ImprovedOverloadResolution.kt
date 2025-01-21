/*
 * Copyright 2025 Oleksii Shtanko
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

package dev.shtanko.kotlinlang.overloadresolution

@Suppress("EmptyFunctionBlock", "UnusedParameter")
object ImprovedOverloadResolution {
    @JvmStatic
    fun main(args: Array<String>) {
        test(KeyValueStore())
    }

    class KeyValueStore<K, V> {
        fun store(key: K, value: V) {} // 1
        fun store(key: K, lazyValue: () -> V) {} // 2
    }

    fun <K, V> KeyValueStore<K, V>.storeExtension(key: K, value: V) {} // 1
    fun <K, V> KeyValueStore<K, V>.storeExtension(key: K, lazyValue: () -> V) {} // 2


    fun test(kvs: KeyValueStore<String, Int>) {
        // Member functions
        kvs.store("", 1)    // Resolves to 1
        kvs.store("") { 1 } // Resolves to 2

        // Extension functions
        kvs.storeExtension("", 1)    // Resolves to 1
        // kvs.storeExtension("") { 1 } // Doesn't resolve // todo uncomment
    }
}
