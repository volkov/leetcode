package org.example;

import java.util.Arrays;

public class Cookies {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int j = 0;
        int result = 0;
        for (int i = 0; i < s.length && j < g.length; i++) {
            if (g[j] <= s[i]) {
                result++;
                j++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Cookies().findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));
    }

}
