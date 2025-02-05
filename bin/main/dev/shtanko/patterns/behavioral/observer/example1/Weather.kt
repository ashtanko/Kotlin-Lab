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

import org.slf4j.LoggerFactory

class Weather(
    currentWeather: WeatherType = WeatherType.SUNNY,
    observers: List<WeatherObserver> = ArrayList(),
) {
    private val _observers: MutableList<WeatherObserver> = observers.toMutableList()
    private var _currentWeather = currentWeather

    fun addObserver(obs: WeatherObserver) {
        _observers.add(obs)
    }

    fun removeObserver(obs: WeatherObserver) {
        _observers.remove(obs)
    }

    fun timePasses() {
        val enumValues = WeatherType.values()
        _currentWeather = enumValues[(_currentWeather.ordinal + 1) % enumValues.size]
        LOGGER.info("The weather changed to $_currentWeather.")
        notifyObservers()
    }

    private fun notifyObservers() {
        for (obs in _observers) {
            obs.update(_currentWeather)
        }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(Weather::class.java)
    }
}
