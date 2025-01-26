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

internal fun interface Source<out T> {
    fun nextT(): T
}

internal class EmptySource : Source<String> {
    override fun nextT(): String {
        return ""
    }
}

internal fun demo(s: Source<String>): Source<Any> = s

internal fun interface Bank<out T> {
    fun produce(sum: Int): T
}

internal class DepositBank : Bank<Deposit> {
    override fun produce(sum: Int): Deposit {
        return Deposit()
    }
}

internal class AccountBank : Bank<Account> {
    override fun produce(sum: Int): Account {
        return Account()
    }
}

internal open class Account

internal class Deposit : Account()

fun copy(from: Array<out Any>, to: Array<Any?>) {
    assert(from.size == to.size)
    for (i in from.indices)
        to[i] = from[i]
}
