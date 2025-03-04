# Information Expert Principle

## Overview

The Information Expert is a fundamental principle from the GRASP (General Responsibility Assignment Software Patterns)
patterns. This principle guides the assignment of responsibilities in object-oriented software design by suggesting that
a class should be responsible for performing a task if it has the necessary information to do so.

This README provides a concise explanation of the Information Expert principle, its benefits, and how it can be applied
in software design.

## Principle Explanation

The Information Expert principle focuses on assigning responsibilities to classes that possess the required information
and context to carry out those responsibilities effectively. By doing so, the design enhances encapsulation, promotes
loose coupling between classes, and creates a more modular and maintainable system.

## Example Scenario

Consider an e-commerce system with an `Order` class and an `Item` class:

1. The `Order` class has the responsibility of calculating the total cost of all items in the order.
2. The `Item` class encapsulates information about a product's name and price.

The Information Expert principle applies here:

- The `Order` class has access to all the necessary information (item prices) to perform the task of calculating the
  total cost accurately. Therefore, it is the most suitable candidate to handle this responsibility.

## Usage

To apply the Information Expert principle effectively in your software design:

1. Identify tasks or responsibilities within your system.
2. Determine the classes that have access to the required information to fulfill those responsibilities.
3. Assign responsibilities to classes that possess the necessary information, promoting strong encapsulation and
   reducing direct dependencies between classes.

## Benefits

Applying the Information Expert principle offers several benefits:

- Improved encapsulation: Responsibilities are assigned to classes that naturally have access to the required
  information, leading to better encapsulation and reduced exposure of internal details.

- Reduced coupling: Classes with necessary information are responsible for performing related tasks, reducing the need
  for direct interactions between unrelated classes.

- Enhanced maintainability: Changes to responsibilities can be localized within classes that have the required
  information, minimizing the impact on other parts of the system.

## Example Code

Here's a simplified Kotlin implementation demonstrating the Information Expert principle:

```kotlin
class Item(val name: String, val price: Double)

class Order {
  private val items = mutableListOf<Item>()

  fun addItem(item: Item) {
    items.add(item)
  }

  fun calculateTotalCost(): Double {
    var totalCost = 0.0
    for (item in items) {
      totalCost += item.price
    }
    return totalCost
  }
}

// Usage
fun main() {
  val order = Order()

  order.addItem(Item("Product A", 10.0))
  order.addItem(Item("Product B", 15.0))
  order.addItem(Item("Product C", 20.0))

  val totalCost = order.calculateTotalCost()
  println("Total cost of the order: $totalCost")
}
```
