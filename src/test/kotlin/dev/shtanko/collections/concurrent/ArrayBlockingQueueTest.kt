package dev.shtanko.collections.concurrent

import java.util.concurrent.ArrayBlockingQueue
import org.jetbrains.lincheck.datastructures.ModelCheckingOptions
import org.jetbrains.lincheck.datastructures.Operation
import org.jetbrains.lincheck.datastructures.StressOptions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable

@DisabledIfEnvironmentVariable(named = "CI", matches = "true")
class ArrayBlockingQueueTest {
    // We limit capacity to 2 to maximize contention and boundary condition hits
    private val queue = ArrayBlockingQueue<Int>(2)

    @Operation
    fun offer(value: Int): Boolean = queue.offer(value)

    @Operation
    fun poll(): Int? = queue.poll()

    @Operation
    fun peek(): Int? = queue.peek()

    @Operation
    fun size(): Int = queue.size

    @Test
    fun stressTest() {
        StressOptions()
            .iterations(100)
            .threads(3)
            .invocationsPerIteration(10)
            .check(this::class)
    }

    @Test
    fun modelCheckingTest() {
        ModelCheckingOptions()
            .iterations(50)
            .threads(3) // 3 threads are better for catching deadlocks in queues
            .invocationsPerIteration(10)
            .check(this::class)
    }
}
