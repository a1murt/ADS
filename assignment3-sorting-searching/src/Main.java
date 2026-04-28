public class Main {
    public static void main(String[] args) {

        System.out.println("Sorting and Searching Algorithm Analysis");
        System.out.println("Algorithms: Bubble Sort, Quick Sort, Binary Search\n");

        Sorter sorter = new Sorter();
        Searcher searcher = new Searcher();
        Experiment experiment = new Experiment();

        // small demo so output looks nice
        int[] demo = sorter.generateRandomArray(10);
        System.out.println("Demo random array:");
        sorter.printArray(demo);

        int[] demoCopy = demo.clone();
        sorter.basicSort(demoCopy);
        System.out.println("After Bubble Sort:");
        sorter.printArray(demoCopy);

        int[] demoCopy2 = demo.clone();
        sorter.advancedSort(demoCopy2);
        System.out.println("After Quick Sort:");
        sorter.printArray(demoCopy2);

        // quick search demo
        int target = demoCopy[5];
        int idx = searcher.search(demoCopy, target);
        System.out.println("Binary Search for " + target + " -> index " + idx);

        // search for something that doesn't exist
        int notFound = searcher.search(demoCopy, -1);
        System.out.println("Binary Search for -1  -> index " + notFound);
        System.out.println();

        // run all the timing experiments
        experiment.runAllExperiments();
    }
}
