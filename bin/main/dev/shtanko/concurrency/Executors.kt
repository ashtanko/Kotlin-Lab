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

package dev.shtanko.concurrency

import java.io.File
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

@Suppress("MagicNumber")
object SimpleExecutorExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()

        executor.execute {
            println("Task is running on a separate thread.")
            Thread.sleep(1000)
        }

        // Shut down the executor gracefully
        executor.shutdown()

        // Wait for tasks to finish (optional)
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow()
            }
        } catch (_: InterruptedException) {
            executor.shutdownNow()
        }
    }
}

@Suppress("MagicNumber")
object ExecutorServiceExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val executorService: ExecutorService = Executors.newFixedThreadPool(3)

        for (i in 1..5) {
            executorService.execute {
                println("Task $i is running on ${Thread.currentThread().name}")
            }
        }

        executorService.shutdown()
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow()
            }
        } catch (e: InterruptedException) {
            executorService.shutdownNow()
        }
    }
}

@Suppress("MagicNumber")
object ScheduledExecutorExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val scheduledExecutorService: ScheduledExecutorService = Executors.newScheduledThreadPool(1)

        scheduledExecutorService.schedule(
            {
                println("Task is running after a delay.")
            },
            5,
            TimeUnit.SECONDS,
        )

        scheduledExecutorService.scheduleAtFixedRate(
            {
                println("Repeating task is running.")
            },
            0,
            3,
            TimeUnit.SECONDS,
        )

        // To stop the scheduled tasks after some time
        Thread.sleep(15000)
        scheduledExecutorService.shutdown()
    }
}

@Suppress("MagicNumber")
object UsingCallableAndFuture {
    @JvmStatic
    fun main(args: Array<String>) {
        val executorService = Executors.newSingleThreadExecutor()

        val future: Future<String> = executorService.submit(
            Callable {
                // Simulate long running task
                Thread.sleep(2000)
                "Task Completed"
            },
        )

        println("Task submitted")
        val result = future.get() // This will block until the task is complete
        println("Result: $result")

        executorService.shutdown()
    }
}

@Suppress("TooGenericExceptionCaught", "MagicNumber")
object GeneratingUserReportsConcurrently {
    class ReportGenerationTask(private val userId: String) : Callable<String> {
        override fun call(): String {
            // Simulate complex report generation logic
            println("Generating report for user: $userId on ${Thread.currentThread().name}")
            Thread.sleep(2000) // Simulate time-consuming task
            return "Report for user $userId"
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val userIds = listOf("user1", "user2", "user3", "user4", "user5")
        val executorService = Executors.newFixedThreadPool(2)
        val futures = mutableListOf<Future<String>>()

        // Submit tasks to the executor service
        for (userId in userIds) {
            val task = ReportGenerationTask(userId)
            val future: Future<String> = executorService.submit(task)
            futures.add(future)
        }

        // Retrieve and process results
        for (future in futures) {
            try {
                val result = future.get()
                println("Received result: $result")
            } catch (e: Exception) {
                println("Error occurred while generating report: ${e.message}")
            }
        }

        executorService.shutdown()
    }
}

@Suppress("TooGenericExceptionCaught")
object ConcurrentFileDownloads {

    class FileDownloadTask(private val fileUrl: String, private val outputDir: String) : Callable<String> {
        override fun call(): String {
            val url = URL(fileUrl)
            val fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1)
            val outputFile = File(outputDir, fileName)

            url.openStream().use { inputStream: InputStream ->
                outputFile.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }

            return "Downloaded $fileUrl to $outputFile"
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val fileUrls = listOf(
            "https://example.com/file1.jpg",
            "https://example.com/file2.jpg",
            "https://example.com/file3.jpg",
        )
        val outputDir = "downloads"
        val executorService = Executors.newFixedThreadPool(3)
        val futures = mutableListOf<Future<String>>()

        // Ensure the output directory exists
        File(outputDir).mkdirs()

        // Submit tasks to the executor service
        for (fileUrl in fileUrls) {
            val task = FileDownloadTask(fileUrl, outputDir)
            val future: Future<String> = executorService.submit(task)
            futures.add(future)
        }

        // Retrieve and process results
        for (future in futures) {
            try {
                val result = future.get()
                println(result)
            } catch (e: Exception) {
                println("Error occurred while downloading file: ${e.message}")
            }
        }

        executorService.shutdown()
    }
}
