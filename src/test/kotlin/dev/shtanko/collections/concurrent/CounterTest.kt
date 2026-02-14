package dev.shtanko.collections.concurrent

import java.util.concurrent.atomic.AtomicInteger
import org.jetbrains.lincheck.datastructures.Operation
import org.jetbrains.lincheck.datastructures.StressOptions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable

@DisabledIfEnvironmentVariable(named = "CI", matches = "true")
private class Counter {
    private val value = AtomicInteger(0)

    fun inc(): Int = value.incrementAndGet()
    fun get() = value.get()
}

class CounterTest {
    private val c = Counter()

    @Operation
    fun inc() = c.inc()

    @Operation
    fun get() = c.get()

    @Test
    fun stressTest() = StressOptions() // Stress testing options:
        .actorsBefore(2) // Number of operations before the parallel part
        .threads(2) // Number of threads in the parallel part
        .actorsPerThread(2) // Number of operations in each thread of the parallel part
        .actorsAfter(1) // Number of operations after the parallel part
        .iterations(50) // Generate 100 random concurrent scenarios
        .invocationsPerIteration(500) // Run each generated scenario 1000 times
        .check(this::class) // Run the test
}
