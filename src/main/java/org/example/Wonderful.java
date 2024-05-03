package org.example;


//
// https://leetcode.com/problems/number-of-wonderful-substrings/description/?envType=daily-question&envId=2024-04-30
public class Wonderful {
    public long wonderfulSubstrings(String word) {
        var counts = new long[2 << 10];
        int bits = 0;
        counts[bits] = 1;
        long result = 0;
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            bits ^= (1 << c);
            result += counts[bits];
            for (int j = 'a'; j <= 'j'; j++) {
                result += counts[bits ^ (1 << (j - 'a'))];
            }
            counts[bits]++;
        }
        return result;
    }



    public static void main(String[] args) {
        System.out.println(new Wonderful().wonderfulSubstrings("aba"));
    }
}
