@file:Suppress("MagicNumber")

package dev.shtanko.concurrency.coroutines.tasks

import kotlin.random.Random
import kotlinx.coroutines.yield

class CryptographicTask(
    private val messageCount: Int = 1000,
) : BaseTask<Int>(
    name = "RSA Encryption",
    description = "Encrypting $messageCount messages",
) {
    override suspend fun execute(): Int {
        // Small RSA parameters for demonstration
        val p = 61
        val q = 53
        val n = p * q
        val phi = (p - 1) * (q - 1)
        val e = 17 // Public exponent
        val d = modInverse(e, phi) // Private exponent

        var encryptedCount = 0

        for (i in 0 until messageCount) {
            if (i % 50 == 0) {
                yield()
                updateProgress(i.toFloat() / messageCount)
            }

            val message = Random.nextInt(1, n)
            val encrypted = modPow(message, e, n)
            val decrypted = modPow(encrypted, d, n)

            if (decrypted == message) {
                encryptedCount++
            }
        }

        return encryptedCount
    }

    private fun modPow(base: Int, exp: Int, mod: Int): Int {
        var result = 1L
        var b = base.toLong()
        var e = exp

        while (e > 0) {
            if (e % 2 == 1) {
                result = (result * b) % mod
            }
            b = (b * b) % mod
            e /= 2
        }

        return result.toInt()
    }

    private fun modInverse(a: Int, m: Int): Int {
        for (x in 1 until m) {
            if ((a * x) % m == 1) {
                return x
            }
        }
        return 1
    }
}
