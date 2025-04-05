# Synchronized in JVM  

## What is Synchronized?  
`Synchronized` is a keyword in Java that is used to mark a method or a block of code as **synchronized**. When a method is marked as synchronized, the thread that is executing it is granted a **lock** on the object the method belongs to.  

This means that **no other thread** can execute any synchronized method on the same object until the current thread releases the lock. It ensures that only one thread can access a **shared resource** at a time.  

---

## 🔹 How Synchronization Works  

### 1️⃣ Method-Level Synchronization  
- When a **method** is declared as `synchronized`, the **thread** holds the lock for the **object** on which the method is called.  
- Other threads **cannot call** any synchronized method on the same object **until the lock is released**.  

### 2️⃣ Block-Level Synchronization  
- A **block of code** can be synchronized using the `synchronized` keyword.  
- The **thread holds the lock** for the specified object **only within** the block.  
- This allows **fine-grained** control over synchronization.  

### 3️⃣ Intrinsic Locks  
- Every object in Java has an **intrinsic lock** (or **monitor lock**).  
- When a thread enters a **synchronized method or block**, it **acquires** the **intrinsic lock** for that object.  
- When it exits the method or block, it **releases** the lock.  

### 4️⃣ Reentrant Locks  
- Java's **intrinsic locks** are **reentrant**.  
- If a thread **already holds the lock** for an object, it can **re-enter** any synchronized method or block on that object **without deadlocking**.  

### 5️⃣ Thread Safety  
- Synchronized methods and blocks **ensure** that **only one thread** can execute them at a time on a given object.  
- This prevents **concurrent access issues** and ensures **thread safety**.  

---

## 🔹 Benefits of Synchronized  

✔ **Thread Safety**:  
Synchronized methods ensure that **only one thread** can execute them at a time on a given object, **preventing data corruption** and **ensuring consistency**.  

✔ **Atomicity**:  
Synchronized blocks **provide atomicity**, ensuring that a **series of operations** are executed as a **single, indivisible step**.  

✔ **Visibility**:  
Changes made by one thread to a synchronized block **are visible to other threads**, ensuring they see the **most up-to-date state** of shared variables.  
