/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.algorithms.leetcode

object EmployeeImportance {

    private val employeeMap: MutableMap<Int, Employee> = hashMapOf()

    data class Employee(
        var id: Int = 0,
        var importance: Int = 0,
        var subordinates: List<Int> = listOf(),
    )

    fun getImportance(employees: List<Employee>, id: Int): Int {
        for (e in employees) {
            employeeMap[e.id] = e
        }
        return dfs(id)
    }

    private fun dfs(eid: Int): Int {
        val employee: Employee = employeeMap[eid] ?: return 0
        var ans = employee.importance
        for (subId in employee.subordinates) ans += dfs(subId)
        return ans
    }
}
