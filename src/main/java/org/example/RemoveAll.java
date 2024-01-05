package org.example;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RemoveAll {
    public int minOperations(int[] nums) {
        Map<Integer, Long> counts = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        int result = 0;
        for (Long value : counts.values()) {
            int division =  value < 4 ? 0 : (value.intValue() - 4) / 3;
            result += division;
            value = value - division * 3;
            if (value == 1) {
                return -1;
            }
            result += toValue(value.intValue());

        }
        return result;
    }

    private int toValue(int value) {
        return switch (value) {
            case 2 -> 1;
            case 3 -> 1;
            case 4 -> 2;
            case 5 -> 2;
            case 6 -> 2;
            default -> throw new RuntimeException(String.valueOf(value));
        };
    }

    public static void main(String[] args) {
        System.out.println(new RemoveAll().minOperations(new int[]{14,12,14,14,12,14,14,12,12,12,12,14,14,12,14,14,14,12,12}));
    }
}
