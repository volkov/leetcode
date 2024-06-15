package org.example;

import java.util.Arrays;

public class PerfectSquares {
    public int numSquares(int n) {
        var result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            result[i] = Integer.MAX_VALUE;
            double sqrt = Math.sqrt(i);
            System.out.println(sqrt);
            for (int k = 1; k <= sqrt; k++) {
                var candidate = result[i - k * k] + 1;
                if (result[i] > candidate) {
                    result[i] = candidate;
                }
            }
        }
        return result[n];
    }

    public static void main(String[] args) {
        System.out.println(new PerfectSquares().numSquares(12));
    }
}
