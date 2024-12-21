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

package dev.shtanko.concurrency.coroutines.flow

import dev.shtanko.utils.fourth
import dev.shtanko.utils.second
import dev.shtanko.utils.third
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DataTest {

    @Test
    fun `skill test`() {
        assertThat(Skill.A.value).isEqualTo(1000)
        assertThat(Skill.B.value).isEqualTo(800)
        assertThat(Skill.C1.value).isEqualTo(650)
        assertThat(Skill.C.value).isEqualTo(500)
        assertThat(Skill.D.value).isEqualTo(300)
        assertThat(Skill.E.value).isEqualTo(100)
    }

    @Test
    fun `employee data class test, should be equal`() {
        val e1 = Employee(0, "", 0, Skill.A)
        val e2 = Employee(0, "", 0, Skill.A)
        assertThat(e1).isEqualTo(e2)
    }

    @Test
    fun `employee data class test, shouldn't be equal`() {
        val e1 = Employee(0, "", 0, Skill.A)
        val e2 = Employee(1, "", 0, Skill.A)
        assertThat(e1).isNotEqualTo(e2)
    }

    @Test
    fun `employee file class test, should be equal`() {
        val e1 = EmployeeFile(0, "", "")
        val e2 = EmployeeFile(0, "", "")
        assertThat(e1).isEqualTo(e2)
    }

    @Test
    fun `employee file class test, shouldn't be equal`() {
        val e1 = EmployeeFile(0, "", "")
        val e2 = EmployeeFile(1, "", "")
        assertThat(e1).isNotEqualTo(e2)
    }

    @Test
    fun `get employee files test`() {
        assertThat(getEmployeeFiles().first()).isEqualTo(EmployeeFile(0, "Addr0", "0"))
        assertThat(getEmployeeFiles().second()).isEqualTo(EmployeeFile(1, "Addr1", "1"))
        assertThat(getEmployeeFiles().third()).isEqualTo(EmployeeFile(2, "Addr2", "2"))
        assertThat(getEmployeeFiles().fourth()).isEqualTo(EmployeeFile(3, "Addr3", "3"))
    }

    @Test
    fun `get employee files size test`() {
        assertThat(getEmployeeFiles().size).isEqualTo(12)
        assertThat(getEmployeeFiles().isEmpty()).isFalse
    }
}
