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

package dev.shtanko.benchmark

import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class BenchmarkTest {

    @Test
    @Disabled("rework needed")
    fun `test memory measurement for a coroutine block`() = runTest {
        val memoryMeasurer = CoroutinesMemoryMeasurer()

        // Simulate a block of code that we want to measure
        val block: suspend () -> Unit = {
            // Simulating some work
            delay(500) // Simulate some delay
        }

        // Use a simple config for the test with 3 samples
        val config = BenchmarkConfig(memorySamples = 3)

        // Measure the memory usage of the block
        val memoryUsed = memoryMeasurer(block, config)

        // Assert that the measured memory usage is a reasonable value
        assertTrue(memoryUsed > 0, "Memory usage should be greater than 0")
    }

    @Disabled("This test is disabled because its failed on CI")
    @Test
    fun `test memory measurement over multiple samples`() = runTest {
        val memoryMeasurer = CoroutinesMemoryMeasurer()

        // Simulate a block of code
        val block: suspend () -> Unit = {
            // Simulate work that uses some memory
            delay(100) // Simulate a smaller task
        }

        // Use a config with multiple samples
        val config = BenchmarkConfig(memorySamples = 5)

        // Measure memory usage
        val memoryUsed = memoryMeasurer(block, config)

        // Check that memory usage is reasonable and greater than 0
        assertTrue(memoryUsed > 0, "Memory usage should be greater than 0")
    }

    @Disabled("This test is disabled because its failed on CI")
    @Test()
    fun `test memory measurement with a long-running block`() = runTest {
        val memoryMeasurer = CoroutinesMemoryMeasurer()

        // Simulate a block of code with longer execution time
        val block: suspend () -> Unit = {
            // Simulate a more intensive task
            delay(1000) // Simulating work for 1 second
        }

        // Config with a small number of samples
        val config = BenchmarkConfig(memorySamples = 2)

        // Measure the memory usage of the block
        val memoryUsed = memoryMeasurer(block, config)

        // Ensure that memory usage is positive
        assertTrue(memoryUsed > 0, "Memory usage should be greater than 0")
    }
}
