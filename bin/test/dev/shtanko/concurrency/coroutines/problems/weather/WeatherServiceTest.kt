/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.concurrency.coroutines.problems.weather

import dev.shtanko.concurrency.coroutines.problems.weather.model.AirQuality
import dev.shtanko.concurrency.coroutines.problems.weather.model.CurrentWeather
import dev.shtanko.concurrency.coroutines.problems.weather.model.DailyForecast
import dev.shtanko.concurrency.coroutines.problems.weather.model.WeatherForecast
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class WeatherServiceTest {
    @Test
    fun testFetchCurrentWeather() = runTest {
        val result = fetchCurrentWeather()
        Assertions.assertNotNull(result)
        Assertions.assertEquals(25.0, result.temperature)
        Assertions.assertEquals("Sunny", result.condition)
    }

    @Test
    fun testFetchWeatherForecast() = runTest {
        val result = fetchWeatherForecast()
        Assertions.assertNotNull(result)
        Assertions.assertTrue(result.daily.isNotEmpty())
        Assertions.assertEquals(3, result.daily.size)
        Assertions.assertEquals("Monday", result.daily[0].day)
        Assertions.assertEquals(24.0, result.daily[0].temperature)
        Assertions.assertEquals("Partly Cloudy", result.daily[0].condition)
    }

    @Test
    fun testFetchAirQuality() = runTest {
        val result = fetchAirQuality()
        Assertions.assertNotNull(result)
        Assertions.assertEquals(50, result.index)
        Assertions.assertEquals("Good", result.description)
    }

    @Test
    fun testDisplayWeatherData() {
        val currentWeather = CurrentWeather(25.0, "Sunny")
        val weatherForecast = WeatherForecast(
            listOf(
                DailyForecast("Monday", 24.0, "Partly Cloudy"),
                DailyForecast("Tuesday", 26.0, "Sunny"),
                DailyForecast("Wednesday", 23.0, "Rainy"),
            ),
        )
        val airQuality = AirQuality(50, "Good")

        val result = displayWeatherData(currentWeather, weatherForecast, airQuality)

        val expected = """
            Current Weather: 25.0°C, Sunny
            Weather Forecast:
            Monday: 24.0°C, Partly Cloudy
            Tuesday: 26.0°C, Sunny
            Wednesday: 23.0°C, Rainy
            Air Quality: 50 (Good)

        """.trimIndent()

        Assertions.assertEquals(expected, result)
    }
}
