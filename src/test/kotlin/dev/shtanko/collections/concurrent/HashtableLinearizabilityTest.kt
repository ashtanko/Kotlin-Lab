/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.collections.concurrent

import java.util.Hashtable
import org.jetbrains.lincheck.datastructures.IntGen
import org.jetbrains.lincheck.datastructures.Operation
import org.jetbrains.lincheck.datastructures.Param
import org.jetbrains.lincheck.datastructures.StressOptions
import org.jetbrains.lincheck.util.LoggingLevel
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test

@Param(name = "key", gen = IntGen::class, conf = "1:5")
internal class HashtableLinearizabilityTest {

    private val map: MutableMap<Int, Int> = Hashtable()

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
        assumeTrue(System.getenv("CI") != "true") {
            "Test skipped on CI to reduce runtime"
        }

        StressOptions()
            .iterations(2)
            .threads(3)
            .minimizeFailedScenario(false)
            .logLevel(LoggingLevel.INFO)
            .check(this::class)
    }
}
