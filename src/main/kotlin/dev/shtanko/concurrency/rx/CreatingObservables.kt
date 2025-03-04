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

package dev.shtanko.concurrency.rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

object CreatingObservables {
    @JvmStatic
    fun main(args: Array<String>) {

        /**
         * Creates an Observable from scratch using an ObservableEmitter.
         * @description Allows creation of custom observables with manual control over emissions.
         * @use cases Useful for wrapping non-reactive APIs or creating custom sequences.
         */
        val createObservable = Observable.create<Int> { emitter ->
            emitter.onNext(1)
            emitter.onNext(2)
            emitter.onComplete()
        }
        createObservable.subscribe { println("Create: $it") }

        /**
         * Defers the creation of the Observable until a subscriber subscribes.
         * @description Defers the computation of the Observable source until subscription.
         * @use cases Use when the Observable depends on dynamic or changing state.
         */
        val deferObservable = Observable.defer {
            val value = System.currentTimeMillis()
            Observable.just(value)
        }
        deferObservable.subscribe { println("Defer: $it") }

        /**
         * Creates an Observable that emits no items and immediately completes.
         * @description Emits no items and completes immediately.
         * @use cases Use to represent an empty or no-result stream.
         */
        val emptyObservable = Observable.empty<Unit>()
        emptyObservable.subscribe { println("Empty: $it") }

        /**
         * Creates an Observable that emits an error.
         * @description Emits an error immediately.
         * @use cases Use for testing error-handling or representing a failure case.
         */
        val errorObservable = Observable.error<Int>(Throwable("An error occurred"))
        errorObservable.subscribe({ println("Error: $it") }, { println("Error: ${it.message}") })

        /**
         * Creates an Observable that emits items from a Callable.
         * @description Defers the computation of an item until subscription.
         * @use cases Use for single-operation computations or wrapping function calls.
         */
        val fromCallableObservable = Observable.fromCallable { 42 }
        fromCallableObservable.subscribe { println("FromCallable: $it") }

        /**
         * Creates an Observable from a Future.
         * @description Converts a Future into an Observable.
         * @use cases Integrate with APIs returning Future or CompletableFuture.
         */
        val future = Single.just(100).delay(1, TimeUnit.SECONDS).toFuture()
        val fromFutureObservable = Observable.fromFuture(future)
        fromFutureObservable.subscribe { println("FromFuture: $it") }

        /**
         * Creates an Observable from an Iterable.
         * @description Converts an Iterable (e.g., List) into an Observable.
         * @use cases Use to convert collections into reactive streams.
         */
        val fromIterableObservable = Observable.fromIterable(listOf("A", "B", "C"))
        fromIterableObservable.subscribe { println("FromIterable: $it") }

        /**
         * Creates an Observable from a Publisher.
         * @description Converts a Publisher into an Observable.
         * @use cases Interoperate with other reactive libraries or frameworks.
         */
        val fromPublisherObservable = Observable.fromPublisher<Int> { emitter ->
            emitter.onNext(10)
            emitter.onNext(20)
            emitter.onComplete()
        }
        fromPublisherObservable.subscribe { println("FromPublisher: $it") }

        /**
         * Creates an Observable that emits sequential numbers at fixed intervals.
         * @description Emits sequential numbers at a regular time interval.
         * @use cases Use for periodic tasks or heartbeat mechanisms.
         */
        val intervalObservable = Observable.interval(500, TimeUnit.MILLISECONDS).take(3)
        intervalObservable.subscribe { println("Interval: $it") }

        /**
         * Creates an Observable that emits a range of integers.
         * @description Emits a sequence of numbers in a specified range.
         * @use cases Use for creating numeric sequences.
         */
        val rangeObservable = Observable.range(1, 5)
        rangeObservable.subscribe { println("Range: $it") }

        /**
         * Creates an Observable that emits a range of long integers.
         * @description Emits a sequence of long integers in a specified range.
         * @use cases Use when large number ranges are required.
         */
        val rangeLongObservable = Observable.rangeLong(1L, 3)
        rangeLongObservable.subscribe { println("RangeLong: $it") }

        /**
         * Creates an Observable that emits a single item after a delay.
         * @description Emits a single value after a specified delay.
         * @use cases Use for delayed tasks or timeout scenarios.
         */
        val timerObservable = Observable.timer(1, TimeUnit.SECONDS)
        timerObservable.subscribe { println("Timer: $it") }
    }
}
