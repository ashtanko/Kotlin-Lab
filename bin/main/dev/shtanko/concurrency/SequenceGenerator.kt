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

package dev.shtanko.concurrency

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class SequenceGenerator {
    private var currentValue = 0

    fun getNextSequence(): Int {
        currentValue += 1
        return currentValue
    }

    fun next() {
        currentValue += 1
    }

    fun prev() {
        currentValue -= 1
    }

    suspend operator fun invoke(nCoroutines: Int = 100, times: Int = 10, action: suspend () -> Unit) {
        coroutineScope {
            repeat(nCoroutines) {
                launch {
                    repeat(times) { action() }
                }
            }
        }
    }
}
