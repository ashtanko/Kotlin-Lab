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

package dev.shtanko.concurrency.coroutines.flow

internal enum class Skill(val value: Int) {
    A(1000),
    B(800),
    C1(650),
    C(500),
    D(300),
    E(100),
}

internal data class Employee(val id: Int, val name: String, val salary: Int, val skill: Skill)
internal data class EmployeeFile(val id: Int, val address: String, val zipcode: String)

internal fun getEmployees() = listOf(
    Employee(0, "Jack", 75_000, Skill.C),
    Employee(1, "Jacob", 80_000, Skill.C1),
    Employee(2, "Liam", 120_000, Skill.B),
    Employee(3, "Matthew", 32_000, Skill.E),
    Employee(4, "Elijah", 35_000, Skill.E),
    Employee(5, "Joshua", 37_000, Skill.E),
    Employee(6, "Andrew", 30_000, Skill.E),
    Employee(7, "David", 42_000, Skill.D),
    Employee(8, "Benjamin", 132_000, Skill.B),
    Employee(9, "Logan", 250_000, Skill.A),
    Employee(10, "Christopher", 133_000, Skill.B),
    Employee(11, "Joseph", 29_000, Skill.E),
)

internal fun getEmployeeFiles() = listOf(
    EmployeeFile(0, "Addr0", "0"),
    EmployeeFile(1, "Addr1", "1"),
    EmployeeFile(2, "Addr2", "2"),
    EmployeeFile(3, "Addr3", "3"),
    EmployeeFile(4, "Addr4", "4"),
    EmployeeFile(5, "Addr5", "5"),
    EmployeeFile(6, "Addr6", "6"),
    EmployeeFile(7, "Addr7", "7"),
    EmployeeFile(8, "Addr8", "8"),
    EmployeeFile(9, "Addr9", "9"),
    EmployeeFile(10, "Addr10", "10"),
    EmployeeFile(11, "Addr11", "11"),
)
