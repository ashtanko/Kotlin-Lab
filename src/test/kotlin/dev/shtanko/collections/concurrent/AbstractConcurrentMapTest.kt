package dev.shtanko.collections.concurrent

import java.util.concurrent.ConcurrentHashMap
import org.jetbrains.lincheck.datastructures.ModelCheckingOptions
import org.jetbrains.lincheck.datastructures.Operation
import org.jetbrains.lincheck.datastructures.StressOptions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable

@DisabledIfEnvironmentVariable(named = "CI", matches = "true")
abstract class AbstractConcurrentMapTest<out T : MutableMap<Int, Int>>(private val map: T) {
    @Operation
    fun put(key: Int, value: Int) = map.put(key, value)

    @Operation
    fun get(key: Int) = map[key]

    @Operation
    fun remove(key: Int) = map.remove(key)

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
            .threads(2)
            .invocationsPerIteration(10)
            .check(this::class)
    }
}

class JavaConcurrentHashMapTest : AbstractConcurrentMapTest<ConcurrentHashMap<Int, Int>>(ConcurrentHashMap<Int, Int>())
