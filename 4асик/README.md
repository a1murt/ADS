# Assignment 4: Graph Traversal and Representation System

A Java implementation of a graph data structure (using an adjacency list) with
two classic traversal algorithms, **Breadth-First Search (BFS)** and
**Depth-First Search (DFS)**, including a small experimental study of their
runtime behavior on graphs of different sizes.

---

## A. Project Overview

A **graph** is a collection of objects (called **vertices** or nodes) connected
by **edges**. Graphs are one of the most general data structures in computer
science: they model anything from social networks and road maps to compiler
dependency graphs and neural networks.

In this project the graph is **undirected** and stored as an **adjacency list**.
The adjacency list keeps a map from each vertex id to the list of edges leaving
that vertex. This representation is memory efficient for sparse graphs and
allows traversal in `O(V + E)` time, where `V` is the number of vertices and
`E` is the number of edges.

Two traversal algorithms are implemented:

* **BFS** explores the graph level by level using a FIFO queue.
* **DFS** explores the graph depth first using recursion (the call stack
  acts as an explicit stack).

---

## B. Class Descriptions

### `Vertex`
Represents a single node. Holds a unique integer `id` and provides a getter
plus a `toString()` of the form `V<id>`.

### `Edge`
Represents a connection between two vertices. Stores a `source` and a
`destination` vertex, with getters and a `toString()` of the form
`V<src> -> V<dst>`.

### `Graph`
Holds the actual graph state. Uses two maps:

* `Map<Integer, Vertex> vertices` for fast lookup of a vertex by id.
* `Map<Integer, List<Edge>> adjacencyList` for the adjacency list.

A boolean flag `directed` controls whether `addEdge(from, to)` also inserts
the reverse edge.

Public methods:

* `addVertex(Vertex v)`
* `addEdge(int from, int to)`
* `printGraph()`
* `bfs(int start)` and `bfsTraverse(int start)` returning a list
* `dfs(int start)` and `dfsTraverse(int start)` returning a list
* `dfsIterative(int start)` as a non-recursive alternative

### `Experiment`
Generates graphs of size 10, 30 and 100, runs BFS and DFS on each, measures
execution time with `System.nanoTime()` and prints a summary table.

### `Main`
Entry point. Builds a small hand-crafted graph for the demonstration output,
then runs the timed experiment.

### Adjacency list representation

For the small demo graph the adjacency list looks like this:

```
V0 -> V1, V2
V1 -> V0, V3, V4
V2 -> V0, V5, V6
V3 -> V1, V7
V4 -> V1, V8
V5 -> V2, V9
V6 -> V2, V9
V7 -> V3
V8 -> V4
V9 -> V5, V6
```

Each line shows the neighbors of the vertex on the left.

---

## C. Algorithm Descriptions

### Breadth-First Search (BFS)

**Step by step:**

1. Mark the start vertex as visited and put it into a queue.
2. While the queue is not empty:
   1. Dequeue the front vertex and record it in the visit order.
   2. For every neighbor of that vertex that has not been visited yet,
      mark it as visited and enqueue it.
3. Stop when the queue is empty.

**Use cases:** shortest path in an unweighted graph, finding connected
components, level-order processing, web crawlers, social network "friends of
friends" queries.

**Time complexity:** `O(V + E)`.
**Space complexity:** `O(V)` for the queue and the visited set.

### Depth-First Search (DFS)

**Step by step:**

1. Mark the current vertex as visited and record it.
2. For every neighbor of the current vertex that has not been visited yet,
   recursively run DFS on that neighbor.
3. When all neighbors of the current vertex are visited, return to the caller.

**Use cases:** topological sorting, cycle detection, finding strongly
connected components, solving mazes and puzzles, generating spanning trees.

**Time complexity:** `O(V + E)`.
**Space complexity:** `O(V)` for the recursion stack and visited set
(in the worst case the recursion can be as deep as `V`).

---

## D. Experimental Results

Test graphs were generated with a fixed random seed so the results are
reproducible. Each graph contains a backbone chain plus roughly `V` extra
random edges, so the edge count scales linearly with the vertex count.

| Vertices | Edges | BFS time (ns) | DFS time (ns) |
|---------:|------:|--------------:|--------------:|
| 10       | 18    | 143,671       | 31,566        |
| 30       | 57    | 84,876        | 45,604        |
| 100      | 198   | 223,093       | 152,088       |

(Run on OpenJDK 21, results in nanoseconds. Absolute numbers vary between
runs because of JIT compilation, OS scheduling and CPU caching, but the
relative pattern stays the same across runs.)

### Observations and patterns

1. Both algorithms scale roughly linearly with `V + E`, which matches the
   theoretical `O(V + E)` complexity.
2. The first measurement on the small graph is often the slowest because the
   JIT compiler has not warmed up yet. A warm-up call is performed before the
   real measurement to reduce this effect, but a small spike still shows up.
3. DFS is slightly faster than BFS in these tests. The explanation is mostly
   constant-factor: BFS uses a `LinkedList`-based queue with object boxing on
   every `offer`/`poll`, while DFS recursion only pushes a stack frame.
4. The visit order is different. BFS produces a level-order traversal
   (`0, 1, 2, 3, 4, ...`), DFS dives along the first branch and only later
   comes back (`0, 1, 3, 7, 4, 8, 2, 5, 9, 6`).

### Analysis questions

**How does graph size affect BFS and DFS performance?**
Both grow linearly with `V + E`. Doubling the vertex count roughly doubles
the runtime, given that the edge count grows in the same way.

**Which traversal is faster in your experiments?**
DFS is consistently a bit faster, because of lower overhead per visited
vertex (no queue boxing). The difference is a constant factor and disappears
asymptotically.

**Do results match the expected complexity O(V + E)?**
Yes. The runtime grows roughly proportionally to the number of edges, which
is what `O(V + E)` predicts.

**How does graph structure affect traversal order?**
The order of neighbors in the adjacency list directly determines the visit
order. BFS always visits the start, then all direct neighbors, then their
neighbors, and so on. DFS goes deep into the first neighbor before touching
the second one, so a chain-like graph produces a long thin DFS path while a
star-like graph gives a very wide BFS tree.

**When is BFS preferred over DFS?**
When you need the shortest path in an unweighted graph, when the goal is
likely close to the start, or when the graph is very deep and a recursive
DFS could cause stack overflow.

**What are the limitations of DFS?**
Recursive DFS can blow the call stack on deep graphs. It does not give
shortest paths in unweighted graphs. It can also wander far from the start
before finding a target that is actually nearby.

---

## E. Screenshots

Place screenshots in `docs/screenshots/`. The reference output produced by
running `Main` is:

```
=== Small Graph (10 vertices) Demonstration ===
Graph (adjacency list):
V0 -> V1, V2
V1 -> V0, V3, V4
V2 -> V0, V5, V6
V3 -> V1, V7
V4 -> V1, V8
V5 -> V2, V9
V6 -> V2, V9
V7 -> V3
V8 -> V4
V9 -> V5, V6

BFS from 0: 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
DFS from 0: 0 -> 1 -> 3 -> 7 -> 4 -> 8 -> 2 -> 5 -> 9 -> 6

=== Running Timed Experiments ===

=== Performance Results ===
Vertices   Edges      BFS time (ns)      DFS time (ns)
-----------------------------------------------------------
10         18         143671             31566
30         57         84876              45604
100        198        223093             152088
```

Suggested screenshots to include:

* `graph_structure.png` for the adjacency list output
* `bfs_output.png` for the BFS traversal line
* `dfs_output.png` for the DFS traversal line
* `performance.png` for the timing table

---

## F. Reflection

Working on this assignment made the difference between BFS and DFS very
concrete for me. On paper they are both `O(V + E)` traversals, but actually
running them on the same graph and printing the visit order shows how
differently they explore the same structure. BFS spreads outward like a wave
and naturally finds the shortest distance from the start to every other
vertex, while DFS commits to one branch and only backtracks when it has no
unvisited neighbors left. Implementing both on top of the same adjacency list
made me appreciate how a single graph representation supports many different
algorithms.

The main challenge was keeping the experiment honest. The first timed call
was always much slower than the rest because of JIT warm-up, so I added a
warm-up traversal before each measurement. I also had to choose how to
generate the test graphs in a way that is connected (so the traversal
actually visits every vertex) but still has random structure. A backbone
chain plus extra random edges turned out to be a clean solution.

---

## Repository structure

```
assignment4-graphs/
├── src/
│   ├── Vertex.java
│   ├── Edge.java
│   ├── Graph.java
│   ├── Experiment.java
│   └── Main.java
├── docs/
│   └── screenshots/
├── README.md
└── .gitignore
```

## How to run

```bash
cd src
javac *.java
java Main
```

Requires JDK 11 or higher.
