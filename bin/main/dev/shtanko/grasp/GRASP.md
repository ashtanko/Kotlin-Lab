# GRASP Principles

## Overview

GRASP (General Responsibility Assignment Software Patterns) is a set of guidelines and principles for designing
object-oriented software systems. These principles help in assigning responsibilities to classes and objects in a way
that promotes good design, flexibility, and maintainability.

This README provides a brief explanation of the core GRASP principles and their importance in software design.

## Principles

### 1. Information Expert

The Information Expert principle suggests that a class should be responsible for performing a task if it has the
necessary information to do so. This helps in keeping the responsibilities well-contained and avoids unnecessary
coupling between classes.

### 2. Creator

The Creator principle dictates that a class should be responsible for creating instances of another class if it contains
or uses the other class. This prevents tight coupling and ensures that the creation of objects is managed appropriately.

### 3. Controller

The Controller principle assigns the responsibility of handling system events and coordinating actions to a class.
Controllers help in managing the flow of control and interactions between different components.

### 4. Low Coupling

Low Coupling aims to reduce the dependencies between classes. This principle suggests that classes should interact with
each other through well-defined interfaces, minimizing direct connections.

### 5. High Cohesion

High Cohesion advises that the responsibilities within a class should be closely related and focused. This principle
helps in creating classes that are easier to understand, maintain, and reuse.

### 6. Polymorphism

Polymorphism encourages the use of interfaces and abstract classes to allow multiple classes to implement common
behaviors. This promotes flexibility and extensibility in the system.

### 7. Pure Fabrication

The Pure Fabrication principle suggests creating artificial classes to facilitate design goals, even if they don't
represent real-world concepts. This can help in achieving better design and separation of concerns.

### 8. Indirection

Indirection promotes the use of intermediate classes or methods to decouple classes that should not depend directly on
each other. This helps in maintaining flexibility and avoiding unnecessary coupling.

## Usage

When designing a software system, consider applying the GRASP principles to achieve a modular, maintainable, and
flexible design. By assigning responsibilities based on these principles, you can create a well-structured and
understandable system architecture.

## Example

To better understand how to apply GRASP principles, let's consider a simple e-commerce system. We could use the
Information Expert principle to assign the responsibility of calculating the total cost of an order to the `Order`
class, as it has all the necessary information about the items and their prices.

Similarly, the Creator principle could guide us in assigning the responsibility of creating `Product` instances to
the `Catalog` class, which contains information about available products.

Remember that the effective application of these principles depends on the specific context and requirements of your
software project.

## Resources

- [GRASP: Patterns for Assigning Responsibilities](http://www.blackwasp.co.uk/grasp.aspx)
- [Applying UML and Patterns by Craig Larman](https://www.amazon.com/Applying-UML-Patterns-Introduction-Object-Oriented/dp/0131489062)
