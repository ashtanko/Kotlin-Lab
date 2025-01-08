/*
 * Copyright 2025 Oleksii Shtanko
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

@file:Suppress("MagicNumber")

package dev.shtanko.concurrency.rx

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableEmitter
import io.reactivex.rxjava3.core.FlowableOnSubscribe
import io.reactivex.rxjava3.schedulers.Schedulers

object RxJavaSample1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val names = listOf("John", "Jane", "Jack", "Jill")
        val namesFlowable = Flowable<String>.create(
            object : FlowableOnSubscribe<String> {
                override fun subscribe(emitter: FlowableEmitter<String>) {
                    println("Subscribe executes on: ${Thread.currentThread().name}")
                    names.forEach { name ->
                        emitter.onNext(name)
                    }
                    emitter.onComplete()
                }
            },
            BackpressureStrategy.BUFFER,
        )

        namesFlowable
            .observeOn(Schedulers.computation())
            .subscribeOn(Schedulers.io())
            .filter {
                println("Filter executes on: ${Thread.currentThread().name}")
                it != "Jack"
            }.observeOn(Schedulers.single())
            .map {
                println("Map executes on: ${Thread.currentThread().name}")
                "${it.first()} => ${it.last()}"
            }.forEach {
                println("For each executes on: ${Thread.currentThread().name}")
                println(it)
            }

        Thread.sleep(1000L)
    }
}
