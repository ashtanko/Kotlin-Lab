# Tree in Computer Science

In computer science, a **tree** is a widely used abstract data type that simulates a hierarchical tree structure with
a set of connected nodes. It is a non-linear data structure, unlike arrays, linked lists, 
stacks, and queues which are linear data structures.

## Components of a Tree

- **Node**: A node is a basic unit of a tree which contains data and links to other nodes.
- **Root**: The top node of a tree. There is only one root in a tree.
- **Parent**: A node that has a branch to one or more child nodes.
- **Child**: A node that has a parent node. 
- **Leaf**: A node that does not have any children. Also known as an external node.
- **Internal Node**: A node with at least one child.
- **Edge**: A link between two nodes.
- **Path**: A sequence of nodes and edges connecting a node with a descendant.
- **Subtree**: A tree consisting of a node and its descendants.
- **Depth**: The length of the path from the root to the node.
- **Height**: The length of the path from the node to the deepest leaf.
- **Degree**: The number of children of a node.

## Types of Trees

- **Binary Tree**: A tree where each node has at most two children, referred to as the left child and the right child.
- **Binary Search Tree (BST)**: A binary tree in which each node has a value greater than all the values in the left 
subtree and less than those in the right subtree.
- **Balanced Tree**: A tree where the height of the two subtrees of any node differ by at most one.
- **Complete Binary Tree**: A binary tree in which all levels, except possibly the last, are completely filled and all 
nodes are as far left as possible.
- **Full Binary Tree**: A binary tree in which every node other than the leaves has two children.
- **AVL Tree**: A self-balancing binary search tree where the difference between heights of left and right subtrees 
cannot be more than one for all nodes.
- **Red-Black Tree**: A self-balancing binary search tree where nodes are colored red or black to ensure the tree 
remains balanced during insertions and deletions.
- **N-ary Tree**: A tree where each node can have at most N children.

## Operations on Trees

- **Traversal**: Visiting all the nodes in some order. Types include:
  - **In-order Traversal**: Visit the left subtree, the root, and then the right subtree.
  - **Pre-order Traversal**: Visit the root, the left subtree, and then the right subtree.
  - **Post-order Traversal**: Visit the left subtree, the right subtree, and then the root.
  - **Level-order Traversal**: Visit nodes level by level.
- **Insertion**: Adding a new node to the tree.
- **Deletion**: Removing a node from the tree.
- **Searching**: Finding a node in the tree with a given value.

## Applications of Trees

- **Hierarchical Data Representation**: Such as file systems and organizational structures.
- **Binary Search Trees**: For efficient searching, insertion, and deletion.
- **Expression Trees**: Used in compilers to represent expressions.
- **Trie**: Used for efficient retrieval of a key in a dataset of strings, commonly used in autocomplete features.
- **Heaps**: Used to implement priority queues.

Trees are fundamental data structures that provide efficient means of managing hierarchical data and are crucial in 
various algorithms and applications in computer science.

## Binary Tree Examples:
- Binary Tree (Example):
     A
    / \
   B   C
  / \
 D   E
 
- Binary Search Tree (BST) (Example):
      4
     / \
    2   6
   / \ / \
  1  3 5  7
  
- Balanced Tree (Example):
       10
     /    \
    5      15
   / \    /  \
  3   7  12   17

- Complete Binary Tree (Example):
          1
        /   \
       2     3
      / \   /
     4   5 6

- Full Binary Tree (Example):
          1
        /   \
       2     3
      / \   / \
     4   5 6   7

- AVL Tree (Example):
       10
     /    \
    5      15
   / \    /  \
  3   7  12   20
         /
        11

- Red-Black Tree (Example):
        7 (Black)
       / \
     3 (Red) 18 (Red)
    / \      / \
   1   5   10   22
      / \         \
     4   6        30
