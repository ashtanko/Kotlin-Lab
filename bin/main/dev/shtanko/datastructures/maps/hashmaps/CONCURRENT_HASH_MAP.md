Concurrent hash maps are a specialized form of hash maps designed to handle concurrent access by multiple 
threads efficiently. They provide thread-safe operations without requiring explicit synchronization, which can lead to 
performance bottlenecks. In computer science, concurrent hash maps are essential for building scalable and 
high-performance applications that require safe and efficient access to shared data structures.

### Key Concepts

1. **Thread Safety**: Concurrent hash maps ensure that multiple threads can safely read, write, and update the map 
concurrently without causing data corruption or inconsistencies.

2. **Fine-Grained Locking**: Instead of using a single lock for the entire map 
(which would be a performance bottleneck), concurrent hash maps typically use fine-grained locking. 
This means that different parts of the map can be locked independently, allowing for higher concurrency.

3. **Lock-Free Algorithms**: Some implementations use lock-free or non-blocking algorithms to manage concurrent access. 
These algorithms rely on atomic operations and are designed to avoid traditional locking mechanisms.

### Common Implementations

1. **Segmented Locking**: One common approach is to divide the hash map into segments, each with its own lock. 
Operations only lock the segment relevant to the key being accessed. This allows multiple threads to operate on 
different segments simultaneously without contention.

2. **Java's `ConcurrentHashMap`**:
   - **Segmented Locks**: In earlier versions of Java, `ConcurrentHashMap` used segmented locking. The map was divided 
        into segments, and each segment was locked independently.
   - **CAS Operations**: Modern versions of `ConcurrentHashMap` (Java 8 and later) use a combination of lock-free 
        techniques and fine-grained locking. They use Compare-And-Swap (CAS) operations for most updates, reducing 
        the need for locking.
   - **Node Locking**: Fine-grained locking is used at the level of individual bins (or nodes) within the hash map. 
        Only bins undergoing structural changes (like resizing or rehashing) are locked.

3. **Lock-Free Hash Tables**: Some implementations, like certain versions of the `ConcurrentSkipListMap`, 
        use lock-free techniques that rely on atomic operations to ensure thread safety without locking.

### Operations

1. **Insertion**: The key is hashed to find the appropriate segment or bin. Fine-grained locking or CAS operations 
ensure that the insertion is thread-safe. If the segment or bin is being modified, only that part is locked.

2. **Lookup**: Lookups are typically lock-free and involve hashing the key and accessing the appropriate segment or bin.
Since lookups do not modify the map, they can often proceed without locking.

3. **Deletion**: Similar to insertion, the key is hashed to locate the segment or bin. Fine-grained locking or CAS 
operations ensure that the deletion is thread-safe.
