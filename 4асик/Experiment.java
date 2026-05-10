import java.util.*;

public class Experiment {
    private List<long[]> results = new ArrayList<>();

    public void runTraversals(Graph g) {
        g.bfsTraverse(0);
        g.dfsTraverse(0);

        long s1 = System.nanoTime();
        g.bfsTraverse(0);
        long e1 = System.nanoTime();

        long s2 = System.nanoTime();
        g.dfsTraverse(0);
        long e2 = System.nanoTime();

        results.add(new long[] {
            g.getVertexCount(),
            g.getEdgeCount(),
            e1 - s1,
            e2 - s2
        });
    }

    public void runMultipleTests() {
        int[] sizes = {10, 30, 100};
        for (int size : sizes) {
            Graph g = makeGraph(size);
            runTraversals(g);
        }
    }

    private Graph makeGraph(int n) {
        Graph g = new Graph(false);
        for (int i = 0; i < n; i++) {
            g.addVertex(new Vertex(i));
        }
        for (int i = 0; i < n - 1; i++) {
            g.addEdge(i, i + 1);
        }
        Random rand = new Random(42);
        for (int i = 0; i < n; i++) {
            int a = rand.nextInt(n);
            int b = rand.nextInt(n);
            if (a != b) {
                g.addEdge(a, b);
            }
        }
        return g;
    }

    public void printResults() {
        System.out.println();
        System.out.println("=== Performance Results ===");
        System.out.printf("%-10s %-10s %-18s %-18s%n",
                "Vertices", "Edges", "BFS time (ns)", "DFS time (ns)");
        System.out.println("-----------------------------------------------------------");
        for (long[] r : results) {
            System.out.printf("%-10d %-10d %-18d %-18d%n", r[0], r[1], r[2], r[3]);
        }
    }
}
