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

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    try {
        val currentWeatherDeferred = async { fetchCurrentWeather() }
        val weatherForecastDeferred = async { fetchWeatherForecast() }
        val airQualityDeferred = async { fetchAirQuality() }

        val currentWeather = currentWeatherDeferred.await()
        val weatherForecast = weatherForecastDeferred.await()
        val airQuality = airQualityDeferred.await()

        val data = displayWeatherData(currentWeather, weatherForecast, airQuality)
        println(data)
    } catch (e: CancellationException) {
        println("Failed to fetch weather data: ${e.message}")
    }
}
