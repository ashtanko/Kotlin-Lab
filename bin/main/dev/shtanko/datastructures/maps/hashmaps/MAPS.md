# Maps in Kotlin

| **Type**             | **Mutable** | **Thread-Safe** | **Order**                  | **Sorted** |
|----------------------|-------------|-----------------|----------------------------|------------|
| `HashMap`            | Yes         | No              | No                         | No         |
| `LinkedHashMap`      | Yes         | No              | Insertion Order            | No         |
| `TreeMap`            | Yes         | No              | Natural Ordering of Keys   | Yes        |
| `ConcurrentHashMap`  | Yes         | Yes             | No                         | No         |
| `Map` (Immutable)    | No          | No              | No                         | No         |
