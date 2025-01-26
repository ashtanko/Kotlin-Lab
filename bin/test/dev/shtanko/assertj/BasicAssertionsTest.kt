/*
 * Copyright 2025 Oleksii Shtanko
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

package dev.shtanko.assertj

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BasicAssertionsTest {

    // region Equality Assertions
    @Test
    fun `isEqualTo example test`() {
        assertThat(2 + 2).isEqualTo(4)
    }

    @Test
    fun `isNotEqualTo example test`() {
        assertThat(2 + 2).isNotEqualTo(5)
    }
    // endregion

    // region Null/Non-null Assertions
    @Test
    fun `isNull example test`() {
        val nullableString: String? = null
        assertThat(nullableString).isNull()
    }

    @Test
    fun `isNotNull example test`() {
        val nullableString: String? = "1"
        assertThat(nullableString).isNotNull()
    }
    // endregion

    // region Boolean Assertions
    @Test
    fun `isTrue example test`() {
        assertThat(true).isTrue()
    }

    @Test
    fun `isFalse example test`() {
        assertThat(false).isFalse()
    }
    // endregion

    // region Collection Assertions
    @Test
    fun `isEmpty example test`() {
        assertThat(emptyList<String>()).isEmpty()
    }

    @Test
    fun `isNotEmpty example test`() {
        assertThat(listOf(1, 2, 3)).isNotEmpty()
    }

    @Test
    fun `contains example test`() {
        assertThat(listOf(1, 2, 3)).contains(2)
    }

    @Test
    fun `doesNotContain example test`() {
        assertThat(listOf(1, 2, 3)).doesNotContain(4)
    }

    @Test
    fun `hasSize example test`() {
        assertThat(listOf(1, 2, 3)).hasSize(3)
    }
    // endregion

    // region String Assertions

    @Test
    fun `string isEqualTo example test`() {
        assertThat("Kotlin").isEqualTo("Kotlin")
    }

    @Test
    fun `string contains example test`() {
        assertThat("Kotlin is awesome").contains("awesome")
    }

    @Test
    fun `string startsWith example test`() {
        assertThat("Kotlin").startsWith("Kot")
    }

    @Test
    fun `string endsWith example test`() {
        assertThat("Kotlin").endsWith("lin")
    }

    @Test
    fun `string matches example test`() {
        assertThat("Kotlin").matches("K.*n")
    }
    // endregion

    // region Number Assertions
    @Test
    fun `isGreaterThan example test`() {
        assertThat(5).isGreaterThan(3)
    }

    @Test
    fun `isLessThan example test`() {
        assertThat(2).isLessThan(5)
    }

    @Test
    fun `isBetween example test`() {
        assertThat(5).isBetween(3, 7)
    }
    // endregion

    // region Object Assertions
    @Test
    fun `isInstanceOf example test`() {
        assertThat("Hello").isInstanceOf(String::class.java)
    }

    @Test
    fun `isExactlyInstanceOf example test`() {
        assertThat("Hello").isExactlyInstanceOf(String::class.java)
    }
    // endregion
}
