package org.example;

import java.util.HashMap;
import java.util.Map;

public class DistanceSubstrings {

    public int maxLengthBetweenEqualCharacters(String s) {
        Map<Character, Integer> firstOccurance = new HashMap<>();
        int result = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (firstOccurance.containsKey(c)) {
                result = Math.max(result, i - firstOccurance.get(c) - 1);
            }
            firstOccurance.putIfAbsent(c, i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new DistanceSubstrings().maxLengthBetweenEqualCharacters("aba"));
    }
}
