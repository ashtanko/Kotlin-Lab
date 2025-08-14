@file:JvmName("Main")

package dev.shtanko.http

import okhttp3.OkHttpClient

fun main() {
    val connectionFactory: ConnectionFactory = FileSqliteConnectionFactory("http_logs.db")
    val repository = HttpLogRepository(connectionFactory()).apply { init() }
    val dbLogger = DbHttpLogger(repository)

    val client = OkHttpClient.Builder()
        .addInterceptor(CustomInterceptor.create(dbLogger))
        .build()

    // Example request
    val request = okhttp3.Request.Builder()
        .url("https://httpbin.org/get")
        .build()

    client.newCall(request).execute().use { response ->
        println("Response: ${response.code}")
    }

    // Show logs
    val allLogs = repository.getAll()
    println("Stored logs(${allLogs.size}):")
    allLogs.forEach { println(it) }
}
