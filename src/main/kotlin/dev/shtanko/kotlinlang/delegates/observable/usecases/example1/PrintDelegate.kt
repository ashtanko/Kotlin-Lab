/*
 * Copyright 2022 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.kotlinlang.delegates.observable.usecases.example1

import kotlin.properties.Delegates

fun <T> printDelegate(init: T) = Delegates.observable(init) { prop, _, new ->
    println("${prop.name} = $new")
}

fun main() {
    var s1 by printDelegate("s1")
    s1 = "s2"
    s1 = "s3"

    foo = Foo()
    foo = Foo()

    var fooDelegate: Foo by printDelegate(Foo())
    fooDelegate = Foo()
    fooDelegate = Foo()

    bar = Bar()
    bar = Bar()
}
