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

package dev.shtanko.patterns.structural.decorator.example2

import dev.shtanko.patterns.structural.decorator.examples.example2.DistinctStream
import dev.shtanko.patterns.structural.decorator.examples.example2.SimpleStream
import dev.shtanko.patterns.structural.decorator.examples.example2.StreamDecorator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StreamDecoratorTest {
    @Test
    fun testSimpleStream() {
        val data = listOf(1, 2, 3, 4, 5)
        val simpleStream: StreamDecorator<Int> = SimpleStream(data)
        Assertions.assertEquals(data, simpleStream.collect())
    }

    @Test
    fun testFilteredStream() {
        val data = listOf(1, 2, 3, 4, 5)
        val simpleStream: StreamDecorator<Int> = SimpleStream(data)
        val filteredStream: StreamDecorator<Int> = simpleStream.filter { it > 2 }
        Assertions.assertEquals(listOf(3, 4, 5), filteredStream.collect())
    }

    @Test
    fun testDistinctStream() {
        val data = listOf(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
        val simpleStream: StreamDecorator<Int> = SimpleStream(data)
        val distinctStream: StreamDecorator<Int> = DistinctStream(simpleStream)
        Assertions.assertEquals(listOf(1, 2, 3, 4, 5), distinctStream.collect())
    }

    @Test
    fun testFilteredAndDistinctStream() {
        val data = listOf(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
        val simpleStream: StreamDecorator<Int> = SimpleStream(data)
        val filteredStream: StreamDecorator<Int> = simpleStream.filter { it > 2 }
        val distinctStream: StreamDecorator<Int> = DistinctStream(filteredStream)
        Assertions.assertEquals(listOf(3, 4, 5), distinctStream.collect())
    }
}
