/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.kotlinlang.delegates.observable

import kotlin.properties.Delegates
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ObservablePropertiesTest {

    var name: String by Delegates.observable("no name") { _, old, new ->
        println("$old -> $new")
    }

    @Test
    fun `simple test`() {
        assertThat(name).isEqualTo("no name")
        name = "kek"
        assertThat(name).isEqualTo("kek")
    }
}
