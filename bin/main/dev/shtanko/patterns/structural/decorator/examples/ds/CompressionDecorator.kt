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

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.Base64
import java.util.zip.Deflater
import java.util.zip.DeflaterOutputStream
import java.util.zip.InflaterInputStream

class CompressionDecorator(source: DataSource) : DataSourceDecorator(source) {

    companion object {
        private const val BYTES_PER_HALF_KB = 512
    }

    private var compressionLevel = 6

    override fun writeData(data: String) {
        super.writeData(compress(data) ?: "")
    }

    override fun readData(): String {
        return decompress(super.readData()) ?: ""
    }

    private fun compress(stringData: String): String? {
        val data = stringData.toByteArray()
        return try {
            val bout = ByteArrayOutputStream(BYTES_PER_HALF_KB)
            val dos = DeflaterOutputStream(bout, Deflater(compressionLevel))
            dos.write(data)
            dos.close()
            bout.close()
            Base64.getEncoder().encodeToString(bout.toByteArray())
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    private fun decompress(stringData: String): String? {
        val data: ByteArray = Base64.getDecoder().decode(stringData)
        return try {
            val inputStream: InputStream = ByteArrayInputStream(data)
            val iin = InflaterInputStream(inputStream)
            val bout = ByteArrayOutputStream(BYTES_PER_HALF_KB)
            var b: Int
            while (iin.read().also { b = it } != -1) {
                bout.write(b)
            }
            inputStream.close()
            iin.close()
            bout.close()
            String(bout.toByteArray())
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }
}
