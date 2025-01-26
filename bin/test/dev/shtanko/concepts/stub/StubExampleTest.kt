/*
 * Copyright 2025 Oleksii Shtanko
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

package dev.shtanko.concepts.stub

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify

class StubExampleTest {
    private val orderRepository: OrderRepository = mock(OrderRepository::class.java)
    private val orderService = OrderService(orderRepository)

    @Test
    fun `should create order and return order id`() {
        // Arrange
        val order = Order(1, "customer1", listOf(OrderItem(1, "product1", 2)))
        // line defines a stub, it provides a predefined behavior for the mocked method
        `when`(orderRepository.save(order)).thenReturn(1L)

        // Act
        val orderId = orderService.createOrder(order)

        // Assert
        assertEquals(1L, orderId)
        verify(orderRepository, times(1)).save(order)
    }
}

private interface OrderRepository {
    fun save(order: Order): Long
}

private data class Order(
    val id: Int,
    val customer: String,
    val items: List<OrderItem>,
)

private data class OrderItem(
    val productId: Int,
    val name: String,
    val quantity: Int,
)

private class OrderService(private val orderRepository: OrderRepository) {

    fun createOrder(order: Order): Long {
        return orderRepository.save(order)
    }
}
