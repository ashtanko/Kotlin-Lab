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

package dev.shtanko.benchmark.jobs

import java.io.ByteArrayOutputStream
import java.io.File
import java.util.zip.Deflater
import java.util.zip.Inflater

/**
 * File compression benchmark job.
 *
 * This job compresses a large text file using the Deflater class and then decompresses it using the Inflater class.
 */
@Suppress("MagicNumber")
class FileCompressionJob : BenchmarkJob {
    override suspend fun invoke(vararg args: Any?) {
        val inputFile = File("large_text_file.txt")
        val outputFile = File("compressed_file.zip")

        // Compress the file
        val inputBytes = inputFile.readBytes()
        val deflater = Deflater()
        val byteArrayOutputStream = ByteArrayOutputStream(inputBytes.size)
        deflater.setInput(inputBytes)
        deflater.finish()
        val buffer = ByteArray(1024)
        while (!deflater.finished()) {
            val count = deflater.deflate(buffer)
            byteArrayOutputStream.write(buffer, 0, count)
        }
        deflater.end()
        outputFile.writeBytes(byteArrayOutputStream.toByteArray())

        // Decompress the file
        val decompressedBytes = ByteArray(inputBytes.size)
        val inflater = Inflater()
        inflater.setInput(byteArrayOutputStream.toByteArray())
        val decompressedData = inflater.inflate(decompressedBytes)
        inflater.end()
    }
}
