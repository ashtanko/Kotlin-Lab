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

package dev.shtanko.concurrency

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CounterTest {

    private lateinit var counter: Counter

    @BeforeEach
    fun setUp() {
        counter = Counter()
    }

    @Test
    fun incrementIncreasesValueByOne() {
        // Initial value is 0
        counter.inc()
        assertEquals(1, counter.get())
    }

    @Test
    fun addAndGetIncreasesValueByOneAndReturnsNewValue() {
        val newValue = counter.addAndGet()
        assertEquals(1, newValue)
        assertEquals(newValue, counter.get())
    }

    @Test
    fun getReturnsCurrentValue() {
        // Initial value is 0
        assertEquals(0, counter.get())
        counter.inc()
        // After increment
        assertEquals(1, counter.get())
    }

    @Test
    fun multipleIncrementsAccuratelyIncreaseValue() {
        for (i in 1..5) {
            counter.inc()
        }
        assertEquals(5, counter.get())
    }

    @Test
    fun incrementAndAddAndGetTogetherBehaveCorrectly() {
        counter.inc() // +1
        counter.addAndGet() // +1 and get
        assertEquals(2, counter.get())
    }
}
