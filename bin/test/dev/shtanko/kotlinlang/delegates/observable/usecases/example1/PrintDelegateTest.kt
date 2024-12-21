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

package dev.shtanko.kotlinlang.delegates.observable.usecases.example1

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class PrintDelegateTest {

    @Test
    fun `printDelegate changes s1 value`() {
        var s1 by printDelegate("s1")
        s1 = "s2"
        assertEquals("s2", s1)
    }

    @Test
    fun `printDelegate changes Foo instance`() {
        var fooDelegate: Foo by printDelegate(Foo())
        val newFoo = Foo()
        fooDelegate = newFoo
        assertEquals(newFoo, fooDelegate)
    }

    @Test
    fun `printDelegate changes Bar instance`() {
        var barDelegate: Bar by printDelegate(Bar())
        val newBar = Bar()
        barDelegate = newBar
        assertEquals(newBar, barDelegate)
    }
}
