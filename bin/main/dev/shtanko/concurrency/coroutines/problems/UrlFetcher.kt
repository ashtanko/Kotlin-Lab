/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.concurrency.coroutines.problems

import java.net.URL
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    // Define a list of URLs to fetch data from
    val urls = listOf(
        "https://jsonplaceholder.typicode.com/posts/1",
        "https://jsonplaceholder.typicode.com/posts/2",
        "https://jsonplaceholder.typicode.com/posts/3",
    )

    // Start a coroutine
    runBlocking {
        // Create a list to store the deferred results
        val deferredResults = mutableListOf<Deferred<String>>()

        // Launch a coroutine for each URL and add the deferred result to the list
        urls.forEach { url ->
            val deferred = async {
                fetchData(url)
            }
            deferredResults.add(deferred)
        }

        // Await all the deferred results
        val results = deferredResults.awaitAll()

        // Process the results
        results.forEachIndexed { index, result ->
            println("Result from URL ${urls[index]}:\n$result\n")
        }
    }
}

suspend fun fetchData(url: String): String {
    return withContext(Dispatchers.IO) {
        println("Fetching data from URL: $url")

        // Fetch data from the URL
        val response = URL(url).readText()

        println("Data fetched from URL: $url")

        // Return the fetched data
        response
    }
}
