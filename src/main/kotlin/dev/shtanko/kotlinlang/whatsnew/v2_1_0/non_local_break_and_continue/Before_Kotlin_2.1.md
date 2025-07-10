Before this feature, you'd have to do awkward flag checks:

```kotlin
for (item in data) {
    val skip = item.run {
        if (it == "SKIP") {
            println("Skipping...")
            true
        } else false
    }
    if (skip) continue
    // etc...
}
```
