# Polymorphism Principle

## Overview

The Polymorphism principle is a crucial concept in object-oriented software design that promotes the use of common
interfaces and base classes to enable multiple, interchangeable implementations. By embracing polymorphism, software
systems become more flexible, extensible, and easier to maintain.

This README provides an explanation of the Polymorphism principle, its benefits, and how it can be applied in software
design.

## Principle Explanation

Polymorphism allows objects of different classes to be treated as instances of a common interface or base class. This
principle enables dynamic binding, where the specific method implementation is determined at runtime based on the actual
object type.

## Example Scenario

Consider a geometric shape application with different shapes (e.g., Circle, Rectangle, Triangle) and a `ShapeRenderer`
responsible for rendering them:

1. `Shape`: Base class or interface representing a geometric shape.
2. `Circle`, `Rectangle`, `Triangle`: Subclasses or implementations of the `Shape`.
3. `ShapeRenderer`: Renders various shapes.

The Polymorphism principle applies here:

- The `Shape` base class defines a common interface for different shapes.
- The `Circle`, `Rectangle`, and `Triangle` classes implement the `Shape` interface and provide specific
  implementations.
- The `ShapeRenderer` interacts with `Shape` objects without needing to know their specific types.

This allows for easy extensibility by adding new shapes without modifying the `ShapeRenderer`.

## Usage

To apply the Polymorphism principle effectively in your software design:

1. Identify areas where multiple related but distinct implementations can be grouped under a common interface or base
   class.
2. Define the common interface or base class that outlines the shared behavior.
3. Implement specific subclasses that provide unique implementations of the common behavior.
4. Interact with objects through the common interface or base class to leverage polymorphism.

## Benefits

Applying the Polymorphism principle offers several benefits:

- Improved extensibility: New implementations can be added without modifying existing code, promoting a modular and
  open-closed design.

- Simplified codebase: Code that interacts with objects through a common interface is more readable and less prone to
  errors.

- Flexibility: Polymorphism allows for runtime behavior determination, enhancing adaptability and reusability.

## Example Code

Here's a simplified Kotlin implementation demonstrating the Polymorphism principle:

```kotlin
interface Shape {
  fun area(): Double
}

class Circle(private val radius: Double) : Shape {
  override fun area(): Double = Math.PI * radius * radius
}

class Rectangle(private val width: Double, private val height: Double) : Shape {
  override fun area(): Double = width * height
}

class ShapeRenderer {
  fun render(shape: Shape) {
    println("Rendering shape with area: ${shape.area()}")
  }
}

// Usage
fun main() {
  val circle = Circle(5.0)
  val rectangle = Rectangle(3.0, 4.0)

  val shapeRenderer = ShapeRenderer()
  shapeRenderer.render(circle)
  shapeRenderer.render(rectangle)
}
```
