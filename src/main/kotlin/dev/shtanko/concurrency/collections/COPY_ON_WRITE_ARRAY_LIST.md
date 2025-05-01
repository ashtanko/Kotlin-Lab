## 🧠 `CopyOnWriteArrayList`

`CopyOnWriteArrayList` is a thread-safe version of `ArrayList` found in `java.util.concurrent`. 
It works by **creating a new copy of the internal array** on every write operation (such as `add`, `remove`, or `set`).

---

### 🔍 How It Works

- **Reads** are not synchronized and are very fast.
- **Writes** (modifications) copy the entire array to ensure thread safety.
- **Iterators** are **fail-safe** and do **not** throw `ConcurrentModificationException`.

---

### ✅ When to Use

Use `CopyOnWriteArrayList` when:

- ✅ Reads **vastly outnumber** writes.
- ✅ You need **thread-safe iteration** without locking.
- ✅ The list is **mostly immutable** but updated occasionally.
- ✅ You can tolerate the cost of copying on writes.

---

### ❌ When *Not* to Use

Avoid it when:

- ❌ You have **frequent updates** (add/remove/set).
- ❌ You need **low memory usage** — copying can be expensive.
- ❌ Your app is **write-heavy** and performance-sensitive.

---

### 📌 Example

```kotlin
import java.util.concurrent.CopyOnWriteArrayList

fun main() {
    val list = CopyOnWriteArrayList<String>()
    list.add("A")
    list.add("B")

    for (s in list) {
        println(s)
        list.add("C") // Safe: no ConcurrentModificationException
    }
}

```

---

### 💡 Summary Table

| Feature                   | Behavior                          |
|--------------------------|-----------------------------------|
| Thread-safe              | ✅ Yes                            |
| Fast read access         | ✅ Yes                            |
| Costly writes            | ❌ Yes (due to array copying)     |
| Best use case            | Read-heavy, infrequently updated collections |
| Iterator behavior        | ✅ Fail-safe (no exceptions)      |
