package org.example;

import java.util.Arrays;

public class RunLength {

    public int getLengthOfOptimalCompression(String s, int k) {
        int[][] dp = new int[s.length() + 1][k + 1];
        for (int i = 0; i <= s.length(); i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= k; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }

                int gropupSize = 1;
                int toDelete = 0;
                for (int l = i - 1; l >= 0; l--) {
                    if (s.charAt(i - 1) != s.charAt(l)) {
                        toDelete++;
                    } else {
                        gropupSize++;
                    }
                    if (toDelete > j) {
                        break;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[l][j - toDelete] + groupToEncoding(gropupSize));
                }

                System.out.printf("dp[%d][%d] = %d%n", i, j, dp[i][j]);
            }
        }
        return dp[s.length()][k];
    }

    private int groupToEncoding(int i) {
        if (i >= 100) {
            return 4;
        }
        if (i >= 10) {
            return 3;
        }
        if (i > 1) {
            return 2;
        }
        return 1;
    }

    public static void main(String[] args) {
        System.out.println(new RunLength().getLengthOfOptimalCompression("aaabcccd", 2));
    }
}
