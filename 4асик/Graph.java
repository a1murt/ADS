import java.util.*;

public class Graph {
    private Map<Integer, Vertex> vertices = new HashMap<>();
    private Map<Integer, List<Edge>> adj = new HashMap<>();
    private boolean directed;

    public Graph() {
        this.directed = false;
    }

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public void addVertex(Vertex v) {
        if (!vertices.containsKey(v.getId())) {
            vertices.put(v.getId(), v);
            adj.put(v.getId(), new ArrayList<>());
        }
    }

    public void addEdge(int from, int to) {
        Vertex a = vertices.get(from);
        Vertex b = vertices.get(to);
        adj.get(from).add(new Edge(a, b));
        if (!directed) {
            adj.get(to).add(new Edge(b, a));
        }
    }

    public void printGraph() {
        System.out.println("Graph (adjacency list):");
        List<Integer> ids = new ArrayList<>(adj.keySet());
        Collections.sort(ids);
        for (int id : ids) {
            System.out.print(vertices.get(id) + " -> ");
            List<String> neighbors = new ArrayList<>();
            for (Edge e : adj.get(id)) {
                neighbors.add(e.getDestination().toString());
            }
            System.out.println(String.join(", ", neighbors));
        }
    }

    public void bfs(int start) {
        List<Integer> order = bfsTraverse(start);
        System.out.print("BFS from " + start + ": ");
        printOrder(order);
    }

    public List<Integer> bfsTraverse(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            order.add(cur);
            for (Edge e : adj.get(cur)) {
                int next = e.getDestination().getId();
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }
        return order;
    }

    public void dfs(int start) {
        List<Integer> order = dfsTraverse(start);
        System.out.print("DFS from " + start + ": ");
        printOrder(order);
    }

    public List<Integer> dfsTraverse(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfsHelper(start, visited, order);
        return order;
    }

    private void dfsHelper(int cur, Set<Integer> visited, List<Integer> order) {
        visited.add(cur);
        order.add(cur);
        for (Edge e : adj.get(cur)) {
            int next = e.getDestination().getId();
            if (!visited.contains(next)) {
                dfsHelper(next, visited, order);
            }
        }
    }

    private void printOrder(List<Integer> order) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < order.size(); i++) {
            sb.append(order.get(i));
            if (i < order.size() - 1) sb.append(" -> ");
        }
        System.out.println(sb.toString());
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        int count = 0;
        for (List<Edge> edges : adj.values()) {
            count += edges.size();
        }
        if (directed) return count;
        return count / 2;
    }
}
