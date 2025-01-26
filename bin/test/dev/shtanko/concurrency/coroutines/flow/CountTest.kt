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

package dev.shtanko.concurrency.coroutines.flow

import dev.shtanko.concurrency.TestBase
import dev.shtanko.concurrency.TestException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Test

class CountTest : TestBase() {
    @Test
    fun `count test`() = runTest {
        val flow = flowOf(239, 240)
        assertEquals(2, flow.count())
        assertEquals(2, flow.count { true })
        assertEquals(1, flow.count { it % 2 == 0 })
        assertEquals(0, flow.count { false })
    }

    @Test
    fun `no values test`() = runTest {
        assertEquals(0, flowOf<Int>().count())
        assertEquals(0, flowOf<Int>().count { false })
        assertEquals(0, flowOf<Int>().count { true })
    }

    @Test
    fun `exception test`() = runTest {
        val flow = flow<Int> {
            throw TestException()
        }

        assertFailsWith<TestException> { flow.count() }
        assertFailsWith<TestException> { flow.count { false } }
    }

    @Test
    fun `exception after value test`() = runTest {
        val flow = flow {
            emit(1)
            throw TestException()
        }

        assertFailsWith<TestException> { flow.count() }
        assertFailsWith<TestException> { flow.count { false } }
    }
}
