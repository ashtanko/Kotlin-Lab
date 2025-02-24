# Happens-Before Relationship

In concurrency, the **happens-before** relationship is a fundamental concept used to ensure that operations are 
performed in a predictable and safe order. It helps define the ordering constraints between different operations in a 
concurrent program, ensuring that certain operations are completed before others begin. Here’s a breakdown of the 
concept:

## 1. Definition

The happens-before relationship is a partial ordering of operations in a concurrent system that guarantees that if one 
operation A happens-before another operation B, then the effects of A are visible to B. In other words, if operation A 
happens-before operation B, then B will see the results of A.

## 2. Why It Matters

The happens-before relationship is crucial for understanding and reasoning about concurrency issues such as race 
conditions, visibility of changes across threads, and ordering of operations. It helps ensure consistency and 
correctness in concurrent programs.

## 3. Rules and Examples

Here are some common rules and examples of happens-before relationships:
