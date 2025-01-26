/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.utils

import dev.shtanko.algorithms.sorts.isSorted
import kotlin.random.Random
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MeasureTimeTest {

    @Test
    fun `measure time test`() {
        measureTime(
            {
                getLongRunningTask()
            },
        ) {
            assertThat(it.second.toTypedArray().isSorted()).isTrue()
        }
    }

    @Test
    fun `measure time test with print message`() {
        val (_, actual) = measureTime(isPrint = true) {
            getLongRunningTask()
        }
        assertThat(actual.toTypedArray().isSorted()).isTrue
    }

    @Test
    fun `measure time test without print message`() {
        val (time, actual) = measureTime {
            getLongRunningTask()
        }
        println(time)
        assertThat(actual.toTypedArray().isSorted()).isTrue
    }

    private fun getLongRunningTask(): List<Int> {
        return (1..1000_000).map {
            var r = 0
            repeat(3) {
                r += Random.nextInt(100_000)
            }
            r
        }.sorted()
    }
}
