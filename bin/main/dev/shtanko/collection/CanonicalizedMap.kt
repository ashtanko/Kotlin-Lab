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

package dev.shtanko.collection

import java.util.AbstractMap

/**
 * A map implementation that allows for keys to be canonicalized, or standardized, before being used.
 * This can be useful in situations where keys may come in different formats but represent the same thing.
 *
 * @param C The type of the canonicalized key.
 * @param K The type of the original key.
 * @param V The type of the value.
 * @param canonicalize A function that transforms a key of type K into a canonicalized key of type C.
 * @param isValidKey An optional function that checks if a key of type K is valid. If not provided, all keys are
 * considered valid.
 */
class CanonicalizedMap<C, K, V>(
    private val canonicalize: (K) -> C,
    private val isValidKey: ((K) -> Boolean)? = null,
) : MutableMap<K, V> {

    // The underlying map that stores the canonicalized keys and their associated values.
    private val base = mutableMapOf<C, Map.Entry<K, V>>()

    /**
     * Constructs a new CanonicalizedMap with the same mappings as the specified map.
     */
    private constructor(canonicalize: (K) -> C, isValidKey: ((K) -> Boolean)?, base: Map<C, Map.Entry<K, V>>) : this(
        canonicalize,
        isValidKey,
    ) {
        this.base.putAll(base)
    }

    /**
     * Returns a shallow copy of this CanonicalizedMap instance: the keys and values themselves are not cloned.
     */
    fun copy(): CanonicalizedMap<C, K, V> = CanonicalizedMap(canonicalize, isValidKey, base)

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     */
    override fun get(key: K): V? {
        if (!checkValidKey(key)) return null
        return base[canonicalize(key)]?.value
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     */
    override fun put(key: K, value: V): V? {
        if (!checkValidKey(key)) return null
        return base.put(canonicalize(key), AbstractMap.SimpleEntry(key, value))?.value
    }

    /**
     * Copies all the mappings from the specified map to this map.
     */
    override fun putAll(from: Map<out K, V>) {
        from.forEach { (key, value) -> this[key] = value }
    }

    /**
     * Removes all the mappings from this map.
     */
    override fun clear() {
        base.clear()
    }

    /**
     * Returns true if this map contains no key-value mappings.
     */
    override fun isEmpty() = base.isEmpty()

    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    override fun containsKey(key: K): Boolean {
        if (!checkValidKey(key)) return false
        return base.containsKey(canonicalize(key))
    }

    /**
     * Returns true if this map maps one or more keys to the specified value.
     */
    override fun containsValue(value: V): Boolean = base.values.any { it.value == value }

    /**
     * Returns a Set view of the mappings contained in this map.
     */
    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = base.values.map { it as MutableMap.MutableEntry<K, V> }.toMutableSet()

    /**
     * Removes the mapping for a key from this map if it is present.
     */
    override fun remove(key: K): V? {
        if (!checkValidKey(key)) return null
        return base.remove(canonicalize(key))?.value
    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    override val keys: MutableSet<K>
        get() = base.values.map { it.key }.toMutableSet()

    /**
     * Returns the number of key-value mappings in this map.
     */
    override val size: Int
        get() = base.size

    /**
     * Returns a Collection view of the values contained in this map.
     */
    override val values: MutableCollection<V>
        get() = base.values.map { it.value }.toMutableList()

    /**
     * Checks if the key is valid.
     */
    private fun checkValidKey(key: K): Boolean = key != null && (isValidKey == null || isValidKey.invoke(key))

    /**
     * Returns a new Map with the original keys and their associated values.
     */
    fun toMap(): Map<K, V> = base.values.associateBy({ it.key }, { it.value })

    /**
     * Returns a new Map with the canonicalized keys and their associated values.
     */
    fun toMapOfCanonicalKeys(): Map<C, V> = base.entries.associateBy({ it.key }, { it.value.value })
}

/**
 * Extension function to convert a Map<K, V> to a CanonicalizedMap<C, K, V>.
 *
 * @param canonicalize A function that transforms a key of type K into a canonicalized key of type C.
 * @param isValidKey An optional function that checks if a key of type K is valid. If not provided, all keys are
 * considered valid.
 * @return A new CanonicalizedMap instance.
 */
fun <C, K, V> Map<K, V>.toCanonicalizedMap(
    canonicalize: (K) -> C,
    isValidKey: ((K) -> Boolean)? = null,
): CanonicalizedMap<C, K, V> {
    val canonicalizedMap = CanonicalizedMap<C, K, V>(canonicalize, isValidKey)
    this.forEach { (key, value) -> canonicalizedMap[key] = value }
    return canonicalizedMap
}
