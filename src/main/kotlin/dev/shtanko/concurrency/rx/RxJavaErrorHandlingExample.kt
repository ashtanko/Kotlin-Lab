/*
 * Designed and developed by 2025 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.concurrency.rx

import io.reactivex.rxjava3.core.Observable
import java.io.IOException

object RxJavaErrorHandlingExample {
    @JvmStatic
    fun main(args: Array<String>) {
        // Example with onErrorReturn
        Observable.create<String> { emitter ->
            emitter.onNext("Task 1")
            emitter.onNext("Task 2")
            emitter.onError(IOException("Network error"))
            emitter.onNext("Task 3") // This will not be emitted
            emitter.onComplete()
        }.onErrorReturn { error ->
            // Handle error and provide fallback value
            println("Error caught: ${error.message}")
            "Fallback Task"
        }.subscribe(
            { item -> println("Received: $item") },
            { error -> println("Error: ${error.message}") },
            { println("Completed") },
        )

        // Example with onErrorResumeNext
        Observable.create<String> { emitter ->
            emitter.onNext("Task A")
            emitter.onError(RuntimeException("Something went wrong"))
            emitter.onNext("Task B") // This will not be emitted
        }.onErrorResumeNext { error: Throwable ->
            // Return a new Observable as a fallback
            println("Resuming with a new Observable after error: ${error.message}")
            Observable.just("Resumed Task 1", "Resumed Task 2")
        }.subscribe(
            { item -> println("Received: $item") },
            { error -> println("Error: ${error.message}") },
            { println("Completed") },
        )
    }
}
