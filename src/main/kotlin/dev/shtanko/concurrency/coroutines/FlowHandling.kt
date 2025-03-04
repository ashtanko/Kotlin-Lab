/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.concurrency.coroutines

import java.lang.Error
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

@Suppress("TooGenericExceptionThrown")
fun main() = runBlocking {
    val flow = flow {
        emit(1)
        throw Error("Exception emitted")
    }

    flow.catch { e ->
        println("Caught exception: $e") // Handling the emitted exception
    }.collect { value ->
        println(value) // This line won't be reached due to the thrown exception
    }
}
