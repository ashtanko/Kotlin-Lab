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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class StrongPasswordCheckerTest {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `strong password checker test`(password: String, expected: Int) {
        val actual = strongPasswordChecker(password)
        assertEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = listOf(
            "" to 6,
            "1" to 5,
            "12" to 4,
            "123" to 3,
            "123456" to 2,
            "qwerty" to 2,
            "password123456" to 1,
            "QWERTY" to 2,
            "QWERTYuiopasdfghjkl;'[]zxcvbnm,./." to 15,
            "''K5b3Q" to 0,
            "BJFGy5Kqw" to 0,
            "3^ZCtXxN" to 0,
            "DZCRm2FG" to 0,
            "rZTbVpbd2zUh" to 0,
            "Y8fnqGEhUj45ab4" to 0,
            "c_H2`$`Kc4%3qcW*x" to 0,
            "2%LH5#=39qLwgf\$3" to 0,
            "M+^3Ddc*u9TdsuTruA59" to 0,
            "H5MYhKFBP+p8swN%#!`$`yJRXfM" to 7,
            "88tV^_#&Q?GdLk*%^3*bG2?%KzW3Ve" to 10,
            "e75X6nVtUxxaDvbcLyxt-Hs2XeHq?M^AK" to 13,
            "s_-JX*32e!LR*Bu*SqmqY6Mezf9M6JdweYA" to 15,
            "6fhA2G9Dq2K4ZTVruGL#5_@_y4X#WtTM_Ek@A" to 17,
            "BhatKNzL&UyLeyHgt-RME#nZ`$`pF6YJ2MaZV5dHPC" to 22,
            "mXtt7Y2&bwnsF*8xK`$`kUk%*ULh-5X6%^QYjhP_zPP_#" to 25,
            "_n6!5VM^8ek?3^FbPuSgXAZ53H=&=6wvhAhAEct*fXAg_" to 25,
            "V*uGe\$Tbf8?x&WwQQ*5xVTE\$hgHeTpgBD\$6bNQdDz-4ka+E8-x" to 30,
            "6##@uw%UCP7_aEAgF%JtrTa*MUdZq+5#9Smy=j2!4Jm29P%%#SH?8zs" to 35,
            "\$uLrU6RL2y!dGM+#6Bvz4yKj+#h\$ZVgQgyH3z929h=qK^vLHg%6Vs+L=jeV@" to 40,
            "ffc#2_vWFe9HzrTH&P*R63Hr9^Sp8f7uBNdSQef3FFU97T\$yc2vn^9P=9SD=kr@Fy" to 45,
        ).map { Arguments.of(it.first, it.second) }.stream()
    }
}
