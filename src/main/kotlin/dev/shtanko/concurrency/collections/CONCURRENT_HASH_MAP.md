## 🧠 `ConcurrentHashMap` in Java and Kotlin

`ConcurrentHashMap` is a thread-safe and highly performant implementation of `Map` designed for concurrent access without needing to synchronize the entire map.

---

### 🔍 Key Features

- Allows **concurrent reads and writes** by segmenting the map internally.
- **Does not allow `null` keys or values**.
- **No need to synchronize** explicitly for most operations.
- Iterators are **weakly consistent** — they reflect some (not necessarily all) changes made during iteration and do **not** throw `ConcurrentModificationException`.

---

### ✅ When to Use

- ✅ You need a **high-performance thread-safe map**.
- ✅ Multiple threads **frequently read and write** concurrently.
- ✅ You're building multi-threaded services, caches, or registries.

---

### ❌ When Not to Use

- ❌ You need to store `null` values (not allowed).
- ❌ In single-threaded scenarios — prefer regular `HashMap`.

---

### 📌 Kotlin Example

```kotlin
import java.util.concurrent.ConcurrentHashMap

fun main() {
    val map = ConcurrentHashMap<String, String>()

    map["name"] = "Alice"
    map["city"] = "Wonderland"

    // Accessing elements
    println("Name: ${map["name"]}")
    println("City: ${map["city"]}")

    // Iterating over keys
    for (key in map.keys) {
        println("$key => ${map[key]}")
    }

    // Safe concurrent access (example using multiple threads)
    val threads = List(10) { i ->
        Thread {
            map["thread-$i"] = "value-$i"
        }
    }

    threads.forEach { it.start() }
    threads.forEach { it.join() }

    println("Map size after concurrency: ${map.size}")
}
```

---

### 💡 Summary Table

| Feature              | `ConcurrentHashMap`        |
|----------------------|-----------------------------|
| Thread-safe          | ✅ Yes                      |
| Null keys/values     | ❌ Not allowed              |
| Performance          | ✅ High under concurrency   |
| Iterators            | ✅ Weakly consistent        |
| Best use case        | ✅ Multi-threaded apps      |
