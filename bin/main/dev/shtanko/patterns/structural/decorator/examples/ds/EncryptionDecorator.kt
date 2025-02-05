/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.patterns.structural.decorator.examples.ds

import java.util.Base64

class EncryptionDecorator(source: DataSource) : DataSourceDecorator(source) {
    override fun writeData(data: String) {
        super.writeData(encode(data))
    }

    override fun readData(): String {
        return decode(super.readData())
    }

    private fun encode(data: String): String {
        val result = data.toByteArray()

        for (i in result.indices) {
            result[i].inc()
        }
        return Base64.getEncoder().encodeToString(result)
    }

    private fun decode(data: String): String {
        val result: ByteArray = Base64.getDecoder().decode(data)
        for (i in result.indices) {
            result[i].dec()
        }
        return String(result)
    }
}
