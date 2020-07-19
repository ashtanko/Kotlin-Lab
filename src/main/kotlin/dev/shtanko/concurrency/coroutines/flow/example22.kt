package dev.shtanko.concurrency.coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import java.lang.System.currentTimeMillis

private const val DELAY = 300L
private const val PROCESSING_DELAY = 400L

fun main() = runBlocking<Unit> {
    val nums = (1..3).asFlow().onEach { delay(DELAY) } // numbers 1..3 every 300 ms
    val strs = flowOf("one", "two", "three").onEach { delay(PROCESSING_DELAY) } // strings every 400 ms
    val startTime = currentTimeMillis() // remember the start time
    nums.combine(strs) { a, b -> "$a -> $b" } // compose a single string with "combine"
        .collect { value -> // collect and print
            println("$value at ${currentTimeMillis() - startTime} ms from start")
        }
}
