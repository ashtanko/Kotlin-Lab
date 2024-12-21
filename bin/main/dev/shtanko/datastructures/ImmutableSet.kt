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

package dev.shtanko.datastructures

import dev.shtanko.algorithms.search.BinarySearch

/**
 * An immutable set implementation that ensures sorted elements.
 *
 * The values are stored in a sorted array,
 * and the inclusion queries perform binary search, taking logarithmic time in the worst case.
 * @param T The type of elements in the set, must be Comparable.
 * @property values The array of values to be stored in the set.
 */
class ImmutableSet<T : Comparable<T>>(values: Array<T>) : Set<T> {

    private val arr: Array<T> = values.sortedArray()

    /**
     * Binary search instance for searching elements in the set.
     */
    private val bs = BinarySearch<T>()

    /**
     * Returns the size of the set.
     */
    override val size: Int
        get() = arr.size

    /**
     * Checks if the set contains the given element.
     *
     * @param element The element to check for.
     * @return `true` if the element is found, otherwise `false`.
     */
    override fun contains(element: T): Boolean {
        return bs.perform(arr, element) != -1
    }

    /**
     * Checks if the set contains all the elements in the specified collection.
     *
     * @param elements The collection of elements to check for.
     * @return `true` if all elements are found, otherwise `false`.
     */
    override fun containsAll(elements: Collection<T>): Boolean {
        return elements.all {
            contains(it)
        }
    }

    /**
     * Checks if the set is empty.
     *
     * @return `true` if the set is empty, otherwise `false`.
     */
    override fun isEmpty(): Boolean {
        return size == 0
    }

    /**
     * Returns an iterator over the elements of the set.
     *
     * @return An iterator over the elements of the set.
     */
    override fun iterator(): Iterator<T> {
        return arr.iterator()
    }
}

/**
 * Creates an immutable set with the specified elements.
 *
 * @param elements The elements to populate the set with.
 * @return An immutable set containing the specified elements.
 */
fun <T : Comparable<T>> immutableSetOf(vararg elements: T): ImmutableSet<out T> {
    return ImmutableSet(elements.copyOf())
}
