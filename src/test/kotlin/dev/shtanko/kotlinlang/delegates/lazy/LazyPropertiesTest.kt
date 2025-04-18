/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.kotlinlang.delegates.lazy

import dev.shtanko.concurrency.TestBase
import kotlinx.coroutines.async
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LazyPropertiesTest : TestBase() {
    @Test
    fun `simple test`() {
        assertThat(lazyValue).isEqualTo("Hello")
        assertThat(lazyValue).isEqualTo("Hello")
    }

    @Test
    fun `simple test2`() = runTest {
        val s1 = async { notThreadSafeLazy }
        val s2 = async { notThreadSafeLazy }

        assertThat(s1.await()).isEqualTo("World")
        assertThat(s2.await()).isEqualTo("World")
    }
}
