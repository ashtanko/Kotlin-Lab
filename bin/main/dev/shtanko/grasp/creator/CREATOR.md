# Creator Principle README

## Overview

The Creator principle is one of the GRASP (General Responsibility Assignment Software Patterns) principles that guides
the assignment of responsibilities in object-oriented software design. The principle suggests that a class should be
responsible for creating instances of another class if it contains or uses the other class.

This README provides a brief explanation of the Creator principle, its importance, and how it can be applied in software
design.

## Principle Explanation

The Creator principle focuses on ensuring that the class that possesses the necessary information or context for
creating another class is the one responsible for its instantiation. By doing so, the design promotes better
encapsulation, reduced coupling, and improved clarity in responsibility assignment.

## Example Scenario

Let's consider an e-commerce system where we have a `Product` class representing individual products and a `Catalog`
class responsible for managing products. The Creator principle applies as follows:

1. The `Product` class encapsulates information about a product, such as its name and price.

2. The `Catalog` class is responsible for creating instances of `Product` using a `createProduct` method. Since
   the `Catalog` class contains information about available products and their construction, it's well-suited to be the
   creator of `Product` instances.

## Usage

To apply the Creator principle effectively in your software design:

1. Identify classes that have the information or context required for creating instances of another class.

2. Ensure that the class responsible for creating instances of another class is the one that contains or uses the
   necessary information.

3. Encapsulate the creation logic within the creator class, providing appropriate methods for creating instances of the
   other class.

4. Avoid tightly coupling classes by letting the creator class handle the instantiation process.

## Benefits

Applying the Creator principle offers several benefits:

- Improved encapsulation: Creation logic is concentrated within a single class, enhancing encapsulation and reducing
  exposure of construction details.

- Reduced coupling: Creator classes decouple the client classes from the details of object creation, promoting
  flexibility and easier maintenance.

- Enhanced maintainability: Changes to the construction process can be localized within the creator class, minimizing
  the impact on other parts of the system.

## Example Code

Here's a simplified Kotlin implementation demonstrating the Creator principle:

```kotlin
class Product(val name: String, val price: Double)

class Catalog {
  fun createProduct(name: String, price: Double): Product {
    return Product(name, price)
  }
}

// Usage
val catalog = Catalog()
val product = catalog.createProduct("Sample Product", 29.99)
```
