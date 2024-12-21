/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.concurrency.coroutines.mutex.bank

import dev.shtanko.concurrency.TestBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BankTest : TestBase() {
    private val bank = Bank()

    @Test
    fun `deposit & withdraw test`() = runTest {
        assertThat(bank).isNotNull

        withContext(Dispatchers.Default) {
            bank.invoke(nCoroutines = 1000) {
                bank.deposit(1)
                bank.withdraw(1)
            }
        }

        assertThat(bank.getBalance()).isEqualTo(1000)
    }

    @Test
    fun `deposit & withdraw test 2`() = runTest {
        assertThat(bank).isNotNull

        withContext(Dispatchers.Default) {
            bank.invoke(nCoroutines = 1000) {
                bank.deposit(1)
            }
            bank.invoke(nCoroutines = 1000) {
                bank.withdraw(1)
            }
        }

        assertThat(bank.getBalance()).isEqualTo(1000)
    }

    @Test
    fun `deposit & withdraw test 3`() = runTest {
        assertThat(bank).isNotNull

        withContext(Dispatchers.Default) {
            bank.invoke(nCoroutines = 1000) {
                bank.deposit(1)
            }
            bank.invoke(nCoroutines = 1000) {
                bank.deposit(2)
            }
            bank.invoke(nCoroutines = 1000) {
                bank.withdraw(2)
            }
            bank.invoke(nCoroutines = 1000) {
                bank.withdraw(1)
            }
        }

        assertThat(bank.getBalance()).isEqualTo(1000)
    }

    @Test
    fun `deposit & withdraw test 4`() = runTest {
        assertThat(bank).isNotNull

        withContext(Dispatchers.Default) {
            bank.invoke(nCoroutines = 1000) {
                bank.deposit(1)
                bank.withdraw(1)
                bank.deposit(2)
            }
            bank.invoke(nCoroutines = 1000) {
                bank.withdraw(2)
                bank.deposit(1)
            }
            bank.invoke(nCoroutines = 1000) {
                bank.withdraw(1)
            }
            bank.invoke(nCoroutines = 1000) {
                bank.deposit(5)
            }
            bank.invoke(nCoroutines = 1000) {
                bank.withdraw(5)
            }
        }

        assertThat(bank.getBalance()).isEqualTo(1000)
    }

    @Test
    fun `deposit & withdraw test 5`() = runTest {
        assertThat(bank).isNotNull

        withContext(Dispatchers.Default) {
            for (i in 1..5) {
                bank.invoke(nCoroutines = 1000 * i) {
                    bank.deposit(i)
                    bank.withdraw(i)
                }
            }
            for (j in 1..5) {
                bank.invoke(nCoroutines = 1000 * j) {
                    bank.deposit(j)
                    bank.withdraw(j)
                }
            }
        }

        assertThat(bank.getBalance()).isEqualTo(1000)
    }
}
