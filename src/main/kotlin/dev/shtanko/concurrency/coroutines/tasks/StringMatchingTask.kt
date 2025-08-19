@file:Suppress("MagicNumber")

package dev.shtanko.concurrency.coroutines.tasks

import kotlinx.coroutines.yield

class StringMatchingTask(
    private val textSize: Int = 1_000_000,
    private val patternSize: Int = 100,
) : BaseTask<Int>(
    name = "String Pattern Matching",
    description = "Finding patterns in $textSize characters",
) {
    override suspend fun execute(): Int {
        val text = buildString {
            repeat(textSize) {
                append(('a'..'z').random())
            }
        }

        val pattern = buildString {
            repeat(patternSize) {
                append(('a'..'z').random())
            }
        }

        // Use KMP algorithm for pattern matching
        return kmpSearch(text, pattern)
    }

    private suspend fun kmpSearch(text: String, pattern: String): Int {
        val lps = computeLPSArray(pattern)
        var matches = 0
        var i = 0 // index for text
        var j = 0 // index for pattern

        while (i < text.length) {
            if (i % 10000 == 0) {
                yield()
                updateProgress(i.toFloat() / text.length)
            }

            if (pattern[j] == text[i]) {
                i++
                j++
            }

            if (j == pattern.length) {
                matches++
                j = lps[j - 1]
            } else if (i < text.length && pattern[j] != text[i]) {
                if (j != 0) {
                    j = lps[j - 1]
                } else {
                    i++
                }
            }
        }

        return matches
    }

    private fun computeLPSArray(pattern: String): IntArray {
        val lps = IntArray(pattern.length)
        var len = 0
        var i = 1

        while (i < pattern.length) {
            if (pattern[i] == pattern[len]) {
                len++
                lps[i] = len
                i++
            } else {
                if (len != 0) {
                    len = lps[len - 1]
                } else {
                    lps[i] = 0
                    i++
                }
            }
        }

        return lps
    }
}
