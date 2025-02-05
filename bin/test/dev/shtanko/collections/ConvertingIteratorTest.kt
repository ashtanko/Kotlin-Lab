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

package dev.shtanko.collections

import java.util.stream.Collectors
import java.util.stream.StreamSupport
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

internal class ConvertingIteratorTest {

    @Test
    internal fun `using a while loop test`() {
        val iterator = listOf(1, 2, 3).iterator()
        val actualList: MutableList<Int> = ArrayList()
        while (iterator.hasNext()) {
            actualList.add(iterator.next())
        }
        assertThat(actualList, containsInAnyOrder(1, 2, 3))
    }

    @Test
    internal fun `using Java 8 iterator forEachRemaining test`() {
        val iterator = listOf(1, 2, 3).iterator()
        val actualList: MutableList<Int> = ArrayList()
        iterator.forEachRemaining(actualList::add)
        assertThat(actualList, containsInAnyOrder(1, 2, 3))
    }

    @Test
    internal fun `using the Java 8 Streams API test`() {
        val iterator = listOf(1, 2, 3).iterator()
        val iterable = Iterable { iterator }
        val actualList = StreamSupport
            .stream(iterable.spliterator(), false)
            .collect(Collectors.toList())
        assertThat(actualList, containsInAnyOrder(1, 2, 3))
    }

    @Test
    internal fun `using the List iterator test`() {
        val numbers = listOf("one", "two", "three", "four")
        val actualList: MutableList<String> = ArrayList()
        val listIterator = numbers.listIterator()
        while (listIterator.hasNext()) listIterator.next()
        while (listIterator.hasPrevious()) {
            actualList.add(listIterator.previous())
        }
        assertThat(actualList, equalTo(listOf("four", "three", "two", "one")))
    }

    @Test
    internal fun `using Mutable iterators test`() {
        val numbers = mutableListOf("one", "four", "four")
        val mutableListIterator = numbers.listIterator()
        mutableListIterator.next()
        mutableListIterator.add("two")
        mutableListIterator.next()
        mutableListIterator.set("three")
        assertThat(numbers, equalTo(listOf("one", "two", "three", "four")))
    }
}
