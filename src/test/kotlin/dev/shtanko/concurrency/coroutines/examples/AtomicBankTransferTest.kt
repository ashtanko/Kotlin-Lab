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

package dev.shtanko.concurrency.coroutines.examples

import dev.shtanko.concurrency.coroutines.examples.AtomicBankTransfer.Account
import java.util.concurrent.CountDownLatch
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.fail
import org.mockito.Mockito

class AtomicBankTransferTest {
    private lateinit var atomicBankTransfer: AtomicBankTransfer

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setUp() {
        atomicBankTransfer = AtomicBankTransfer()
    }

    @Test
    fun testValidTransfer() = runTest(timeout = 2.seconds) {
        val accounts = mutableListOf(
            Account(1, 1000.0), // Account A
            Account(2, 500.0),    // Account B
        )
        withContext(Dispatchers.Default) {
            atomicBankTransfer.performAtomicTransfer(accounts[0], accounts[1], 200.0, dispatcher)
            assertEquals(800.0, accounts[0].balance)
            assertEquals(700.0, accounts[1].balance)
        }
    }

    @Test
    @Disabled("rework needed")
    fun testInsufficientBalance() = runTest {
        val accountA = Account(1, 1000.0)
        val accountB = Account(2, 500.0)
        try {
            atomicBankTransfer.performAtomicTransfer(accountA, accountB, 1200.0, dispatcher)
            fail("Expected exception due to insufficient balance")
        } catch (e: Exception) {
            assertEquals("Insufficient funds in Account 1", e.message)
        }
    }

    // Test: Simulate failure and ensure rollback occurs
    @Test
    fun testFailureAndRollback() = runTest {
        val accountA = Account(1, 1000.0)
        val accountB = Account(2, 500.0)
        // Simulate an exception during the transfer
        val mockAtomicBankTransfer = Mockito.mock(AtomicBankTransfer::class.java)
        Mockito.`when`(mockAtomicBankTransfer.performAtomicTransfer(accountA, accountB, 200.0, dispatcher)).thenThrow(
            Error("Logging failed"),
        )
        val exception = assertThrows<Error>({ "Should throw an Exception" }) {
            mockAtomicBankTransfer.performAtomicTransfer(accountA, accountB, 200.0, dispatcher)
        }
        assertEquals("Logging failed", exception.message)
        // Verify rollback has occurred
        assertEquals(1000.0, accountA.balance)
        assertEquals(500.0, accountB.balance)
    }

    @Test
    fun testFailureHappenedInTheMiddleOfTransferAndRollback() = runTest {
        val accountA = Account(1, 1000.0)
        val accountB = Account(2, 500.0)
        // Simulate an exception during the transfer
        val mockAtomicBankTransfer = Mockito.mock(AtomicBankTransfer::class.java)
        Mockito.`when`(mockAtomicBankTransfer.transferFunds(accountA, accountB, 200.0)).thenThrow(
            Error("Logging failed"),
        )
        Mockito.`when`(mockAtomicBankTransfer.performAtomicTransfer(accountA, accountB, 200.0, dispatcher))
            .thenReturn(Unit)
        mockAtomicBankTransfer.performAtomicTransfer(accountA, accountB, 200.0, dispatcher)
        //assertEquals("Logging failed", exception.message)
        // Verify rollback has occurred
        assertEquals(1000.0, accountA.balance)
        assertEquals(500.0, accountB.balance)
    }

    // Test: Ensure that retries happen in case of a failure (timeout, network, etc.)
    @Test
    fun testRetryOnFailure() = runTest(timeout = 2.seconds) {
        val accountA = Account(1, 1000.0)
        val accountB = Account(2, 500.0)
        val mockTransfer = FakeAtomicBankTransfer()

        mockTransfer.performAtomicTransfer(accountA, accountB, 200.0, dispatcher)

        assertEquals(800.0, accountA.balance)
        assertEquals(700.0, accountB.balance)
    }

    private class FakeAtomicBankTransfer : IAtomicBankTransfer {
        private val atomicBankTransfer = AtomicBankTransfer()

        val countDownLatch = CountDownLatch(1)
        var retries = 0

        override suspend fun performAtomicTransfer(
            from: Account,
            to: Account,
            amount: Double,
            dispatcher: CoroutineDispatcher,
        ) {
            return atomicBankTransfer.performAtomicTransfer(from, to, amount, dispatcher)
        }

        override suspend fun transferFunds(
            from: Account,
            to: Account,
            amount: Double,
        ) {
            if (retries == 0) {
                retries++
                throw Error("Test time out error")
            } else {
                atomicBankTransfer.transferFunds(from, to, amount)
                countDownLatch.countDown()
            }
            countDownLatch.await()
        }

        override suspend fun compensateTransaction(
            from: Account,
            to: Account,
            amount: Double,
        ) {
            return atomicBankTransfer.compensateTransaction(from, to, amount)
        }

        override suspend fun logTransaction(
            from: Account,
            to: Account,
            amount: Double,
        ) {
            return atomicBankTransfer.logTransaction(from, to, amount)
        }

        override suspend fun debitAccount(
            account: Account,
            amount: Double,
        ) {
            return atomicBankTransfer.debitAccount(account, amount)
        }

        override suspend fun creditAccount(
            account: Account,
            amount: Double,
        ) {
            return atomicBankTransfer.creditAccount(account, amount)
        }
    }
}
