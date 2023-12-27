package org.example;

import java.util.HashMap;
import java.util.Map;

public class Dice {

    record Key(int n, int target) {
    }

    Map<Key, Integer> cache = new HashMap<>();
    public int numRollsToTarget(int n, int k, int target) {
        if (target < n || target > n * k) {
            return 0;
        }
        if (n == 1 ) {
            return 1;
        }
        Key key = new Key(n, target);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        var ans = 0L;
        for (int i = 1; i <= k; i++) {
            ans += numRollsToTarget(n-1, k, target - i);
        }
        var ansM = (int) (ans % (1_000_000_000+7));
        cache.put(key, ansM);
        return ansM;
    }

    public static void main(String[] args) {

        System.out.println(new Dice().numRollsToTarget(30, 30 ,500));
    }
}
