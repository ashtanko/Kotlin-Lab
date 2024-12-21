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

package dev.shtanko.kotlinlang.generics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CovarianceTest {

    @Test
    fun `copy copies elements from one array to another`() {
        val from = arrayOf(1, 2, 3)
        val to = arrayOfNulls<Any>(3)
        copy(from, to)
        assertEquals(arrayListOf(1, 2, 3), to.toList())
    }

    @Test
    fun `DepositBank produce returns Deposit instance`() {
        val bank: Bank<Deposit> = DepositBank()
        val result = bank.produce(100)
        assertEquals(Deposit::class, result::class)
    }

    @Test
    fun `AccountBank produce returns Account instance`() {
        val bank: Bank<Account> = AccountBank()
        val result = bank.produce(100)
        assertEquals(Account::class, result::class)
    }

    @Test
    fun `EmptySource nextT returns empty string`() {
        val source: Source<String> = EmptySource()
        assertEquals("", source.nextT())
    }

    @Test
    fun `demo returns Source of Any type`() {
        val source: Source<String> = EmptySource()
        val result = demo(source)
        assertEquals("", result.nextT())
    }
}
