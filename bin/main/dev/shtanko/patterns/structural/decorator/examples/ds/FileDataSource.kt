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

import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException

class FileDataSource(private val name: String) : DataSource {
    override fun writeData(data: String) {
        val file = File(name)
        try {
            FileOutputStream(file).use { fos -> fos.write(data.toByteArray(), 0, data.length) }
        } catch (ex: IOException) {
            println(ex.message)
        }
    }

    override fun readData(): String {
        var buffer: CharArray? = null
        val file = File(name)
        try {
            FileReader(file).use { reader ->
                buffer = CharArray(file.length().toInt())
                buffer?.let { reader.read(it) }
            }
        } catch (ex: IOException) {
            println(ex.message)
        }
        return String(buffer ?: charArrayOf())
    }
}
