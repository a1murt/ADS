public class Main {
    public static void main(String[] args) {
        System.out.println("=== Small Graph (10 vertices) Demonstration ===");
        Graph g = new Graph(false);
        for (int i = 0; i < 10; i++) {
            g.addVertex(new Vertex(i));
        }
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(2, 6);
        g.addEdge(3, 7);
        g.addEdge(4, 8);
        g.addEdge(5, 9);
        g.addEdge(6, 9);

        g.printGraph();
        System.out.println();
        g.bfs(0);
        g.dfs(0);

        System.out.println();
        System.out.println("=== Running Timed Experiments ===");
        Experiment exp = new Experiment();
        exp.runMultipleTests();
        exp.printResults();
    }
}
