package org.example;

public class Triplets {

    public int countTriplets(int[] arr) {
        int result = 0;
        int a = 0;
        int b = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            a = 0;
            for (int j = i + 1; j < arr.length; j++) {
                a ^= arr[j - 1];
                b = 0;
                for (int k = j; k < arr.length; k++) {
                    b ^= arr[k];
                    if (a == b) {
                        //System.out.printf("%d %d %d (%d = %d)%n", i, j, k, a, b);
                        result++;
                    }
                }

            }
            a ^= arr[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Triplets().countTriplets(new int[]{1,1,1,1,1}));
    }
}
