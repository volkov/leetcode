package org.example;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class LIS {
    public int lengthOfLIS(int[] nums) {

        TreeMap<Integer, Integer> lis = new TreeMap<>();
        for (int num : nums) {
            Map.Entry<Integer, Integer> prev = lis.floorEntry(num - 1);
            int value = 1;
            if (prev == null) {
                //System.out.println(num + " is smallest so far");
                lis.put(num, value);
            } else {
                value = prev.getValue() + 1;
                //System.out.printf("%d goes on top of %d with %d%n", num, prev.getKey(), value);
                lis.put(num, value);
            }
            Iterator<Map.Entry<Integer, Integer>> upperIterator = lis.subMap(num + 1, Integer.MAX_VALUE).entrySet().iterator();
            while (upperIterator.hasNext()) {
                Map.Entry<Integer, Integer> upperEntry = upperIterator.next();
                if (upperEntry.getValue() <= value) {
                    upperIterator.remove();
                } else {
                    break;
                }
            }
        }
        //System.out.println(lis);
        return lis.values().stream().mapToInt(i -> i).max().getAsInt();
    }

    public static void main(String[] args) {
        System.out.println(new LIS().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
