# The Right Shift Operation

The **Right Shift** operation shifts the bits of a number to the right by a specified number of positions. Each right shift by one position is equivalent to dividing the number by `2` (and rounding down). For signed integers, the behavior of the leftmost bits depends on whether it's a signed or unsigned right shift.

## How It Works

- Each number is represented in binary.
- The operation shifts all the bits of the number to the right by the specified number of positions.
- For signed integers:
  - **Signed Right Shift**: The leftmost bits (sign bits) are preserved.
  - **Unsigned Right Shift**: The leftmost bits are filled with `0`.

## Example of Right Shift

Let’s take an integer:

- **A = 5** (binary: `0101`)

Performing a right shift by 1 position:

- **A** = (binary: `0101`)
- **A >> 1** = (binary: `0010`)

So, shifting `5` to the right by 1 results in `2` (binary `0010`).

## Explanation

- Shift all bits of `5` to the right by 1 position:
  - The binary of `5` is `0101`.
  - After shifting right by 1 position, the new binary value is `0010`, which is `2` in decimal.

The result of `5 >> 1` is `2`.

## Note on Signed vs. Unsigned Right Shift

- **Signed Right Shift** preserves the sign bit (leftmost bit), meaning if the number is negative, it will maintain its negative sign.
- **Unsigned Right Shift** fills the leftmost bits with `0`, even for negative numbers.
