package dev.shtanko.concurrency.coroutines.tasks

import kotlinx.coroutines.flow.StateFlow

interface Task<R> {
    val progress: StateFlow<Float>
    val status: StateFlow<TaskStatus>
    val name: String
    val description: String
    suspend fun run(): R
    fun cancel()
}
