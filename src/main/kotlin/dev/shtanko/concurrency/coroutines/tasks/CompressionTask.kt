@file:Suppress("MagicNumber")

package dev.shtanko.concurrency.coroutines.tasks

import kotlin.random.Random
import kotlinx.coroutines.yield

class CompressionTask(
    private val dataSize: Int = 100_000,
) : BaseTask<Double>(
    name = "Data Compression",
    description = "LZ77 compression of $dataSize bytes",
) {
    override suspend fun execute(): Double {
        val data = ByteArray(dataSize) { (Random.nextInt(26) + 'a'.code).toByte() }
        val compressed = mutableListOf<Triple<Int, Int, Byte>>() // (offset, length, next char)

        var i = 0
        while (i < data.size) {
            if (i % 1000 == 0) {
                yield()
                updateProgress(i.toFloat() / dataSize)
            }

            var maxLength = 0
            var maxOffset = 0
            val searchStart = maxOf(0, i - 4096) // Search window

            for (j in searchStart until i) {
                var length = 0
                while (i + length < data.size &&
                    length < 255 &&
                    data[j + length] == data[i + length]
                ) {
                    length++
                }

                if (length > maxLength) {
                    maxLength = length
                    maxOffset = i - j
                }
            }

            val nextChar = if (i + maxLength < data.size) data[i + maxLength] else 0
            compressed.add(Triple(maxOffset, maxLength, nextChar))
            i += maxLength + 1
        }

        val compressionRatio = compressed.size * 3.0 / dataSize
        return compressionRatio
    }
}
