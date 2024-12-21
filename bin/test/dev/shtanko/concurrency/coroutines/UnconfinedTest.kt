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

package dev.shtanko.concurrency.coroutines

import dev.shtanko.concurrency.TestBase
import dev.shtanko.concurrency.TestException
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Test

class UnconfinedTest : TestBase() {
    @Test
    fun `order test`() = runTest {
        expect(1)
        launch(Dispatchers.Unconfined) {
            expect(2)
            launch {
                expect(4)
                launch {
                    expect(6)
                }

                launch {
                    expect(7)
                }
                expect(5)
            }

            expect(3)
        }

        finish(8)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `block throws test`() = runTest {
        expect(1)
        try {
            withContext(Dispatchers.Unconfined) {
                expect(2)
                withContext(Dispatchers.Unconfined + CoroutineName("a")) {
                    expect(3)
                }

                expect(4)
                launch(start = CoroutineStart.ATOMIC) {
                    expect(5)
                }

                throw TestException()
            }
        } catch (e: TestException) {
            e.printStackTrace()
            finish(6)
        }
    }
}
