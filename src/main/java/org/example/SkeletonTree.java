package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class SkeletonTree {

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int[] aliceComponents = new int[n];
        fill123(aliceComponents);
        int[] bobComponents = new int[n];
        fill123(bobComponents);
        Arrays.sort(edges, Comparator.comparing(e -> ((int[]) e)[0]).reversed());
        int count = 0;
        for (int[] edge : edges) {
            int type = edge[0];
            int from = edge[1] - 1;
            int to = edge[2] - 1;
            boolean shouldAdd = false;
            //System.out.printf("type %d from %d to %d%n", edge[0], edge[1], edge[2]);
            if (type == 1 || type == 3) {
                if (differentComponents(aliceComponents, from, to)) {
                    merge(aliceComponents, from, to);
                    shouldAdd = true;
                    //System.out.println("Should add because of alice");
                }
            }
            if (type == 2 || type == 3) {
                if (differentComponents(bobComponents, from, to)) {
                    merge(bobComponents, from, to);
                    shouldAdd = true;
                    //System.out.println("Should add because of bob");
                }
            }
            if (shouldAdd) {
                count++;
            }
        }
        if (allReachable(aliceComponents) && allReachable(bobComponents)) {
            return edges.length - count;
        }
        return -1;
    }

    private boolean allReachable(int[] components) {
        for (int i = 0; i < components.length; i++) {
            if (p(components, 0) != p(components, i)) {
                return false;
            }
        }
        return true;
    }

    private void merge(int[] components, int a, int b) {
        // random here?
        components[p(components, a)] = p(components, b);
    }

    private boolean differentComponents(int[] aliceComponents, int from, int to) {
        return p(aliceComponents, from) != p(aliceComponents, to);
    }

    private int p(int[] components, int i) {
        if (components[i] != i) {
            components[i] = p(components, components[i]);
        }
        return components[i];
    }

    private void fill123(int[] aliceComponents) {
        for (int i = 0; i < aliceComponents.length; i++) {
            aliceComponents[i] = i;
        }
    }

    public static void main(String[] args) {
        System.out.println(new SkeletonTree().maxNumEdgesToRemove(4, new int[][]{
                {3, 1, 2},
                {3, 2, 3},
                {1, 1, 3},
                {1, 2, 4},
                {1, 1, 2},
                {2, 3, 4}
        }));
    }

}
