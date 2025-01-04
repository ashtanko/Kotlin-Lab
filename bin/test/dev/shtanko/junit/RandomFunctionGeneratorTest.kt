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

package dev.shtanko.junit

import kotlin.random.Random
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RandomFunctionGeneratorTest {
    @Test
    fun `should generate random numbers within specified range`() {
        val min = 1
        val max = 10
        val iterations = 1000

        val randomNumbers = mutableListOf<Int>()
        repeat(iterations) {
            randomNumbers.add(Random.nextInt(min, max + 1))
        }

        // Basic checks:
        // 1. Ensure all numbers are within the expected range
        assertTrue(randomNumbers.all { it in min..max })

        // 2. Perform basic statistical checks (optional)
        // - Calculate mean and standard deviation
        // - Compare with expected values for a uniform distribution
        // (This requires more complex statistical analysis)
    }
}
