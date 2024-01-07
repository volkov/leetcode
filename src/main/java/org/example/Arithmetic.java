package org.example;

import java.util.Arrays;

public class Arithmetic {
    public int numberOfArithmeticSlices(int[] nums) {
        int[] combinations = new int[nums.length];
        int result = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                long a = nums[i];
                long b = nums[j];
                long slope = b - a;
                Arrays.fill(combinations, 0);
                if (slope != 0) {
                    boolean hasIncrement = false;
                    for (int k = j + 1; k < nums.length; k++) {
                        long c = nums[k];
                        if ((c - b) % slope == 0) {
                            long index = ((c - b) / slope) - 1;
                            int previousCombinations;
                            if (index == 0) {
                                previousCombinations = 1;
                            } else if (index >= combinations.length || index < 0) {
                                continue;
                            } else {
                                previousCombinations = combinations[(int)(index - 1)];
                            }
                            combinations[(int)index] += previousCombinations;
                            result += previousCombinations;
                            hasIncrement = hasIncrement || previousCombinations > 0;
                        }
                    }
                    if (hasIncrement) {
                        System.out.printf("%d %d combinations (slope %d):%n", i, j, slope);
                        System.out.println(Arrays.toString(combinations));
                        System.out.println("total: " + result);
                    }
                } else {
                    int power = 0;
                    for (int k = j + 1; k < nums.length; k++) {
                        if (nums[k] == b) {
                            power++;
                        }
                    }
                    if (power != 0) {
                        result += (2 << (power - 1)) - 1;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Arithmetic().numberOfArithmeticSlices(new int[]{
                2147483638,2147483639,2147483640,2147483641,2147483642,2147483643,2147483644,2147483645,2147483646,2147483647,
                -2147483648,-2147483647,-2147483646,-2147483645,-2147483644,-2147483643,-2147483642,-2147483641,-2147483640,-2147483639}));
    }
}
