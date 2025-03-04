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

@file:Suppress("MagicNumber")
package dev.shtanko.concurrency.rx.operators

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

object RxJavaMapOperators {

    /**
     * Maps each emitted item into an Observable, then merges the results into a single Observable.
     * Order of emissions is not guaranteed.
     *
     * Use Case:
     * When you need to process multiple tasks in parallel and don't care about the order of the results.
     */
    object FlatMapExample {
        @JvmStatic
        fun main(args: Array<String>) {
            Observable.just("Task1", "Task2", "Task3")
                .flatMap { task ->
                    Observable.just("Processing $task")
                        .delay((1..3).random().toLong(), TimeUnit.SECONDS) // Simulate async processing
                }
                .subscribe { result -> println(result) }

            Thread.sleep(5000) // Allow time for emissions
        }
    }

    /**
     * Maps each emitted item into an Observable, unsubscribes from the previous Observable, and subscribes to the
     * latest one.
     * Only the most recent observable's emissions are considered.
     *
     * Use Case:
     * Useful in search or user-input scenarios where only the latest result is needed, e.g., debounce-like behavior.
     */
    object SwitchMapExample {
        @JvmStatic
        fun main(args: Array<String>) {
            Observable.just("Search1", "Search2", "Search3")
                .switchMap { query ->
                    Observable.just("Fetching results for $query")
                        .delay(1, TimeUnit.SECONDS) // Simulate network delay
                }
                .subscribe { result -> println(result) }

            Thread.sleep(2000) // Allow time for emissions
        }
    }

    /**
     * Maps each emitted item into an Observable and concatenates the results into a single Observable.
     * Processes items sequentially and preserves the order.
     *
     * Use Case:
     * When order matters, or when operations must be performed sequentially.
     */
    object ConcatMapExample {
        @JvmStatic
        fun main(args: Array<String>) {
            Observable.just("Task1", "Task2", "Task3")
                .concatMap { task ->
                    Observable.just("Processing $task")
                        .delay(1, TimeUnit.SECONDS) // Simulate async processing
                }
                .subscribe { result -> println(result) }

            Thread.sleep(4000) // Allow time for emissions
        }
    }
}
