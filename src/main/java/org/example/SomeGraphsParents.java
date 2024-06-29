package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class SomeGraphsParents {

    int c = 0;

    record Node(int i, int order) {}

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<List<Integer>> adjacency = IntStream.range(0, n).mapToObj(i -> (List<Integer>) new ArrayList<Integer>()).toList();
        for (int[] edge : edges) {
            adjacency.get(edge[0]).add(edge[1]);
        }
        boolean[] visited = new boolean[n];
        Node[] onExit = new Node[n];
        for (int i = 0; i < n; i++) {
            dfs(i, adjacency, visited, onExit);
        }
        Arrays.sort(onExit, Comparator.comparing(Node::order).reversed());


        List<Set<Integer>> result = IntStream.range(0, n).mapToObj(i -> (Set<Integer>)new HashSet<Integer>()).toList();
        for (Node node : onExit) {
            Set<Integer> iSet = result.get(node.i);
            for (Integer nextI : adjacency.get(node.i)) {
                Set<Integer> curr = result.get(nextI);
                curr.add(node.i);
                curr.addAll(iSet);
            }
        }
        return result.stream().map(s -> s.stream().sorted().toList()).toList();

    }

    private void dfs(int i, List<List<Integer>> edges, boolean[] visited, Node[] onExit) {
        if (visited[i]) {
            return;
        }
        visited[i] = true;
        for (Integer next : edges.get(i)) {
            dfs(next, edges, visited, onExit);
        }
        onExit[i] = new Node(i, c++);
    }

    public static void main(String[] args) {
        System.out.println(new SomeGraphsParents().getAncestors(2, new int[][] {{0, 1},{1, 0}}));
    }
}
