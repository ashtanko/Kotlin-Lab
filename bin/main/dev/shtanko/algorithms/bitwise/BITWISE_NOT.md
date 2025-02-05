# The Bitwise NOT Operation

The **Bitwise NOT** operation inverts each bit of a number. It flips `1` to `0` and `0` to `1`. This is also known as the **one's complement** of the number.

## How It Works

- Each number is represented in binary.
- The operation inverts all the bits of the number.
- For signed integers, the result is represented using two's complement notation.

## Example of Bitwise NOT

Let’s take an integer:

- **A = 5** (binary: `0101`)

Performing a bitwise NOT:

- **A** = (binary: `0101`)
- **NOT A** = (binary: `1010`)

For **signed numbers**, the result is typically represented using two's complement notation, which means flipping all the bits and adding 1. But for simplicity, we will focus on the operation as it is for unsigned numbers.

So, the result of `NOT 5` (bitwise NOT) is `-6` (binary: `1010` for a 4-bit representation).

## Explanation

- Invert each bit of `5`:
  - The binary of `5` is `0101`.
  - After inverting the bits, it becomes `1010`.

So, the result of `NOT 5` is `-6` (in two's complement for signed integers, though this can vary depending on the system and bit-length used).
