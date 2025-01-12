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

@file:Suppress("MagicNumber")
package dev.shtanko.concurrency.coroutines.examples

import kotlin.random.Random
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withTimeoutOrNull

object ECommerceDataSync {

    // Simulated database and network operations
    private val mutex = Mutex()
    private val productDatabase = mutableListOf<Product>()

    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        // Measure the total execution time
        val executionTime = measureTimeMillis {
            val syncJob = launch {
                // Use a flow to track the sync progress
                launch {
                    productSyncProgressFlow().collect { progress ->
                        println(progress)
                    }
                }

                try {
                    val productData = async {
                        retryOperation(3) { fetchProductData() }
                    }

                    // Wait for the data to be fetched
                    val products = productData.await()

                    // Now save the products to the database
                    val saveJob = async {
                        saveProductsToDatabase(products)
                    }

                    // Wait for saving to finish
                    saveJob.await()

                    // Final check: simulate a result update (e.g., notify UI)
                    println("Sync completed successfully.")
                } catch (e: Exception) {
                    println("Error during sync: ${e.message}")
                }
            }

            // Cancel the sync job after a timeout
            withTimeoutOrNull(5000) {
                syncJob.join()
            } ?: println("Sync operation timed out!")
        }

        println("Total execution time: $executionTime ms")
        println("Product database: $productDatabase")
    }

    // Flow to emit product sync progress
    fun productSyncProgressFlow(): Flow<String> = flow {
        emit("Sync started")
        delay(500)
        emit("Fetching product data...")
        delay(500)
        emit("Saving product data...")
        delay(500)
        emit("Sync completed")
    }

    // Retry mechanism for network request
    private suspend fun <T> retryOperation(maxRetries: Int, block: suspend () -> T): T {
        var currentAttempt = 0
        while (true) {
            try {
                return block()
            } catch (e: Exception) {
                currentAttempt++
                if (currentAttempt >= maxRetries) throw e
                println("Error occurred, retrying... Attempt $currentAttempt/$maxRetries")
                delay(1000) // Delay between retries
            }
        }
    }

    // Simulate a network call to fetch product data
    private suspend fun fetchProductData(): List<Product> {
        delay(1000) // Simulate network delay
        return List(5) { Product(Random.nextInt(), "Product-${Random.nextInt(100)}", Random.nextDouble(10.0, 100.0)) }
    }

    // Simulate saving products to a local database
    private suspend fun saveProductsToDatabase(products: List<Product>) {
        delay(500) // Simulate saving to DB
        mutex.withLock {
            productDatabase.addAll(products)
        }
        println("Saved products to database")
    }

    private data class Product(val id: Int, val name: String, val price: Double)
}
