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

@file:Suppress("MagicNumber", "SwallowedException", "TooGenericExceptionThrown", "TooGenericExceptionCaught")

package dev.shtanko.concurrency.coroutines.examples

import dev.shtanko.concurrency.coroutines.examples.AtomicBankTransfer.Account
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

interface IAtomicBankTransfer {
    suspend fun performAtomicTransfer(from: Account, to: Account, amount: Double, dispatcher: CoroutineDispatcher)
    suspend fun transferFunds(from: Account, to: Account, amount: Double)
    suspend fun compensateTransaction(from: Account, to: Account, amount: Double)
    suspend fun logTransaction(from: Account, to: Account, amount: Double)
    suspend fun debitAccount(account: Account, amount: Double)
    suspend fun creditAccount(account: Account, amount: Double)
}

class AtomicBankTransfer : IAtomicBankTransfer {
    // Mutex to ensure atomicity on account balance updates
    private val mutex = Mutex()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            // Simulate account repository
            val accounts = mutableListOf(
                Account(1, 1000.0), // Account A
                Account(2, 500.0),    // Account B
            )
            val atomicBankTransfer = AtomicBankTransfer()
            val executionTime = measureTimeMillis {
                // Atomic transfer: $200 from Account 1 to Account 2
                atomicBankTransfer.performAtomicTransfer(accounts[0], accounts[1], 200.0, Dispatchers.IO)
            }

            println("Execution time: $executionTime ms")
            println("Account 1 balance: ${accounts[0].balance}")
            println("Account 2 balance: ${accounts[1].balance}")
        }
    }

    // Function to perform the entire operation atomically with retries and timeouts
    override suspend fun performAtomicTransfer(
        from: Account,
        to: Account,
        amount: Double,
        dispatcher: CoroutineDispatcher,
    ) =
        withContext(Dispatchers.Default) {
            var retries = 3
            var transferSuccess = false

            while (retries > 0 && !transferSuccess) {
                try {
                    val job = CoroutineScope(dispatcher).launch {
                        // Using coroutine to handle retries and ensure atomicity
                        transferFunds(from, to, amount)
                    }
                    // Timeout in case of long-running operations
                    withTimeout(5000) {
                        job.join() // Wait for the transfer to complete
                    }
                    transferSuccess = true
                } catch (e: TimeoutCancellationException) {
                    println("Transfer timed out, retrying... ${e.message}")
                    retries--
                } catch (e: Exception) {
                    println("Exception: ${e.message}")
                    retries--
                }
            }

            if (!transferSuccess) {
                println("Transfer failed after 3 retries")
            }
        }

    // Function to perform the atomic transfer with rollback/compensation
    override suspend fun transferFunds(from: Account, to: Account, amount: Double) {
        try {
            // Debit from 'from' account
            debitAccount(from, amount)

            // Credit to 'to' account
            creditAccount(to, amount)

            // Log the transaction
            logTransaction(from, to, amount)

            println("Transfer completed successfully: $amount from Account ${from.id} to Account ${to.id}")
        } catch (e: Exception) {
            println("Error during transfer: ${e.message}")
            // Compensate (rollback) if any error occurs
            compensateTransaction(from, to, amount)
        }
    }

    // A compensating action for rollback (reverse the transaction if something fails)
    override suspend fun compensateTransaction(from: Account, to: Account, amount: Double) {
        println("Compensating transaction: rolling back $amount from Account ${to.id} to Account ${from.id}")
        debitAccount(to, amount)  // Reverse the credit to `to` account
        creditAccount(from, amount)  // Reverse the debit to `from` account
    }

    // Simulate logging the transaction (for auditing)
    override suspend fun logTransaction(from: Account, to: Account, amount: Double) = withContext(Dispatchers.Default) {
        println("Logging transaction: $amount from Account ${from.id} to Account ${to.id}")
        delay(500) // Simulate network delay
    }

    // Function to simulate debiting from an account
    override suspend fun debitAccount(account: Account, amount: Double) {
        mutex.withLock {
            if (account.balance >= amount) {
                println("Debiting ${account.id}: $amount")
                account.balance -= amount
            } else {
                throw Exception("Insufficient funds in Account ${account.id}")
            }
        }
    }

    // Function to simulate crediting to an account
    override suspend fun creditAccount(account: Account, amount: Double) {
        mutex.withLock {
            println("Crediting ${account.id}: $amount")
            account.balance += amount
        }
    }

    // Data models
    data class Account(var id: Int, var balance: Double)
}
