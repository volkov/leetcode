package org.example;

public class Pillow {

    public int passThePillow(int n, int time) {
        int res = time % (2 * (n - 1));
        if (res >= n) {
            return 2 * n - res - 1;
        }
        return res + 1;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 15; i++) {
            System.out.println(new Pillow().passThePillow(4, i));
        }

    }

}
