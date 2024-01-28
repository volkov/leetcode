package org.example;

import java.util.*;

public class GraphCycles {

    private static Set<Integer> findVerticesInCycles(List<List<Integer>> graph) {
        Set<Integer> verticesInCycles = new HashSet<>();
        boolean[] visited = new boolean[graph.size()];
        boolean[] recStack = new boolean[graph.size()];
        int[] parent = new int[graph.size()];
        Arrays.fill(parent, -1);

        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                dfs(i, graph, visited, recStack, verticesInCycles, parent);
            }
        }
        return verticesInCycles;
    }

    private static void dfs(int v, List<List<Integer>> graph, boolean[] visited, boolean[] recStack, Set<Integer> verticesInCycles, int[] parent) {
        visited[v] = true;
        recStack[v] = true;

        for (int neighbour : graph.get(v)) {
            if (!visited[neighbour]) {
                parent[neighbour] = v;
                dfs(neighbour, graph, visited, recStack, verticesInCycles, parent);
            } else if (recStack[neighbour]) {
                for (int i = v; parent[i] != -1 && i != neighbour; i = parent[i]) {
                    verticesInCycles.add(i);
                }
                verticesInCycles.add(neighbour);
                verticesInCycles.add(v);
            }
        }

        recStack[v] = false;
    }



    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(List.of(1));
        graph.add(List.of(3));
        graph.add(List.of(3));
        graph.add(List.of(1));
        Set<Integer> result = findVerticesInCycles(graph);
        System.out.println("Vertices in cycles: " + result);
    }
}
