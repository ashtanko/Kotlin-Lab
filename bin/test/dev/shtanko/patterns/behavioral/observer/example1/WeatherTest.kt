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

import dev.shtanko.patterns.utils.InMemoryAppender
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class WeatherTest {
    private lateinit var appender: InMemoryAppender

    @BeforeEach
    fun setUp() {
        appender = InMemoryAppender()
    }

    @AfterEach
    fun tearDown() {
        appender.stop()
    }

    @Test
    fun `add remove observer test`() {
        val observer = Mockito.mock(WeatherObserver::class.java)
        val weather = Weather()
        weather.addObserver(observer)
        Mockito.verifyNoInteractions(observer)

        weather.timePasses()
        assertThat(appender.lastMessage).isEqualTo("The weather changed to rainy.")

        weather.removeObserver(observer)
        weather.timePasses()
        assertThat(appender.lastMessage).isEqualTo("The weather changed to windy.")

        // Mockito.verifyNoMoreInteractions(observer)
        assertThat(appender.logSize).isEqualTo(2)
    }

    @Test
    fun `time passes test`() {
        val observer = Mockito.mock(WeatherObserver::class.java)
        val weather = Weather()
        weather.addObserver(observer)
        val inOrder = Mockito.inOrder(observer)
        val weatherTypes = WeatherType.values()
        for (i in 1..19) {
            weather.timePasses()
            inOrder.verify(observer).update(weatherTypes[i % weatherTypes.size])
        }

        Mockito.verifyNoMoreInteractions(observer)
    }
}
