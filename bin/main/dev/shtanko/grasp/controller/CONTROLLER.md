# Controller Principle README

## Overview

The Controller principle is a vital concept within the GRASP (General Responsibility Assignment Software Patterns)
patterns. This principle focuses on assigning responsibilities to classes that act as controllers to manage interactions
and coordinate activities within a software system.

This README provides an explanation of the Controller principle, its significance, and how it can be applied in software
design, using an example from an online bookstore system.

## Principle Explanation

The Controller principle promotes the use of dedicated controller classes to manage and coordinate activities in a
system. Controllers act as intermediaries between user interfaces, business logic, and other components, ensuring a
clear separation of concerns and promoting modularity.

## Example Scenario: Online Bookstore

Consider an online bookstore system with the following classes:

1. `Book`: Represents individual books with attributes like title and price.
2. `ShoppingCart`: Manages the items added to a shopping cart and calculates the total price.
3. `CheckoutController`: Acts as the controller responsible for handling the checkout process.

In this example:

- The `ShoppingCart` class is responsible for managing items and calculating the total price.
- The `CheckoutController` class acts as a controller that coordinates the checkout process by interacting with
  the `ShoppingCart` and performing additional checkout logic.

The Controller principle applies here by utilizing the `CheckoutController` as an intermediary to manage the checkout
process and related interactions.

## Usage

To apply the Controller principle effectively in your software design:

1. Identify use cases or interactions that involve coordinating activities or managing a specific process.
2. Create dedicated controller classes that encapsulate the logic and interactions related to those activities.
3. Ensure that controllers act as intermediaries, facilitating communication between different components.

## Benefits

Applying the Controller principle offers several benefits:

- Clear separation of concerns: Controllers separate user interfaces, business logic, and other components, improving
  modularity and maintainability.

- Enhanced organization: Controller classes help organize and manage complex interactions within a system, improving
  readability and understandability.

- Reusability: Controllers can be reused across different parts of the application, promoting code reusability and
  reducing redundancy.

## Example Code

Here's a simplified Kotlin implementation demonstrating the Controller principle in the context of an online bookstore:

```kotlin
class Book(val title: String, val price: Double)

class ShoppingCart {
  // ... (implementation details)
}

class CheckoutController(private val shoppingCart: ShoppingCart) {
  fun processCheckout(): Double {
    // ... (checkout logic)
  }
}

// Usage example
fun main() {
  val book1 = Book("Book A", 15.0)
  val book2 = Book("Book B", 20.0)
  val book3 = Book("Book C", 25.0)

  val shoppingCart = ShoppingCart()
  shoppingCart.addItem(book1)
  shoppingCart.addItem(book2)
  shoppingCart.addItem(book3)

  val checkoutController = CheckoutController(shoppingCart)
  val totalAmount = checkoutController.processCheckout()

  println("Total amount to pay: $totalAmount")
}
```
