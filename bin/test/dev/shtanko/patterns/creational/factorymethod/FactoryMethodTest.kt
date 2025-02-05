/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.patterns.creational.factorymethod

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FactoryMethodTest {

    @Test
    fun `greece currency test`() {
        val greeceCurrency = CurrencyFactory.currencyForCountry(Greece("")).code
        assertThat(greeceCurrency).isEqualTo("EUR")
    }

    @Test
    fun `usa currency test`() {
        val usaCurrency = CurrencyFactory.currencyForCountry(Country.USA).code
        assertThat(usaCurrency).isEqualTo("USD")
    }

    @Test
    fun `spain currency test`() {
        val usaCurrency = CurrencyFactory.currencyForCountry(Spain).code
        assertThat(usaCurrency).isEqualTo("EUR")
    }

    @Test
    fun `canada currency test`() {
        val usaCurrency = CurrencyFactory.currencyForCountry(Canada("")).code
        assertThat(usaCurrency).isEqualTo("CAD")
    }

    @Test
    fun `ukraine currency test`() {
        val usaCurrency = CurrencyFactory.currencyForCountry(Ukraine).code
        assertThat(usaCurrency).isEqualTo("UAH")
    }
}
