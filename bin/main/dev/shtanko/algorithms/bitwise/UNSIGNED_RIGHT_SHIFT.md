# The Unsigned Right Shift Operation

The **Unsigned Right Shift** operation shifts the bits of a number to the right by a specified number of positions. Unlike the signed right shift, the unsigned right shift does not preserve the sign bit. Instead, it fills the empty bits on the left with `0`, regardless of the number's sign.

## How It Works

- Each number is represented in binary.
- The operation shifts all the bits of the number to the right by the specified number of positions.
- New bits on the left are filled with `0`.

## Example of Unsigned Right Shift

Let’s take an integer:

- **A = 5** (binary: `0101`)

Performing an unsigned right shift by 1 position:

- **A** = (binary: `0101`)
- **A >>> 1** = (binary: `0010`)

So, shifting `5` to the right by 1 results in `2` (binary `0010`).

## Explanation

- Shift all bits of `5` to the right by 1 position:
  - The binary of `5` is `0101`.
  - After shifting right by 1 position, the new binary value is `0010`, which is `2` in decimal.

The result of `5 >>> 1` is `2`.
