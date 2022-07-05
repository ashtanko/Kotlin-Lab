/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.di.multibindings

import dagger.Component
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

private const val FOO_VALUE = "foo"

@MapKey(unwrapValue = false)
annotation class MyComplexKey(val name: String, val implementingClass: KClass<*>, val thresholds: IntArray)

class Abc

@Module
object ComplexModuleA {
    @Provides
    @IntoMap
    @MyComplexKey(name = "abc", implementingClass = Abc::class, thresholds = [1, 5, 10])
    fun provideAbc1510Value(): String {
        return FOO_VALUE
    }
}

@Component(modules = [ComplexModuleA::class])
interface ComplexMapKeysMultibindingsComponent {
    fun myKeyStringMap(): Map<MyComplexKey?, String?>
}
