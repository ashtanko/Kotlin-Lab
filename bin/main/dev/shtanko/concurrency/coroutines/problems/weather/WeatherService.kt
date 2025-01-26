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
import kotlinx.coroutines.delay

suspend fun fetchCurrentWeather(): CurrentWeather {
    delay(100) // Simulate network delay
    return CurrentWeather(25.0, "Sunny")
}

suspend fun fetchWeatherForecast(): WeatherForecast {
    delay(150) // Simulate network delay
    return WeatherForecast(
        listOf(
            DailyForecast("Monday", 24.0, "Partly Cloudy"),
            DailyForecast("Tuesday", 26.0, "Sunny"),
            DailyForecast("Wednesday", 23.0, "Rainy"),
        ),
    )
}

suspend fun fetchAirQuality(): AirQuality {
    delay(120) // Simulate network delay
    return AirQuality(50, "Good")
}

fun displayWeatherData(
    currentWeather: CurrentWeather,
    weatherForecast: WeatherForecast,
    airQuality: AirQuality,
): String {
    val result = StringBuilder()
    result.append("Current Weather: ${currentWeather.temperature}°C, ${currentWeather.condition}\n")
    result.append("Weather Forecast:\n")
    weatherForecast.daily.forEach { forecast ->
        result.append("${forecast.day}: ${forecast.temperature}°C, ${forecast.condition}\n")
    }
    result.append("Air Quality: ${airQuality.index} (${airQuality.description})\n")
    return result.toString()
}
