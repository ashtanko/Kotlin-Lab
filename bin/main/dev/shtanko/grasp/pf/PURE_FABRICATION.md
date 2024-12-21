# Pure Fabrication Principle

## Overview

The Pure Fabrication principle is a significant concept in software design that encourages the creation of classes that
do not represent concepts within the problem domain but are instead introduced solely to facilitate achieving high
cohesion, low coupling, and other design goals. Pure Fabrication classes act as intermediaries, simplifying interactions
and promoting a more modular and maintainable architecture.

This README provides an explanation of the Pure Fabrication principle, its benefits, and how it can be applied in
software design.

## Principle Explanation

The Pure Fabrication principle allows for the introduction of classes or components that do not have a direct
counterpart in the real-world problem domain. These classes serve as "helpers" to achieve better design principles such
as low coupling and high cohesion, enhancing overall system structure.

## Example Scenario

Consider an online shopping cart system with the following classes:

1. `CartItem`: Represents an item added to the shopping cart.
2. `Product`: Represents a product available for purchase.
3. `CartCalculator`: Responsible for calculating the total cost of items in the shopping cart.

The Pure Fabrication principle applies here:

- The `CartCalculator` class is introduced as a Pure Fabrication. It calculates the total cost of items but doesn't
  directly represent a real-world concept. It helps achieve high cohesion by centralizing the calculation logic.

## Usage

To apply the Pure Fabrication principle effectively in your software design:

1. Identify areas where introducing a separate class can simplify interactions, improve cohesion, or reduce coupling.
2. Create Pure Fabrication classes that encapsulate specific functionality without direct ties to the problem domain.
3. Ensure that the introduced classes do not violate other GRASP principles and contribute positively to the overall
   design.

## Benefits

Applying the Pure Fabrication principle offers several benefits:

- Enhanced modularity: Pure Fabrication classes separate concerns, making it easier to maintain, extend, and refactor
  the system.

- Improved organization: Introducing helper classes reduces clutter in domain-related classes, improving readability and
  understanding.

- Flexibility: Pure Fabrication promotes a more flexible architecture by enabling the encapsulation of complex or
  utility functionality.

## Example Code

Here's a simplified Kotlin implementation demonstrating the Pure Fabrication principle:

```kotlin
class CartItem(val product: Product, val quantity: Int)

class Product(val name: String, val price: Double)

class CartCalculator {
  fun calculateTotal(cartItems: List<CartItem>): Double {
    var total = 0.0
    for (item in cartItems) {
      total += item.product.price * item.quantity
    }
    return total
  }
}

// Usage
fun main() {
  val product1 = Product("Product A", 10.0)
  val product2 = Product("Product B", 15.0)

  val cartItem1 = CartItem(product1, 2)
  val cartItem2 = CartItem(product2, 3)

  val cartItems = listOf(cartItem1, cartItem2)

  val cartCalculator = CartCalculator()
  val totalCost = cartCalculator.calculateTotal(cartItems)

  println("Total cost of the shopping cart: $totalCost")
}
```
