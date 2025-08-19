package dev.shtanko.concurrency.coroutines.tasks

data class TaskResult<T>(
    val taskName: String,
    val result: T?,
    val executionTime: Long,
    val status: TaskStatus,
)
