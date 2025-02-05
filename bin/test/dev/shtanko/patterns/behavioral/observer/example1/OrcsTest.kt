/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.patterns.behavioral.observer.example1

import java.util.function.Supplier

class OrcsTest : WeatherObserverTest<Orcs>(Supplier { Orcs() }) {
    override fun dataProvider(): Collection<Array<Any>> {
        return listOf(
            arrayOf(WeatherType.SUNNY, "The orcs are facing Sunny weather now"),
            arrayOf(WeatherType.RAINY, "The orcs are facing Rainy weather now"),
            arrayOf(WeatherType.WINDY, "The orcs are facing Windy weather now"),
            arrayOf(WeatherType.COLD, "The orcs are facing Cold weather now"),
        )
    }
}
