package dev.shtanko.concurrency.coroutines.flow

import kotlin.system.measureTimeMillis
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

private const val DELAY = 100L
private const val PROCESSING_DELAY = 300L

private fun foo(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(DELAY) // pretend we are asynchronously waiting 100 ms
        emit(i) // emit next value
    }
}

fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        foo()
            .collectLatest { value -> // cancel & restart on the latest value
                println("Collecting $value")
                delay(PROCESSING_DELAY) // pretend we are processing it for 300 ms
                println("Done $value")
            }
    }
    println("Collected in $time ms")
}
