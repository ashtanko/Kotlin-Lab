@file:Suppress("MagicNumber", "SwallowedException", "TooGenericExceptionCaught")

package dev.shtanko.concurrency.coroutines.tasks

import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    private val _tasks = MutableStateFlow<List<Task<*>>>(emptyList())
    val tasks: StateFlow<List<Task<*>>> = _tasks.asStateFlow()

    private val _results = MutableStateFlow<List<TaskResult<*>>>(emptyList())
    val results: StateFlow<List<TaskResult<*>>> = _results.asStateFlow()

    private val scope = CoroutineScope(dispatcher + SupervisorJob())

    fun addTask(task: Task<*>) {
        _tasks.value += task
    }

    fun runTask(task: Task<*>) {
        scope.launch {
            val startTime = System.currentTimeMillis()
            try {
                val result = task.run()
                val executionTime = System.currentTimeMillis() - startTime

                _results.value += TaskResult(
                    taskName = task.name,
                    result = result,
                    executionTime = executionTime,
                    status = task.status.value,
                )
            } catch (e: CancellationException) {
                val executionTime = System.currentTimeMillis() - startTime
                _results.value += TaskResult(
                    taskName = task.name,
                    result = null,
                    executionTime = executionTime,
                    status = TaskStatus.CANCELLED,
                )
            } catch (e: Exception) {
                val executionTime = System.currentTimeMillis() - startTime
                _results.value += TaskResult(
                    taskName = task.name,
                    result = null,
                    executionTime = executionTime,
                    status = TaskStatus.ERROR,
                )
            }
        }
    }

    fun runAllTasks() {
        _tasks.value.forEach { task ->
            runTask(task)
        }
    }

    fun cancelAllTasks() {
        _tasks.value.forEach { it.cancel() }
    }

    fun clearResults() {
        _results.value = emptyList()
    }

    fun clearTasks() {
        cancelAllTasks()
        _tasks.value = emptyList()
        _results.value = emptyList()
    }

    fun onCleared() {
        scope.cancel()
    }
}
