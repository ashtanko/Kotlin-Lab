## 🧠 `ConcurrentLinkedDeque`

`ConcurrentLinkedDeque` is a thread-safe, non-blocking **double-ended queue** (deque) that allows elements to be added or removed from both ends of the queue concurrently. 
It provides a **lock-free** implementation for high-performance operations in concurrent environments.

---

### 🔍 Key Features

- **Thread-safe**: Allows multiple threads to access and modify the deque concurrently without requiring synchronization.
- **Non-blocking**: Provides lock-free, non-blocking operations for adding and removing elements from both ends of the deque.
- **FIFO Order**: Elements are dequeued in a **first-in-first-out** order, but since it’s a deque, elements can also be added and removed from both ends.
- **Unbounded**: It grows as needed (limited only by available memory).

---

### ✅ When to Use

- ✅ You need a **high-performance thread-safe deque** that allows **adding/removing elements** from both ends.
- ✅ You require **non-blocking behavior** for both ends of the deque in a **multi-threaded environment**.
- ✅ You have scenarios like **task scheduling**, **buffering**, or **job queues** that require fast access from both ends.

---

### ❌ When Not to Use

- ❌ If you need to block threads while waiting for elements (consider using `BlockingDeque` in that case).
- ❌ If you only need a simple queue or stack, consider using `ConcurrentLinkedQueue` or `Stack` instead.

---

### 📌 Kotlin Example

```kotlin
import java.util.concurrent.ConcurrentLinkedDeque

fun main() {
    val deque = ConcurrentLinkedDeque<String>()

    // Adding elements to both ends of the deque
    deque.offerFirst("Task 1")  // Adds at the front
    deque.offerLast("Task 2")   // Adds at the back
    deque.offerFirst("Task 3")  // Adds at the front
    deque.offerLast("Task 4")   // Adds at the back

    // Polling elements from both ends
    println("Polled from front: ${deque.pollFirst()}")  // Output: Task 3
    println("Polled from back: ${deque.pollLast()}")   // Output: Task 4

    // Peek at the front and back without removing
    println("Peek front: ${deque.peekFirst()}")  // Output: Task 1
    println("Peek back: ${deque.peekLast()}")    // Output: Task 2

    // Checking if the deque is empty
    println("Is deque empty? ${deque.isEmpty()}")  // Output: false

    // Safe concurrent access example
    val threads = List(5) { i ->
        Thread {
            deque.offerLast("Task from thread $i")
        }
    }

    threads.forEach { it.start() }
    threads.forEach { it.join() }

    // Polling all tasks after concurrent additions
    while (deque.isNotEmpty()) {
        println("Polled: ${deque.pollFirst()}")
    }
}
```

---

### 💡 Summary Table

| Feature               | `ConcurrentLinkedDeque`     |
|-----------------------|-----------------------------|
| Thread-safe           | ✅ Yes                      |
| Non-blocking          | ✅ Yes (lock-free)          |
| FIFO order            | ✅ Yes                      |
| Allows adding/removing from both ends | ✅ Yes          |
| Capacity              | ❌ Unbounded (limited by memory) |
| Best use case         | ✅ Task scheduling, job queues, buffers  |
