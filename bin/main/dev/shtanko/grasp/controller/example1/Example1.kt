/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.grasp.controller.example1

/**
 * Represents a controller responsible for handling the checkout process.
 *
 * This class coordinates the checkout process by interacting with a shopping cart and performing
 * additional checkout logic, such as payment processing.
 *
 * @property shoppingCart The shopping cart containing items to be checked out.
 */
class CheckoutController(private val shoppingCart: ShoppingCart) {

    /**
     * Processes the checkout for the items in the shopping cart.
     *
     * This method calculates the total price of the items in the shopping cart and performs any
     * additional checkout logic required.
     *
     * @return The total amount to be paid for the checkout.
     */
    fun processCheckout(): Double {
        val total = shoppingCart.calculateTotalPrice()
        // Perform additional checkout logic, e.g., payment processing
        return total
    }
}

/**
 * Represents a book available for purchase in the online bookstore.
 *
 * @property title The title of the book.
 * @property price The price of the book.
 */
class Book(val title: String, val price: Double)

/**
 * Represents a shopping cart that holds items to be purchased.
 */
class ShoppingCart {
    private val items = mutableListOf<Book>()

    /**
     * Adds a book to the shopping cart.
     *
     * @param book The book to be added to the cart.
     */
    fun addItem(book: Book) {
        items.add(book)
    }

    /**
     * Calculates the total price of all items in the shopping cart.
     *
     * @return The total price of all items in the cart.
     */
    fun calculateTotalPrice(): Double {
        var total = 0.0
        for (book in items) {
            total += book.price
        }
        return total
    }
}

private const val PRODUCT_A_PRICE = 10.0
private const val PRODUCT_B_PRICE = 20.0
private const val PRODUCT_C_PRICE = 25.0

fun main() {
    val book1 = Book("Book A", PRODUCT_A_PRICE)
    val book2 = Book("Book B", PRODUCT_B_PRICE)
    val book3 = Book("Book C", PRODUCT_C_PRICE)

    val shoppingCart = ShoppingCart()
    shoppingCart.addItem(book1)
    shoppingCart.addItem(book2)
    shoppingCart.addItem(book3)

    val checkoutController = CheckoutController(shoppingCart)
    val totalAmount = checkoutController.processCheckout()

    println("Total amount to pay: $totalAmount")
}
