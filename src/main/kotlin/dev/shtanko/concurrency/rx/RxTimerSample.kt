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
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.functions.Consumer
import java.util.Date
import java.util.Timer
import java.util.TimerTask

object RxTimerSample {
    @JvmStatic
    fun main(args: Array<String>) {
        val timerObservable = Observable<Date>.create(
            object : ObservableOnSubscribe<Date> {
                override fun subscribe(emitter: ObservableEmitter<Date>) {
                    val timer = Timer()
                    timer.scheduleAtFixedRate(
                        object : TimerTask() {
                            override fun run() {
                                emitter.onNext(Date())
                            }
                        },
                        0, 1000L,
                    )
                }
            },
        )

        timerObservable.subscribe(
            object : Consumer<Date> {
                override fun accept(t: Date) {
                    println(t.toString())
                }
            },
        )
    }
}
