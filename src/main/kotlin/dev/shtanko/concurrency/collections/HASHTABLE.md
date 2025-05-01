## 🧠 `Hashtable`

`Hashtable` is a legacy class in Java used to store key-value pairs, similar to `HashMap`, 
but it is **synchronized** and **thread-safe**.

---

### 🔍 Key Features

- Stores data as **key-value pairs**.
- **Thread-safe**: All public methods are synchronized.
- **Does not allow `null` keys or values**.
- Iterators are **not fail-fast** (may throw `ConcurrentModificationException`).
- Slower than `HashMap` due to synchronization overhead.

---

### 📌 Example

```kotlin
import java.util.Hashtable

fun main() {
    val table = Hashtable<String, String>()

    table["name"] = "Alice"
    table["city"] = "Wonderland"

    // Accessing elements
    println("Name: ${table["name"]}")
    println("City: ${table["city"]}")

    // Iterating over keys
    for (key in table.keys) {
        println("$key => ${table[key]}")
    }
}
```

---

### ✅ When to Use `Hashtable`

- You need a **thread-safe map** and are working with legacy systems.
- You want **simple synchronization** without manually wrapping a `Map`.

---

### ❌ When *Not* to Use

- ❌ Prefer `ConcurrentHashMap` for better concurrency and performance.
- ❌ Avoid in new codebases — use `HashMap` with `Collections.synchronizedMap()` or other modern concurrency utilities.

---

### 💡 Summary Table

| Feature             | `Hashtable`               |
|--------------------|---------------------------|
| Thread-safe         | ✅ Yes                    |
| Allows null key     | ❌ No                     |
| Allows null value   | ❌ No                     |
| Performance         | ❌ Slower (synchronized)  |
| Fail-fast iterator  | ❌ No                     |
| Preferred today?    | ❌ No (use `ConcurrentHashMap`) |
