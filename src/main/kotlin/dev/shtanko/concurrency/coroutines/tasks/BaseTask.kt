package dev.shtanko.concurrency.coroutines.tasks

import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

@Suppress("TooGenericExceptionCaught")
abstract class BaseTask<R>(
    override val name: String,
    override val description: String,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : Task<R> {
    private val _progress = MutableStateFlow(0f)
    override val progress: StateFlow<Float> = _progress.asStateFlow()

    private val _status = MutableStateFlow(TaskStatus.IDLE)
    override val status: StateFlow<TaskStatus> = _status.asStateFlow()

    private var job: Job? = null

    protected fun updateProgress(value: Float) {
        _progress.value = value.coerceIn(0f, 1f)
    }

    protected fun updateStatus(status: TaskStatus) {
        _status.value = status
    }

    override fun cancel() {
        job?.cancel()
        updateStatus(TaskStatus.CANCELLED)
    }

    override suspend fun run(): R {
        return withContext(dispatcher) {
            job = coroutineContext[Job]
            updateStatus(TaskStatus.RUNNING)
            updateProgress(0f)

            try {
                val result = execute()
                updateStatus(TaskStatus.COMPLETED)
                updateProgress(1f)
                result
            } catch (e: CancellationException) {
                updateStatus(TaskStatus.CANCELLED)
                throw e
            } catch (e: Exception) {
                updateStatus(TaskStatus.ERROR)
                throw e
            }
        }
    }

    protected abstract suspend fun execute(): R
}
