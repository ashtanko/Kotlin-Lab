/*
 * Copyright 2022 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.collections.concurrent

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import org.jetbrains.kotlinx.lincheck.LinChecker
import org.jetbrains.kotlinx.lincheck.LoggingLevel
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.annotations.Param
import org.jetbrains.kotlinx.lincheck.paramgen.IntGen
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressCTest
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.jetbrains.kotlinx.lincheck.verifier.VerifierState
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled("Requires a lot of time to execute")
@StressCTest(minimizeFailedScenario = false)
@Param(name = "key", gen = IntGen::class, conf = "1:5")
class ArrayBlockingQueueLinearizabilityTest : VerifierState() {

    private val queue: BlockingQueue<Int> = ArrayBlockingQueue(10)

    @Operation
    fun poll(): Int? {
        return queue.poll()
    }

    @Operation
    fun offer(value: Int): Boolean {
        return queue.offer(value)
    }

    override fun extractState() = queue

    @Test
    internal fun test() {
        val opts = StressOptions()
            .iterations(2)
            .threads(3)
            .logLevel(LoggingLevel.INFO)
        LinChecker.check(ArrayBlockingQueueLinearizabilityTest::class.java, opts)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as ArrayBlockingQueueLinearizabilityTest

        return queue == other.queue
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + queue.hashCode()
        return result
    }
}
