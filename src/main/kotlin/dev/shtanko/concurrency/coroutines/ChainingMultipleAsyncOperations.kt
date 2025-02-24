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

package dev.shtanko.concurrency.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

object ChainingMultipleAsyncOperations {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val userDetails = async { fetchUserDetails() }
        val userPosts = async { fetchUserPosts() }

        // Combine results
        println("${userDetails.await()} | ${userPosts.await()}")
    }

    private suspend fun fetchUserDetails(): String {
        delay(1000)
        return "User: John"
    }

    private suspend fun fetchUserPosts(): String {
        delay(1000)
        return "Posts: Kotlin, Coroutines"
    }
}
