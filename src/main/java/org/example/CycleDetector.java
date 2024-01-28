package org.example;

import java.util.*;

public class CycleDetector {

    private Map<Integer, List<Integer>> adjList = new HashMap<>();

    private int index = 0;
    private Stack<Integer> stack = new Stack<>();
    private Map<Integer, Integer> indexMap = new HashMap<>();
    private Map<Integer, Integer> lowLinkMap = new HashMap<>();

    private Set<Integer> verticesOnCycle = new HashSet<>();

    // Method to add an edge to the graph
    public void addEdge(int v, int w) {
        adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(w);
    }

    // Main function that traverses given graph
    public Set<Integer> findVerticesOnCycle() {
        for (Integer v : adjList.keySet()) {
            if (!indexMap.containsKey(v)) {
                tarjanDFS(v);
            }
        }
        return verticesOnCycle;
    }

    // A recursive function that finds strongly connected components using DFS
    private void tarjanDFS(int v) {

        // Initialize discovery time and low link, also push vertex to stack
        indexMap.put(v, index);
        lowLinkMap.put(v, index);
        index++;
        stack.push(v);

        if (adjList.get(v) != null) {
            for (int w : adjList.get(v)) {
                if (w == v) {
                    verticesOnCycle.add(v); // it's a loop
                }
                if (!indexMap.containsKey(w)) { // If w is not visited yet, then recursively visit it and update low link value
                    tarjanDFS(w);
                    lowLinkMap.put(v, Math.min(lowLinkMap.get(v), lowLinkMap.get(w)));
                } else if (stack.contains(w)) { // If w is already visited and on stack, then update low link value of v
                    lowLinkMap.put(v, Math.min(lowLinkMap.get(v), indexMap.get(w)));
                }
            }
        }

        // If v is a root node, pop the stack and generate an SCC
        if (lowLinkMap.get(v).equals(indexMap.get(v))) {
            List<Integer> scc = new ArrayList<>();
            int w;
            do {
                w = stack.pop();
                scc.add(w);
            } while (w != v);
            if (scc.size() > 1) {
                verticesOnCycle.addAll(scc);
            }
        }
    }

    // Usage example
    public static void main(String[] args) {
        CycleDetector g = new CycleDetector();

        // SCC 1 2 3 4 with dimond 1->2->4 and 1->3->4
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(4, 1);

        // Not SCC 5->6
        g.addEdge(5, 6);

        // Loop
        g.addEdge(7, 7);

        Set<Integer> result = g.findVerticesOnCycle();
        System.out.println("Vertices in cycles: " + result); // 1, 2, 3, 4 and  7
    }


}
