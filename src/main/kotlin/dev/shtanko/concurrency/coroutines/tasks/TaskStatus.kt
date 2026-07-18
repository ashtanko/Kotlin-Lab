package dev.shtanko.concurrency.coroutines.tasks

enum class TaskStatus {
    IDLE, RUNNING, COMPLETED, CANCELLED, ERROR
}
