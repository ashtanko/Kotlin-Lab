package dev.shtanko.algorithms.codingbat.string2

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class OneTwoTest<out T : OneTwo>(private val strategy: T) {
    private object InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments?>? = Stream.of(
            Arguments.of("abc", "bca"),
            Arguments.of("tca", "cat"),
            Arguments.of("tcagdo", "catdog"),
            Arguments.of("chocolate", "hocolctea"),
            Arguments.of("1234567890", "231564897"),
            Arguments.of("xabxabxabxabxabxabxab", "abxabxabxabxabxabxabx"),
            Arguments.of("abcdefx", "bcaefd"),
            Arguments.of("abcdefxy", "bcaefd"),
            Arguments.of("abcdefxyz", "bcaefdyzx"),
            Arguments.of("", ""),
            Arguments.of("x", ""),
            Arguments.of("xy", ""),
            Arguments.of("xyz", "yzx"),
            Arguments.of("abcdefghijklkmnopqrstuvwxyz1234567890", "bcaefdhigkljmnkpqostrvwuyzx231564897"),
            Arguments.of("abcdefghijklkmnopqrstuvwxyz123456789", "bcaefdhigkljmnkpqostrvwuyzx231564897"),
            Arguments.of("abcdefghijklkmnopqrstuvwxyz12345678", "bcaefdhigkljmnkpqostrvwuyzx231564"),
        )
    }

    @ArgumentsSource(InputArgumentsProvider::class)
    @ParameterizedTest
    fun `one two test`(str: String, expected: String) {
        val actual: String = strategy(str)
        assertThat(actual).isEqualTo(expected)
    }
}

class OneTwoChunkedTest : OneTwoTest<OneTwo>(OneTwo.Chunked)
class OneTwoIterativeTest : OneTwoTest<OneTwo>(OneTwo.Iterative)
