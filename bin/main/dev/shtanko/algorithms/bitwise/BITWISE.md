# Bitwise Operations and Algorithms

Bitwise operations are used to manipulate individual bits of data. These operations are fundamental in low-level 
programming and are widely used for optimization, cryptography, and other applications that require efficient manipulation 
of binary data.

## Table of Contents
1. [Bitwise Operators](#bitwise-operators)
2. [Common Bitwise Algorithms](#common-bitwise-algorithms)
3. [Applications of Bitwise Operations](#applications-of-bitwise-operations)
4. [Conclusion](#conclusion)

---

## Bitwise Operators

Bitwise operators allow you to perform operations on individual bits of an integer. Here are the most commonly used bitwise operators:

### 1. AND (`&`)
The AND operator compares two bits and returns `1` only if both bits are `1`. Otherwise, it returns `0`.

**Example:**
```text
1010   (decimal 10)
& 1100   (decimal 12)
#### --------
   1000  (decimal 8)
```

### 2. OR (|)
The OR operator compares two bits and returns 1 if at least one bit is 1. If both bits are 0, it returns 0.

**Example:**
```text
1010   (decimal 10)
#### -------- 12)
#### --------
   1110  (decimal 14)
```

### 3. XOR (^)
The XOR operator compares two bits and returns 1 if the bits are different (i.e., one is 0, the other is 1). It returns 0 if the bits are the same.

Example:

```text
1010   (decimal 10)
^ 1100   (decimal 12)
#### --------
   0110  (decimal 6)
```

### 4. NOT (~)
The NOT operator inverts all the bits of a number. This is also called the bitwise complement.

Example:

```text
~1010   (decimal 10)
#### --------
  0101  (decimal -11)
```

### 5. Left Shift (<<)
The left shift operator shifts all bits of a number to the left by a specified number of positions. The bits that are shifted out on the left are discarded, and 0 bits are filled on the right.

Example:

```text
1010   (decimal 10)
<< 2
#### --------
  1000  (decimal 40)
```

### 6. Right Shift (>>)
The right shift operator shifts all bits of a number to the right by a specified number of positions. The bits that are shifted out on the right are discarded, and the leftmost bits are filled with the sign bit for signed integers.

Example:

```text
1010   (decimal 10)
>> 2
#### --------
  0010  (decimal 2)
```
