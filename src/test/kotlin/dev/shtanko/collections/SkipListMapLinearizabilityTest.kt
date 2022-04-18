/*
 * Copyright 2020 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.collections

import org.jetbrains.kotlinx.lincheck.LinChecker
import org.jetbrains.kotlinx.lincheck.LoggingLevel
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.annotations.Param
import org.jetbrains.kotlinx.lincheck.paramgen.IntGen
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressCTest
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.jetbrains.kotlinx.lincheck.verifier.VerifierState
import org.junit.jupiter.api.Test
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.ConcurrentSkipListMap

@StressCTest(minimizeFailedScenario = false)
@Param(name = "key", gen = IntGen::class, conf = "1:5")
internal class SkipListMapLinearizabilityTest : VerifierState() {

    private val map: ConcurrentMap<Int, Int> = ConcurrentSkipListMap()

    @Operation
    fun put(@Param(name = "key") key: Int, value: Int): Int? {
        return map.put(key, value)
    }

    @Operation
    operator fun get(@Param(name = "key") key: Int): Int? {
        return map[key]
    }

    @Test
    internal fun test() {
        val opts = StressOptions()
            .iterations(5)
            .threads(3)
            .logLevel(LoggingLevel.INFO)
        LinChecker.check(SkipListMapLinearizabilityTest::class.java, opts)
    }

    override fun extractState(): Any {
        return map
    }
}
