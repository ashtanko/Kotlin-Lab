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

package dev.shtanko.protocols.udp

import java.net.DatagramPacket
import java.net.DatagramSocket

@Suppress("MagicNumber")
fun main() {
    val socket = DatagramSocket(9876)
    val buffer = ByteArray(1024)

    while (true) {
        val packet = DatagramPacket(buffer, buffer.size)
        socket.receive(packet)
        val message = String(packet.data, 0, packet.length)
        println("RECEIVED: $message")

        val responseMessage = message.uppercase()
        val responsePacket = DatagramPacket(
            responseMessage.toByteArray(),
            responseMessage.length,
            packet.address,
            packet.port,
        )
        socket.send(responsePacket)
    }
}
