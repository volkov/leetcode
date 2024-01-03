package org.example;

public class Lasers {
    public int numberOfBeams(String[] bank) {
        int result = 0;
        int prev = 0;
        for (String s : bank) {
            int current = (int) s.chars().filter(c -> c == '1').count();
            result += prev * current;
            if (current != 0) {
                prev = current;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Lasers().numberOfBeams(new String[]{"011001", "000000", "010100", "001000"}));
    }

}
