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

package dev.shtanko.concurrency.rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.TestObserver
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference
import org.hamcrest.CoreMatchers.hasItems
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

internal class ObservableUnitTest {

    private var result: String = ""

    @Test
    internal fun `given string when just and subscribe then emits single item`() {
        val observable = Observable.just("Hello")
        observable.subscribe { str ->
            result = str
        }
        assertTrue(result == "Hello")
    }

    @Test
    internal fun `given array when from and subscribe then emits items`() {
        val letters = listOf("a", "b", "c", "d", "e", "f", "g")
        val observable = Observable.fromIterable(letters)
        observable.subscribe(
            {
                result += it
            },
            { obj: Throwable ->
                obj.printStackTrace()
            },
            {
                result += "_Complete"
            },
        )
        assertTrue(result == "abcdefg_Complete")
    }

    @Test
    internal fun `given Array when converts observable to blocking observable then return first element`() {
        val letters = listOf("a", "b", "c", "d", "e", "f", "g")
        val observable = Observable.fromIterable(letters)
        val blockingObservable: String = observable.blockingFirst()
        observable.subscribe(
            {
                result += it
            },
            { obj: Throwable ->
                obj.printStackTrace()
            },
            {
                result += "_Complete"
            },
        )
        assertTrue(result.first().toString() == blockingObservable)
    }

    @Test
    internal fun `given array when map and subscribe then return capital letters`() {
        val letters = listOf("a", "b", "c", "d", "e", "f", "g")
        Observable.fromIterable(letters).map {
            it.uppercase()
        }.subscribe { letter ->
            result += letter
        }
        assertTrue(result == "ABCDEFG")
    }

    @Test
    internal fun `given array when scan and subscribe then return the sum of all letters`() {
        val letters = listOf("a", "b", "c")
        Observable.fromIterable(letters)
            .scan(StringBuilder(), StringBuilder::append)
            .subscribe { total ->
                result += total.toString()
            }
        assertTrue(result == "aababc")
    }

    @Test
    internal fun `given array of numbers when group by then create two groups based on parity`() {
        val numbers = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val evens = mutableListOf("")
        val odds = mutableListOf("")
        Observable.fromIterable(numbers).groupBy {
            if (it % 2 == 0) "EVEN" else "ODD"
        }.subscribe { group ->
            group.subscribe { number ->
                if (group.key == "EVEN") {
                    evens[0] += "$number"
                } else {
                    odds[0] += "$number"
                }
            }
        }
        assertTrue(evens[0] == "0246810")
        assertTrue(odds[0] == "13579")
    }

    @Test
    internal fun `given array of numbers when filter then get all odd numbers`() {
        val numbers = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        Observable.fromIterable(numbers).filter {
            it % 2 == 1
        }.subscribe {
            result += it
        }
        assertTrue(result == "13579")
    }

    @Test
    internal fun `given empty observable when default if empty then get default message`() {
        Observable.empty<String>().defaultIfEmpty("Observable is empty")
            .subscribe {
                result += it
            }
        assertTrue(result == "Observable is empty")
    }

    @Test
    internal fun `given observable from array when take while then get sum of numbers from condition`() {
        val numbers = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val sum = intArrayOf(0)
        Observable.fromIterable(numbers).takeWhile {
            it < 5
        }.subscribe {
            sum[0] += it
        }
        assertTrue(sum[0] == 10)
    }

    @Test
    internal fun `zip operator test`() {
        val numbers = Observable.just(1, 2, 3, 4, 5)
        val letters = Observable.just("a", "b", "c", "d", "e")
        Observable.zip(
            numbers,
            letters,
        ) { num: Int, c: String ->
            "$num$c"
        }.subscribe {
            result += it
        }
        assertTrue(result == "1a2b3c4d5e")
    }

    @Test
    internal fun `merge operator test`() {
        val numbers = Observable.just(1, 2, 3, 4, 5)
        val letters = Observable.just("a", "b", "c", "d", "e")
        Observable.merge(numbers, letters).subscribe {
            result += it
        }
        assertTrue(result == "12345abcde")
    }

    @Test
    internal fun `concat operator test`() {
        val numbers = Observable.just(1, 2, 3, 4, 5)
        val letters = Observable.just("a", "b", "c", "d", "e")
        Observable.concat(numbers, letters).subscribe {
            result += it
        }
        assertTrue(result == "12345abcde")
    }

    @Test
    internal fun `combine latest operator test`() {
        val numbers = Observable.just(1, 2, 3, 4, 5)
        val letters = Observable.just("a", "b", "c", "d", "e")
        Observable
            .combineLatest(
                numbers,
                letters,
            ) { num: Int, c: String ->
                "$num$c"
            }.subscribe {
                result += it
            }
        assertTrue(result == "5a5b5c5d5e")
    }

    @Test
    internal fun `scan test`() {
        Observable
            .just(0, 1, 2, 3)
            .scan { t1, t2 -> t1 + t2 }
            .subscribe {
                result += it
            }
        println(result)
        assertTrue(result == "0136")
    }

    // TODO
//    @Test
//    internal fun `task test`() {
//        val numbers = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//        Observable
//            .fromIterable(numbers)
//            .map {
//                it * 2
//            }.map {
//                print("square: $it")
//                it * 3
//                "$it"
//            }
//            .subscribe(
//                ::println,
//                Throwable::printStackTrace
//            ) {
//                println("Done!")
//            }
//    }

    @Test
    internal fun `do on error`() {
        val r = AtomicReference<Throwable?>()
        val t: Throwable?
        try {
            Observable.error<String>(RuntimeException("an error"))
                .doOnError { v -> r.set(v) }.blockingSingle()
            fail {
                "expected exception, not a return value"
            }
        } catch (e: Throwable) {
            t = e
        }

        assertNotNull(t)
        assertEquals(t, r.get())
    }

    @Test
    internal fun `do on completed`() {
        val r = AtomicBoolean()
        val output = Observable
            .just("one")
            .doOnComplete { r.set(true) }.blockingSingle()

        assertEquals("one", output)
        assertTrue(r.get())
    }

    @Test
    internal fun `zip with operator test`() {
        val letters = listOf("A", "B", "C", "D")
        val results: MutableList<String> = ArrayList()
        val observable =
            Observable
                .fromIterable(letters)
                .zipWith(
                    Observable.range(
                        1,
                        Int.MAX_VALUE,
                    ),
                ) { str: String, idx: Int ->
                    "$idx-$str"
                }

        observable.subscribe(results::add)
        assertThat(results, notNullValue())
        assertThat(results, hasItems("1-A", "2-B", "3-C", "4-D"))
    }

    @Test
    internal fun `zip with operator using test subscriber test`() {
        val subscriber: TestObserver<Int> = TestObserver()
        val observable = Observable
            .fromIterable(listOf(1, 2, 3, 4, 5))
            .zipWith(
                Observable.range(
                    1,
                    Int.MAX_VALUE,
                ),
            ) { a: Int, b: Int ->
                a * b
            }
        observable.subscribeWith(subscriber)
        subscriber.apply {
            assertComplete()
            assertNoErrors()
            assertValueCount(5)
        }
        assertThat(subscriber.values(), hasItems(1, 4, 9, 16, 25))
    }

    @Test
    internal fun `zip with operator exception test`() {
        val subscriber: TestObserver<Int> = TestObserver()
        val observable = Observable
            .fromIterable(listOf(4))
            .zipWith(
                Observable.range(
                    1,
                    2,
                ),
            ) { a: Int, b: Int ->
                a * b
            }
            .concatWith(Observable.error(RuntimeException("error in Observable")))
        observable.subscribeWith(subscriber)
        subscriber.assertError(RuntimeException::class.java)
        subscriber.assertNotComplete()
    }

    @Test
    internal fun `concat map test`() {
        val subscriber: TestObserver<String> = TestObserver()
        val numbers = Observable.fromIterable(listOf(1, 2, 3))
        val observable = Observable
            .fromIterable(listOf("A", "B", "C"))
            .concatMap { s ->
                numbers.map { num ->
                    "$s$num"
                }
            }
        observable.subscribeWith(subscriber)
        subscriber.apply {
            assertComplete()
            assertNoErrors()
            assertValueCount(9)
        }
        assertThat(subscriber.values(), hasItems("A1", "A2", "A2", "B1", "B2", "B3", "C1", "C2", "C3"))
    }

    @Test
    internal fun `concat map 2 test`() {
        val subscriber: TestObserver<String> = TestObserver()
        val observable = Observable
            .fromIterable(listOf(1, 2, 3))
            .concatMap { n ->
                Observable.just("$n")
            }
        observable.subscribeWith(subscriber)
        subscriber.apply {
            assertComplete()
            assertNoErrors()
            assertValueCount(3)
        }
        assertThat(subscriber.values(), hasItems("1", "2", "3"))
    }

    @Test
    internal fun `flat map test`() {
        val subscriber: TestObserver<String> = TestObserver()
        val observable = Observable.fromIterable(listOf("Q", "W")).flatMap { s ->
            Observable.just(1, 2).map { n ->
                "$n$s"
            }
        }
        observable.subscribeWith(subscriber)
        subscriber.apply {
            assertComplete()
            assertNoErrors()
            assertValueCount(4)
        }
        assertThat(subscriber.values(), hasItems("1Q", "2Q", "1W", "2W"))
    }

    @Test
    internal fun `flat map 2 test`() {
        val subscriber: TestObserver<String> = TestObserver()
        val observable = Observable.fromIterable(listOf(1, 2)).flatMap { n ->
            Observable.just("$n")
        }
        observable.subscribeWith(subscriber)
        subscriber.apply {
            assertComplete()
            assertNoErrors()
            assertValueCount(2)
        }
        assertThat(subscriber.values(), hasItems("1", "2"))
    }

    // Combining Observables
    @Test
    internal fun `startWith test`() {
        val subscriber: TestObserver<String> = TestObserver()
        val observable = Observable.just("Spock", "McCoy")
            .startWithItem("Kirk")
        observable.subscribeWith(subscriber)
        subscriber.apply {
            assertComplete()
            assertNoErrors()
            assertValueCount(3)
        }
        assertThat(subscriber.values(), hasItems("Kirk", "Spock", "McCoy"))
    }

    @Test
    internal fun `merge test`() {
        val subscriber: TestObserver<Int> = TestObserver()
        val observable = Observable.just(1, 2, 3)
            .mergeWith(Observable.just(4, 5, 6))
        observable.subscribeWith(subscriber)
        subscriber.apply {
            assertComplete()
            assertNoErrors()
            assertValueCount(6)
        }
        assertThat(subscriber.values(), hasItems(1, 2, 3, 4, 5, 6))
    }
}
