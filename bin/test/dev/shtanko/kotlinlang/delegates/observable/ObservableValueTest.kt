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

package dev.shtanko.kotlinlang.delegates.observable

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ObservableValueTest {
    @BeforeEach
    fun setup() {
        observableValue = "<no value>"
    }

    @Test
    fun `observableValue changes from no value to 1`() {
        observableValue = "1"
        assertEquals("1", observableValue)
    }

    @Test
    fun `observableValue changes from 1 to 2`() {
        observableValue = "1"
        observableValue = "2"
        assertEquals("2", observableValue)
    }

    @Test
    fun `observableValue changes from 2 to empty string`() {
        observableValue = "2"
        observableValue = ""
        assertEquals("", observableValue)
    }

    @Test
    fun `observableValue changes from empty string to no value`() {
        observableValue = ""
        observableValue = "<no value>"
        assertEquals("<no value>", observableValue)
    }
}
