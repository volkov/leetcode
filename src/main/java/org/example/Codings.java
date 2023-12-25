package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Codings {

    static Set<String> codings = new HashSet<>();
    
    static {
        for (int i = 1; i <= 26; i++) {
            codings.add(String.valueOf(i));
        }
    }

    Map<String, Integer> cache = new HashMap<>();
    public int numDecodings(String s) {
        Integer resFromCache = cache.get(s);
        if (resFromCache != null) {
            return resFromCache;
        }
        if (s.isEmpty()) {
            return 1;
        }
        var res = 0;
        var prefix = s.substring(0, 1);
        if (codings.contains(prefix)) {
            res += numDecodings(s.substring(1));
        }
        if (s.length() == 1) {
            cache.put(s, res);
            return res;
        }
        prefix = s.substring(0, 2);
        if (codings.contains(prefix)) {
            res += numDecodings(s.substring(2));
        }
        cache.put(s, res);
        return res;

    }

    public static void main(String[] args) {
        System.out.println(new Codings().numDecodings("226"));
    }
}
