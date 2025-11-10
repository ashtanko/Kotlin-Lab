package dev.shtanko.algorithms.codingbat.string2

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class ZipZapTest<out T : ZipZap>(private val strategy: T) {
    private object InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations,
            context: ExtensionContext,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of("", ""),
            Arguments.of("zipXzap", "zpXzp"),
            Arguments.of("zipXzap", "zpXzp"),
            Arguments.of("zopzop", "zpzp"),
            Arguments.of("zpzp", "zpzp"),
            Arguments.of("zzzopzop", "zzzpzp"),
            Arguments.of("zibzap", "zibzp"),
            Arguments.of("zip", "zp"),
            Arguments.of("zi", "zi"),
            Arguments.of("z", "z"),
            Arguments.of("zzp", "zp"),
            Arguments.of("abcppp", "abcppp"),
            Arguments.of("azbcppp", "azbcppp"),
            Arguments.of("azbcpzpp", "azbcpzp"),
        )
    }

    @ArgumentsSource(InputArgumentsProvider::class)
    @ParameterizedTest
    fun `zip zap test`(str: String, expected: String) {
        assertThat(strategy.invoke(str)).isEqualTo(expected)
    }
}

class ZipZapIterativeTest : ZipZapTest<ZipZap>(ZipZap.Iterative)
