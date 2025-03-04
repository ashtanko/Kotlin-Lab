/*
 * Designed and developed by 2025 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.junit

import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test

class AssumptionsTest {

    @Test
    fun testOnlyOnCiServer() {
        print("ENV: ${System.getenv("ENV")}")
        assumeTrue("CI" == System.getenv("ENV"))
    }

    @Test
    fun testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV" == System.getenv("ENV")) {
            "Aborting test: not on developer workstation"
        }
    }
}
