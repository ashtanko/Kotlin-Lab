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

package dev.shtanko.mockk

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import org.junit.jupiter.api.Test

class MockingSingletonObjectsTest {

    @Test
    fun `mocking singleton objects example test`() {
        mockkObject(Logger)
        every { Logger.log(any()) } just Runs

        doSomething()
        verify { Logger.log("Action performed") }
        unmockkObject(Logger)
    }

    private object Logger {
        fun log(message: String) {
            println(message)
        }
    }

    private fun doSomething() {
        Logger.log("Action performed")
    }
}
