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

import java.util.concurrent.Semaphore

class PrintInOrder {

    private val run2 = Semaphore(0)
    private val run3 = Semaphore(0)

    @Throws(InterruptedException::class)
    fun first(printFirst: Runnable) {
        printFirst.run()
        run2.release()
    }

    @Throws(InterruptedException::class)
    fun second(printSecond: Runnable) {
        run2.acquire()
        printSecond.run()
        run3.release()
    }

    @Throws(InterruptedException::class)
    fun third(printThird: Runnable) {
        run3.acquire()
        printThird.run()
    }
}
