package dev.shtanko.concurrency.coroutines.channels

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test

class Example2Test {

    @ExperimentalCoroutinesApi
    @Test
    fun `simple test`() = runBlockingTest {
        val s = listOf(7, 2, 8, -9, 4, 0)
        val c = Channel<Int>()
        go { sum(s.subList(s.size / 2, s.size), c) }
        go { sum(s.subList(0, s.size / 2), c) }
        val x = c.receive()
        val y = c.receive()
        println("$x $y ${x + y}")
        assertEquals(12, x + y)
    }
}