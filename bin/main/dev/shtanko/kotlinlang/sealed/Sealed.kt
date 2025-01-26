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

package dev.shtanko.kotlinlang.sealed

import java.io.File

sealed class Result<out S, out F> {
    abstract fun <R> map(func: (S) -> R): Result<R, F>
    abstract fun <R> mapFailure(func: (F) -> R): Result<S, R>
    abstract fun get(): S?
}

data class Success<out S, out F>(val success: S) : Result<S, F>() {
    override fun <R> map(func: (S) -> R): Result<R, F> = Success(func(success))
    override fun <R> mapFailure(func: (F) -> R): Result<S, R> = Success(success)
    override fun get(): S? = success
}

data class Failure<out S, out F>(val failure: F) : Result<S, F>() {
    override fun <R> map(func: (S) -> R): Result<R, F> = Failure(failure)
    override fun <R> mapFailure(func: (F) -> R): Result<S, R> = Failure(func(failure))
    override fun get(): S? = null
}

sealed interface Error
sealed class IOError : Error
class FileReadError(val f: File) : IOError()
object RuntimeError : Error
