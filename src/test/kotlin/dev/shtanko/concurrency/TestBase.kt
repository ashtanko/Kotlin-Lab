/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.concurrency

import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

/**
 * Base class for tests that require deterministic execution order.
 * This class ensures that each action is executed in the expected order and that each action is only
 * executed once. It also provides utilities for testing exceptions.
 *
 * @see runTest
 * @see expect
 * @see finish
 */
open class TestBase {
    private var actionIndex = AtomicInteger()
    private var finished = AtomicBoolean()
    private var error = AtomicReference<Throwable>()

    fun expect(index: Int) {
        val wasIndex = actionIndex.incrementAndGet()
        check(index == wasIndex) { "Expecting action index $index but it is actually $wasIndex" }
    }

    fun finish(index: Int) {
        expect(index)
        check(!finished.getAndSet(true)) {
            "Should call 'finish(...) at most once'"
        }
    }

    /**
     * Run a test with the given block and check that it produces the expected exceptions.
     * The test is considered successful if the block throws an exception that matches the expected
     * exception. If the block throws an exception that does not match the expected exception, the
     * test fails. If the block does not throw an exception, the test fails.
     *
     * @param expected The expected exception. If null, the test is expected to throw an exception.
     * @param unhandled A list of predicates that are used to check if an unhandled exception is
     * expected. The predicates are checked in order, and the test fails if the block throws more
     * exceptions than there are predicates in the list. If the block throws an exception that does
     * not match the predicate at the corresponding index, the test fails.
     * @param block The block to run.
     * @throws Throwable If the block throws an exception that does not match the expected exception.
     */
    fun runTest(
        expected: ((Throwable) -> Boolean)? = null,
        unhandled: List<(Throwable) -> Boolean> = emptyList(),
        block: suspend CoroutineScope.() -> Unit,
    ) {
        var exCount = 0
        var ex: Throwable? = null
        try {
            runBlocking(
                block = block,
                context = CoroutineExceptionHandler { _, e ->
                    if (e is CancellationException) return@CoroutineExceptionHandler // are ignored
                    exCount++
                    when {
                        exCount > unhandled.size ->
                            printError("Too many unhandled exceptions $exCount, expected ${unhandled.size}, got: $e", e)

                        !unhandled[exCount - 1](e) ->
                            printError("Unhandled exception was unexpected: $e", e)
                    }
                },
            )
        } catch (e: Throwable) {
            ex = e
            if (expected != null) {
                if (!expected(e)) {
                    error("Unexpected exception: $e", e)
                }
            } else {
                throw e
            }
        } finally {
            if (ex == null && expected != null) {
                error("Exception was expected but none produced")
            }
        }
    }

    fun expectUnreached() {
        error("Should not be reached, current action index is ${actionIndex.get()}")
    }

    private fun printError(message: String, cause: Throwable) {
        setError(cause)
        println("$message: $cause")
        cause.printStackTrace(System.out)
        println("--- Detected at ---")
        Throwable().printStackTrace(System.out)
    }

    private inline fun check(value: Boolean, lazyMessage: () -> Any) {
        if (!value) error(lazyMessage())
    }

    private fun error(message: Any, cause: Throwable? = null): Nothing {
        throw makeError(message, cause)
    }

    private fun setError(exception: Throwable) {
        error.compareAndSet(null, exception)
    }

    private fun makeError(message: Any, cause: Throwable? = null): IllegalStateException =
        IllegalStateException(message.toString(), cause).also {
            setError(it)
        }
}

class TestException(message: String? = null, private val data: Any? = null) : Throwable(message)
class TestCancellationException(message: String? = null, private val data: Any? = null) : CancellationException(message)
