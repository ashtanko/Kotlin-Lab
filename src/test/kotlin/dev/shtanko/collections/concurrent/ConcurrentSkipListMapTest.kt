package dev.shtanko.collections.concurrent

import java.util.concurrent.ConcurrentSkipListMap
import org.jetbrains.lincheck.datastructures.ModelCheckingOptions
import org.jetbrains.lincheck.datastructures.Operation
import org.jetbrains.lincheck.datastructures.StressOptions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable

@Suppress("SwallowedException")
@DisabledIfEnvironmentVariable(named = "CI", matches = "true")
class ConcurrentSkipListMapTest {
    // A thread-safe, sorted map
    private val map = ConcurrentSkipListMap<Int, Int>()

    @Operation
    fun put(key: Int, value: Int) = map.put(key, value)

    @Operation
    fun get(key: Int) = map[key]

    @Operation
    fun remove(key: Int) = map.remove(key)

    @Operation
    fun firstKey(): Int? = try {
        map.firstKey()
    } catch (e: NoSuchElementException) {
        null
    }

    @Operation
    fun lastKey(): Int? = try {
        map.lastKey()
    } catch (e: NoSuchElementException) {
        null
    }

    @Test
    fun stressTest() {
        StressOptions()
            .iterations(100)
            .threads(3)
            .check(this::class)
    }

    @Test
    fun modelCheckingTest() {
        ModelCheckingOptions()
            .iterations(50)
            .threads(3)
            .check(this::class)
    }
}
