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

package dev.shtanko.algorithms.sorts

internal data class TestObject(val id: Int, val name: String) : Comparable<TestObject> {

    companion object {
        fun empty(): TestObject {
            return TestObject(0, "")
        }
    }

    override fun compareTo(other: TestObject): Int {
        val n = this.name.compareTo(other.name)
        return if (n == 0) this.id.compareTo(other.id) else n
    }
}
