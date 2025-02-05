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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MeasureMemTest {

    @Test
    fun `measure mem test`() {
        val (bytes, actual) = measureMemWithResult {
            allocateMemory()
        }
        val msg = "Consumed: ${bytes.toHumanReadableByteCountBin()}"
        println(msg)
        Assertions.assertTrue(actual.flatten().sorted().toTypedArray().isSorted(), msg)
    }

    @Test
    fun `measure memory formatted test`() {
        val (msg, result) = measureMemFormatted {
            allocateMemory()
        }
        val actual = result.flatten().sorted().toTypedArray().isSorted()
        println(msg)
        assertThat(actual).isTrue
    }

    @Test
    fun `measure memory formatted with task test`() {
        val (msg, result) = measureMemFormatted(taskName = "test-task-1") {
            allocateMemory()
        }
        val actual = result.flatten().sorted().toTypedArray().isSorted()
        println(msg)
        assertThat(actual).isTrue
    }

    private fun allocateMemory(): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        repeat(50_000) {
            res.add(
                intArrayOf(
                    1 - it,
                    435 - it,
                    564 - it,
                    75 - it,
                    453 - it,
                    5 - it,
                    456 - it,
                    455 - it,
                    345 - it,
                    34 - it,
                    6 - it,
                    456 - it,
                    43 - it,
                    5 - it,
                    34 - it,
                    6 - it,
                    34 - it,
                    5 - it,
                    456 - it,
                    45 - it,
                    6 - it,
                    43 - it,
                    56 - it,
                    34 - it,
                    6 - it,
                ).sorted(),
            )
        }
        return res
    }
}
