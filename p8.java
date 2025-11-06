import java.util.*; 
public class p8 { 
    static final int V = 5; // Number of locations (A, B, C, D, E) 
    // DFS using adjacency matrix 
    static void dfsUtil(int u, boolean[] visited, int[][] adjMatrix) { 
        System.out.print((char) ('A' + u) + " "); 
        visited[u] = true; 
        for (int v = 0; v < V; v++) { 
            if (adjMatrix[u][v] == 1 && !visited[v]) { 
                dfsUtil(v, visited, adjMatrix); 
            } 
        } 
    } 
 
    static void DFS(int[][] adjMatrix) { 
        boolean[] visited = new boolean[V]; 
        System.out.print("\nDFS Traversal (using Adjacency Matrix): "); 
        dfsUtil(0, visited, adjMatrix); // Start from A (index 0) 
        System.out.println(); 
    } 
 
    // BFS using adjacency list 
    static void BFS(List<List<Integer>> adjList) { 
        boolean[] visited = new boolean[V]; 
        Queue<Integer> queue = new LinkedList<>(); 
        System.out.print("\nBFS Traversal (using Adjacency List): "); 
        visited[0] = true; // Start from A 
        queue.add(0); 
        while (!queue.isEmpty()) { 
            int u = queue.poll(); 
            System.out.print((char) ('A' + u) + " ");
            for (int v : adjList.get(u)) { 
                if (!visited[v]) { 
                    visited[v] = true; 
                    queue.add(v); 
                } 
            } 
        } 
        System.out.println(); 
    } 
    public static void main(String[] args) { 
 
        // --- Adjacency Matrix for DFS --- 
        int[][] adjMatrix = { 
                // A  B  C  D  E 
                {0, 1, 1, 0, 0}, // A 
                {1, 0, 0, 1, 0}, // B 
                {1, 0, 0, 0, 1}, // C 
                {0, 1, 0, 0, 1}, // D 
                {0, 0, 1, 1, 0}  // E 
        }; 
        // --- Adjacency List for BFS --- 
        List<List<Integer>> adjList = new ArrayList<>(); 
        for (int i = 0; i < V; i++) { 
            adjList.add(new ArrayList<>()); 
        } 
 
        adjList.get(0).addAll(Arrays.asList(1, 2)); // A → B, C 
        adjList.get(1).addAll(Arrays.asList(0, 3)); // B → A, D 
        adjList.get(2).addAll(Arrays.asList(0, 4)); // C → A, E 
        adjList.get(3).addAll(Arrays.asList(1, 4)); // D → B, E 
        adjList.get(4).addAll(Arrays.asList(2, 3)); // E → C, D 
 
        // Perform Traversals 
        DFS(adjMatrix); 
        BFS(adjList); 
    } 
}
