package org.example;

import java.util.Arrays;

public class MinDifficulty {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int[][] dp = new int[jobDifficulty.length][d];
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE / 2);
        }
        dp[0][0] = jobDifficulty[0];
        for (int i = 0; i < jobDifficulty.length; i++) {
            for (int j = 0; j < d; j++) {
                int max = 0;
                // TODO fix first group
                for (int k = i; k >= j; k--) {
                    max = Math.max(max, jobDifficulty[k]);
                    if (j > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[k-1][j - 1] + max);
                    } else if (k == 0) {
                        dp[i][j] = Math.min(dp[i][j], max);
                    }
                }
                //System.out.printf("d[%d][%d] = %d%n", i, j, dp[i][j]);
            }
        }
        int res = dp[jobDifficulty.length - 1][d - 1];
        return res == Integer.MAX_VALUE / 2 ? -1 : res;
    }

    public static void main(String[] args) {
        System.out.println(new MinDifficulty().minDifficulty(new int[]{1,1,1}, 3));
    }
}
