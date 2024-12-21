# Indirection Principle

## Overview

The Indirection principle is a crucial concept in software design that emphasizes the use of intermediaries or wrappers
to manage complex interactions and reduce coupling between components. By introducing an additional layer of
indirection, software systems become more flexible, adaptable, and easier to maintain.

This README provides an explanation of the Indirection principle, its benefits, and how it can be applied in software
design.

## Principle Explanation

The Indirection principle promotes the use of intermediaries or "middleman" components to facilitate interactions
between other components. This intermediate layer helps manage complex interactions, reduces coupling, and enables
better encapsulation.

## Example Scenario

Consider an online booking system with the following classes:

1. `User`: Represents a user of the system.
2. `Booking`: Represents a booking made by a user.
3. `EmailService`: Handles sending email notifications to users.

The Indirection principle applies here:

- The `Booking` class interacts with the `EmailService` class indirectly through an intermediary class, such as
  a `NotificationManager`. This intermediary helps decouple the `Booking` class from the `EmailService` class, allowing
  for easier maintenance and future changes.

## Usage

To apply the Indirection principle effectively in your software design:

1. Identify complex interactions between components that may benefit from an intermediary layer.
2. Create intermediary classes or wrappers that encapsulate interactions and provide a clear interface.
3. Ensure that the use of intermediaries enhances maintainability and reduces coupling without introducing unnecessary
   complexity.

## Benefits

Applying the Indirection principle offers several benefits:

- Reduced coupling: Intermediary classes decouple components, making it easier to make changes to one component without
  affecting others.

- Enhanced maintainability: Complex interactions are managed by intermediaries, allowing for easier modification and
  extension.

- Improved flexibility: Indirection enables swapping out implementations or adding new features with minimal impact on
  existing components.

## Example Code

Here's a simplified Kotlin implementation demonstrating the Indirection principle:

```kotlin
class User(val name: String)

class Booking(val user: User, val date: String)

class EmailService {
  fun sendEmail(to: String, subject: String, content: String) {
    println("Sending email to: $to, Subject: $subject, Content: $content")
  }
}

class NotificationManager(private val emailService: EmailService) {
  fun notifyUser(user: User, message: String) {
    val userEmail = "${user.name}@example.com"
    emailService.sendEmail(userEmail, "Booking Notification", message)
  }
}

// Usage
fun main() {
  val user = User("Alice")
  val booking = Booking(user, "2023-08-10")

  val emailService = EmailService()
  val notificationManager = NotificationManager(emailService)

  val notificationMessage = "Your booking on ${booking.date} is confirmed."
  notificationManager.notifyUser(user, notificationMessage)
}
```
