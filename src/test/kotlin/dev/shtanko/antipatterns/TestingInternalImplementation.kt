/*
 * Designed and developed by 2025 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.antipatterns

import io.mockk.Runs
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

private data class Order(val id: Int)

private interface OrderRepository {
    fun save(order: Order)

    fun getAllOrders(): List<Order>
}

private interface OrderService {
    fun placeOrder(order: Order)

    fun getOrders(): List<Order>
}

// Fake repository for testing
private class InMemoryOrderRepository : OrderRepository {
    private val orders = mutableListOf<Order>()

    override fun save(order: Order) {
        orders.add(order)
    }

    override fun getAllOrders(): List<Order> = orders.toList()
}

private class OrderServiceImpl(private val repository: OrderRepository) : OrderService {
    override fun placeOrder(order: Order) {
        repository.save(order) // Business logic
    }

    override fun getOrders(): List<Order> {
        return repository.getAllOrders()
    }
}

// Why Is This a Bad Approach?
// Problem: The test breaks when refactoring internal details.
class OrderServiceBadTest {
    @Test
    fun `should call repository save method`() {
        val repository = mockk<OrderRepository>()
        val orderService = OrderServiceImpl(repository)

        every { repository.save(any()) } just Runs

        orderService.placeOrder(Order(1))

        coVerify { repository.save(any()) } // Breaks if the implementation changes
    }
}

// Why Is This a Good Approach?
// * No mock abuse → Uses a simple in-memory repository instead of fragile mock verifications.
// * Encapsulation → We test behavior, not how OrderService works internally.
// * Easier refactoring → If OrderService changes its implementation, the test won’t break unnecessarily.
class OrderServiceGoodTest {
    private val repository = InMemoryOrderRepository()
    private val service = OrderServiceImpl(repository)

    @Test
    fun `should successfully place an order`() {
        val order = Order(1)

        service.placeOrder(order)

        assertTrue(repository.getAllOrders().contains(order)) // Verifying outcome, not implementation details
    }
}
