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

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.fail

// This annotation is used to configure the lifecycle of test instances for the annotated test class or test interface.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnnotationsTest {

    @BeforeAll
    fun initAll() {
        println("initAll")

        // Example: Load a large dataset or configuration file
    }

    @BeforeEach
    fun init() {
        println("init")
        // Example: myService = MyService() // Create a new instance before each test
    }

    @Test
    fun succeedingTest() {
        assertTrue(true)
    }

    @Test
    fun failingTest() {
        // fail { "a failing test" }
    }

    @Test
    @Disabled("for demonstration purposes")
    fun skippedTest() {
        println("skippedTest")
    }

    @Test
    fun abortedTest() {
        assumeTrue("abc".contains("Z"))
        fail("test should have been aborted")
    }

    @AfterEach
    fun tearDown() {
        println("tearDown")
        // Example: close the database connection
    }

    @AfterAll
    fun tearDownAll() {
        println("tearDownAll")
        // Example: Close the shared connection pool
    }
}
