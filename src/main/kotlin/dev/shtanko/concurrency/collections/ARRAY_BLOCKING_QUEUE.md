## 🧠 `ArrayBlockingQueue`

`ArrayBlockingQueue` is a bounded, **blocking queue** implementation in Java that uses an array to store the elements. 
It is a **thread-safe** queue designed for situations where a fixed-size queue is needed, and elements are added or removed in a **first-in-first-out (FIFO)** order.

---

### 🔍 Key Features

- **Thread-safe**: Provides safe concurrent access for multiple threads.
- **Blocking behavior**: It **blocks** threads when the queue is full or empty (for `put()` and `take()` operations).
- **Bounded capacity**: The queue has a fixed capacity, preventing unbounded growth.
- **FIFO order**: Elements are dequeued in a first-in-first-out order.

---

### ✅ When to Use

- ✅ You need a **bounded queue** with a **fixed capacity**.
- ✅ Threads should block when the queue is full or empty (e.g., producer-consumer pattern).
- ✅ You want to ensure that the queue doesn’t grow indefinitely, thereby controlling memory usage.

---

### ❌ When Not to Use

- ❌ If you need a **non-blocking** queue or one that grows dynamically (consider `ConcurrentLinkedQueue` or `LinkedBlockingQueue`).
- ❌ When you need **unbounded queue capacity** or if memory control isn’t a priority.

---

### 📌 Kotlin Example

```kotlin
import java.util.concurrent.ArrayBlockingQueue

fun main() {
    // Creating a bounded queue with a capacity of 3
    val queue = ArrayBlockingQueue<String>(3)

    // Producer thread: Adds elements to the queue
    val producer = Thread {
        try {
            queue.put("Task 1")  // Will block if the queue is full
            queue.put("Task 2")
            queue.put("Task 3")
            println("Producer finished adding tasks.")
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    // Consumer thread: Removes elements from the queue
    val consumer = Thread {
        try {
            println("Consumer started")
            println("Taken: ${queue.take()}")  // Will block if the queue is empty
            println("Taken: ${queue.take()}")
            println("Taken: ${queue.take()}")
            println("Consumer finished processing tasks.")
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    // Start producer and consumer threads
    producer.start()
    consumer.start()

    // Wait for threads to finish
    producer.join()
    consumer.join()
}
```

---

### 💡 Summary Table

| Feature               | `ArrayBlockingQueue`        |
|-----------------------|-----------------------------|
| Thread-safe           | ✅ Yes                      |
| Blocking operations   | ✅ Yes (blocks on full/empty) |
| Capacity              | ✅ Bounded (fixed size)     |
| FIFO order            | ✅ Yes                      |
| Best use case         | ✅ Producer-consumer pattern, controlling memory usage  |
