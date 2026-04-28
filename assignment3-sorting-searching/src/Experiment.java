public class Experiment {

    private Sorter sorter = new Sorter();
    private Searcher searcher = new Searcher();

    public long measureSortTime(int[] arr, String type) {
        int[] copy = arr.clone(); // don't mess with original
        long start = System.nanoTime();
        if (type.equals("basic")) {
            sorter.basicSort(copy);
        } else {
            sorter.advancedSort(copy);
        }
        long end = System.nanoTime();
        return end - start;
    }

    public long measureSearchTime(int[] arr, int target) {
        long start = System.nanoTime();
        searcher.search(arr, target);
        long end = System.nanoTime();
        return end - start;
    }

    public void runAllExperiments() {
        int[] sizes = {10, 100, 1000, 5000};

        System.out.println("=========================================");
        System.out.println("       PERFORMANCE EXPERIMENTS");
        System.out.println("=========================================\n");

        for (int size : sizes) {
            System.out.println(">> Array size: " + size);
            System.out.println("-----------------------------------------");

            int[] randomArr = sorter.generateRandomArray(size);

            // create sorted version
            int[] sortedArr = randomArr.clone();
            sorter.advancedSort(sortedArr);

            // sorting on RANDOM data
            long bubbleRandom = measureSortTime(randomArr, "basic");
            long quickRandom = measureSortTime(randomArr, "advanced");

            // sorting on already SORTED data
            long bubbleSorted = measureSortTime(sortedArr, "basic");
            long quickSorted = measureSortTime(sortedArr, "advanced");

            System.out.println("Bubble Sort (random): " + bubbleRandom + " ns");
            System.out.println("Quick Sort  (random): " + quickRandom + " ns");
            System.out.println("Bubble Sort (sorted): " + bubbleSorted + " ns");
            System.out.println("Quick Sort  (sorted): " + quickSorted + " ns");

            // search experiment - pick a target that exists
            int target = sortedArr[size / 2];
            long searchTime = measureSearchTime(sortedArr, target);
            System.out.println("Binary Search:        " + searchTime + " ns  (target=" + target + ")");

            System.out.println();
        }

        System.out.println("=========================================");
        System.out.println("           DONE");
        System.out.println("=========================================");
    }
}
