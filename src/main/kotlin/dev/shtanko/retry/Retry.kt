package dev.shtanko.retry

import java.io.IOException
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import kotlinx.coroutines.delay
import retrofit2.HttpException

/**
 * Defines the retry policy interface.
 * This interface is responsible for determining whether a retry attempt should be made
 * based on the exception thrown and the current attempt number.
 */
fun interface RetryPolicy {
    /**
     * Determines if the operation should be retried.
     *
     * @param throwable The exception that was thrown during the operation.
     * @param attempt The current retry attempt number.
     * @return true if the operation should be retried, false otherwise.
     */
    fun shouldRetry(throwable: Throwable, attempt: Int): Boolean
}

/**
 * The default retry policy. It retries up to a specified maximum number of attempts
 * and retries on certain exceptions (by default, [IOException] and [HttpException]).
 *
 * @param maxAttempts The maximum number of retry attempts.
 * @param retryIf A lambda function to check whether the exception should trigger
 * a retry (default is [IOException] or [HttpException]).
 */
class DefaultRetryPolicy(
    private val maxAttempts: Int = 3,
    private val retryIf: (Throwable) -> Boolean = { it is IOException || it is HttpException },
) : RetryPolicy {

    /**
     * Checks whether a retry should be attempted based on the exception thrown and the current attempt number.
     *
     * @param throwable The exception that was thrown during the operation.
     * @param attempt The current retry attempt number.
     * @return true if the operation should be retried, false otherwise.
     */
    override fun shouldRetry(throwable: Throwable, attempt: Int): Boolean {
        return attempt < maxAttempts && retryIf(throwable)
    }
}

/**
 * Constants used in retry operations, such as initial delay, maximum delay, and retry factor.
 */
object RetryConstants {

    /** Default initial delay in milliseconds for retries. */
    const val DEFAULT_INITIAL_DELAY: Long = 1000L

    /** Default maximum delay in milliseconds for retries. */
    const val DEFAULT_MAX_DELAY: Long = 10_000L

    /** Default factor to multiply the delay by after each retry. */
    const val DEFAULT_FACTOR: Double = 2.0
}

/**
 * A suspend function that retries a block of code based on the given retry policy.
 * The retry logic applies an exponential backoff strategy, which increases the delay between attempts.
 * It keeps retrying until the maximum number of attempts is reached or the block succeeds.
 *
 * @param initialDelay The initial delay before the first retry (in milliseconds).
 * @param maxDelay The maximum delay between retries (in milliseconds).
 * @param factor The multiplier to increase the delay by after each retry.
 * @param policy The retry policy that determines if a retry should be attempted.
 * @param block The suspend function to be retried. The attempt number is passed as a parameter.
 * @return The result of the block if successful.
 * @throws Throwable If the block throws an exception and the policy does not allow further retries.
 */
@Suppress("TooGenericExceptionCaught")
suspend fun <T> retry(
    initialDelay: Long = RetryConstants.DEFAULT_INITIAL_DELAY,
    maxDelay: Long = RetryConstants.DEFAULT_MAX_DELAY,
    factor: Double = RetryConstants.DEFAULT_FACTOR,
    policy: RetryPolicy = DefaultRetryPolicy(),
    block: suspend (Int) -> T,
): T {
    val currentDelay = AtomicLong(initialDelay)
    val attempt = AtomicInteger(0)

    while (true) {
        try {
            return block(attempt.get())
        } catch (e: Throwable) {
            val currentAttempt = attempt.incrementAndGet()
            if (!policy.shouldRetry(e, currentAttempt)) {
                // If the policy indicates no retry, rethrow the exception
                throw e
            }
            delay(currentDelay.get())
            currentDelay.set((currentDelay.get() * factor).toLong().coerceAtMost(maxDelay))
        }
    }
}
