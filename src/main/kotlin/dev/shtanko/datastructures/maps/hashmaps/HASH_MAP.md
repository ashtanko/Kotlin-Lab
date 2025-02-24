# Hash maps

Hash maps, also known as hash tables, are a fundamental data structure in computer science that provide efficient lookup, insertion, and deletion operations. They use a technique called hashing to map keys to values.

## Key Concepts

1. **Key-Value Pair**: A hash map stores data in pairs called key-value pairs. Each key is unique, and it is used to retrieve the corresponding value.

2. **Hash Function**: A hash function takes a key and computes an integer value called the hash code. This hash code determines the index in an array (called a bucket) where the key-value pair should be stored.

3. **Buckets**: The array where the key-value pairs are stored is divided into segments called buckets. Each bucket can store multiple pairs that hash to the same index.

4. **Collision Handling**: Collisions occur when different keys hash to the same index. Common methods to handle collisions include:
   - **Chaining**: Each bucket contains a list of entries. If multiple keys hash to the same bucket, their entries are stored in this list.
   - **Open Addressing**: When a collision occurs, the hash map searches for the next available bucket to store the entry. Methods include linear probing, quadratic probing, and double hashing.

### Operations

1. **Insertion**: To insert a key-value pair, the hash map uses the hash function to determine the bucket index. If there's a collision, it handles it using the chosen method (chaining or open addressing).

2. **Lookup**: To find the value associated with a key, the hash map uses the hash function to locate the correct bucket. It then searches through the bucket (or follows the probing sequence) to find the key.

3. **Deletion**: To delete a key-value pair, the hash map locates the correct bucket using the hash function and then removes the entry. If open addressing is used, special handling may be required to maintain the integrity of the probing sequence.

### Advantages

- **Efficiency**: Hash maps provide average time complexity of O(1) for insertion, lookup, and deletion operations.
- **Flexibility**: They can handle a large number of entries and are not limited by the size of the keys.

### Disadvantages

- **Collision Handling Overhead**: Managing collisions can add complexity and affect performance, especially if many collisions occur.
- **Memory Usage**: Hash maps can use more memory than other data structures due to the need to maintain the array and handle collisions.

## How Does HashMap Work Internally?

### Hashing

- When a key-value pair is added, the key's hashCode() method is called to generate a hash value.
- This hash value determines the index in the underlying array (bucket) where the entry will be stored.

### Buckets

- The HashMap divides the entries into buckets. Each bucket corresponds to a unique hash code.
- Multiple entries can be placed in the same bucket if their hash codes are the same (collision).

### Collision Handling

- Collisions are handled using a linked list or a balanced tree (since Java 8).

- If multiple keys hash to the same bucket, they are stored as a linked list or tree nodes at that bucket.


### Load Factor

- The load factor determines when to resize the HashMap. The default value is 0.75.

- When the number of entries exceeds the load factor threshold, the HashMap is resized to accommodate more entries.
