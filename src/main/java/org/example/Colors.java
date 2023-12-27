package org.example;

import java.util.List;

public class Colors {
    public int minCost(String colors, int[] neededTime) {
        char prev = 0;
        int total = 0;
        int largest = 0;
        int result = 0;
        for (int i = 0; i < colors.length(); i++) {
            char c = colors.charAt(i);
            if (prev != c) {
                result += total - largest;
                total = neededTime[i];
                largest = neededTime[i];
                prev = c;
            } else {
                total += neededTime[i];
                if (neededTime[i] > largest) {
                    largest = neededTime[i];
                }
            }
        }
        return result + total - largest;
    }

    public static void main(String[] args) {
        System.out.println(new Colors().minCost("abaac", new int[] {1,2,3,4,5}));
    }
}
