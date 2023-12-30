package org.example;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Words {

    public boolean makeEqual(String[] words) {
        Map<Integer, Long> counts = Arrays.stream(words).flatMap(w -> w.chars().boxed())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Long value : counts.values()) {
            if (!(value % words.length == 0)) {
                return false;
            }
        }

        return true;
    }
}
