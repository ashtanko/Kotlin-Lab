## 🧠 `ConcurrentLinkedQueue`

`ConcurrentLinkedQueue` is a thread-safe, non-blocking queue that is designed for high concurrency. 
It uses an **unbounded, lock-free** design to provide better performance under concurrent access than traditional blocking queues.

---

### 🔍 Key Features

- **Thread-safe**: Multiple threads can access the queue concurrently without synchronization.
- **Non-blocking**: Operations like `offer`, `poll`, and `peek` are designed to be lock-free.
- **FIFO order**: Maintains the first-in-first-out order of elements.
- **No capacity limit**: It is unbounded, meaning it can grow indefinitely (limited by memory).

---

### ✅ When to Use

- ✅ You need a **high-performance, thread-safe queue**.
- ✅ The queue will be used in **concurrent environments** with many threads producing and consuming data.
- ✅ You require **non-blocking behavior** for queue operations (no waiting for space or elements).

---

### ❌ When Not to Use

- ❌ If you need to block threads while waiting for elements to be available or space to be free (use `BlockingQueue` for that).
- ❌ If you have small or simple queue needs (a regular `Queue` implementation might suffice).

---

### 📌 Kotlin Example

```kotlin
import java.util.concurrent.ConcurrentLinkedQueue

fun main() {
    val queue = ConcurrentLinkedQueue<String>()

    // Adding elements to the queue
    queue.offer("Task 1")
    queue.offer("Task 2")
    queue.offer("Task 3")

    // Polling elements from the queue (removes and returns the element)
    println("Polled: ${queue.poll()}")  // Output: Task 1
    println("Polled: ${queue.poll()}")  // Output: Task 2

    // Peek at the front element without removing it
    println("Peek: ${queue.peek()}")    // Output: Task 3

    // Checking if the queue is empty
    println("Is queue empty? ${queue.isEmpty()}")  // Output: false

    // Safe concurrent access example
    val threads = List(5) { i ->
        Thread {
            queue.offer("Task from thread $i")
        }
    }

    threads.forEach { it.start() }
    threads.forEach { it.join() }

    // Polling all tasks after concurrent additions
    while (queue.isNotEmpty()) {
        println("Polled: ${queue.poll()}")
    }
}
```

---

### 💡 Summary Table

| Feature               | `ConcurrentLinkedQueue`     |
|-----------------------|-----------------------------|
| Thread-safe           | ✅ Yes                      |
| Non-blocking          | ✅ Yes (lock-free)          |
| FIFO order            | ✅ Yes                      |
| Capacity              | ❌ Unbounded (limited by memory) |
| Best use case         | ✅ High concurrency queues  |
