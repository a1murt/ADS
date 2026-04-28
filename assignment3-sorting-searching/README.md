# Assignment 3 — Sorting and Searching Algorithm Analysis

Assignment 3 for Algorithms course. The goal is to implement a basic sorting algo, an advanced sorting algo, and a search algo, then measure how fast they run on different array sizes.

## Algorithms I picked

- **Basic sorting:** Bubble Sort
- **Advanced sorting:** Quick Sort
- **Searching:** Binary Search

## Project Structure

```
assignment3-sorting-searching/
├── src/
│   ├── Sorter.java
│   ├── Searcher.java
│   ├── Experiment.java
│   └── Main.java
├── docs/
│   └── screenshots/
├── README.md
└── .gitignore
```

## How to run

```
cd src
javac *.java
java Main
```

## Algorithm Descriptions

### Bubble Sort (basic)
Goes through the array and swaps adjacent elements if they are in wrong order. Repeats until nothing needs to be swapped. After each pass the biggest element "bubbles up" to the end. Easy to implement but very slow on big arrays.

- Best case: O(n) (if you add an early-exit flag, mine doesn't have one)
- Average / Worst case: O(n²)
- Space: O(1)

### Quick Sort (advanced)
Pick a pivot (I use the last element), then partition the array so that everything smaller than pivot is on the left and everything bigger is on the right. Then recursively sort the two halves. Much faster than bubble sort on random data.

- Best / Average case: O(n log n)
- Worst case: O(n²) — happens when array is already sorted and pivot is bad
- Space: O(log n) (recursion stack)

### Binary Search
The array MUST be sorted first. Look at the middle. If middle == target, done. If target is bigger, search right half. If smaller, search left half. Each step throws away half of the array.

- Best case: O(1)
- Average / Worst case: O(log n)
- Space: O(1)

## Experimental Results

Tested on array sizes 10, 100, 1000, and 5000. Random arrays generated with `Random.nextInt(10000)`. Times measured with `System.nanoTime()`.

### Sorting on RANDOM data

| Size | Bubble Sort (ns) | Quick Sort (ns) |
|------|------------------|-----------------|
| 10   | 5,839            | 1,852           |
| 100  | 176,448          | 108,340         |
| 1000 | 4,012,096        | 76,835          |
| 5000 | 23,029,204       | 355,782         |

### Sorting on already SORTED data

| Size | Bubble Sort (ns) | Quick Sort (ns) |
|------|------------------|-----------------|
| 10   | 1,301            | 2,267           |
| 100  | 102,180          | 154,745         |
| 1000 | 1,159,365        | 1,264,489       |
| 5000 | 2,876,542        | 12,904,173      |

### Binary Search (on sorted array)

| Size | Time (ns) |
|------|-----------|
| 10   | 2,355     |
| 100  | 3,755     |
| 1000 | 1,445     |
| 5000 | 2,144     |

(numbers don't grow much because log2(5000) ≈ 12, so it's basically constant compared to sorting)

## Analysis (answers to questions)

**Which sorting algorithm performed faster? Why?**
Quick Sort was way faster on random data, especially when arrays got bigger. At size 5000 it was about 65x faster than bubble sort. This is because Quick Sort is O(n log n) on average and Bubble Sort is O(n²). On smaller arrays (size 10) the difference is small because the constant overhead matters more than the algorithm complexity.

**How does performance change with input size?**
Bubble Sort time grows really fast — going from 1000 → 5000 elements (5x size), the time went from 4ms to 23ms which is roughly 5.7x. That matches O(n²) behavior (5² = 25 but we only did 5x bigger, so it's somewhere around there). Quick Sort grew from 76,835ns to 355,782ns which is about 4.6x for 5x size — close to linear which is what n log n looks like for these sizes.

**How does sorted vs unsorted data affect performance?**
This was actually surprising. On already-sorted data:
- Bubble Sort got faster (no swaps needed, but my version still does all comparisons since I didn't add the early-exit flag)
- Quick Sort got SLOWER, sometimes a lot slower (12.9ms vs 0.35ms at size 5000!)

The reason is that when I pick the last element as pivot and the array is already sorted, every partition is super unbalanced (one side has 0 elements, other has n-1). This is the worst case for Quick Sort and turns it into O(n²). If I picked a random pivot or median pivot it would be better.

**Do the results match the expected Big-O complexity?**
Mostly yes:
- Bubble Sort on random ≈ O(n²) ✅
- Quick Sort on random ≈ O(n log n) ✅
- Quick Sort on sorted ≈ O(n²) ✅ (this is the worst case I read about, and it actually happened)
- Binary Search ≈ O(log n) ✅ (basically flat across sizes)

**Which searching algorithm is more efficient? Why?**
I only implemented Binary Search but compared to a hypothetical Linear Search: Binary Search is much more efficient because it cuts the search space in half each step (O(log n)), while Linear Search has to check every element (O(n)). For 5000 elements Linear Search would need up to 5000 comparisons, Binary Search needs at most ~13.

**Why does Binary Search require a sorted array?**
Because it works by comparing the target to the middle element and deciding to go left or right based on whether target is bigger or smaller. If the array is not sorted, "go right because target is bigger than middle" doesn't make sense — the bigger elements could be anywhere. The whole "throw away half the array" trick only works if order is guaranteed.

## Screenshots

See `docs/screenshots/` for program output.

## Reflection

Honestly the most interesting part of this assignment was seeing Quick Sort blow up on sorted data. I knew the worst case from the lecture but seeing it actually happen with my own numbers (12ms instead of 0.3ms!) made it click. Also realized my Bubble Sort doesn't have the optimization where you stop early if no swaps happened in a pass — that's why it still takes a long time on sorted arrays.

The biggest gap between theory and practice was at small array sizes. For size=10 the differences were tiny and sometimes Bubble Sort was even faster than Quick Sort because of recursion overhead. So Big-O is really about asymptotic behavior, not about which algorithm wins on small inputs. Also the timer noise at small sizes is huge — running the same test twice gives different numbers, so I'd want to average over many runs for a real benchmark.

Main challenges: figuring out why Quick Sort was slow on sorted data (had to look up "quick sort worst case"), and making sure I cloned the array before sorting so I don't accidentally measure sort time on an already-sorted array.
