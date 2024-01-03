package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Matrix {
    public List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Long> counts = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        int i = 0;
        while (true) {
            List<Integer> row = new ArrayList<>();
            for (Map.Entry<Integer, Long> entry : counts.entrySet()) {
                if (entry.getValue() > i) {
                    row.add(entry.getKey());
                }
            }
            if (row.isEmpty()) {
                return result;
            }
            i++;
            result.add(row);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Matrix().findMatrix(new int[]{1, 3, 4, 1, 2, 3, 1}));
    }
}
