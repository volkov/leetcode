package org.example;

import java.util.ArrayList;
import java.util.List;

public class RunLength {
    
    record Item(char symbol, int count) {

    }
    
    public int getLengthOfOptimalCompression(String s, int k) {
        List<Item> items = getItems(s);
        System.out.println(items);

        while (k > 0) {
            int indexToDelete = 0;
            int countToDelete = 0;
            double maxDiff = 0;
            for (int j = 1; j <= k; j++) {
                for (int i = 0; i < items.size(); i++) {
                    var diff = 0;
                    var current  = items.get(i);

                    if (current.count() - j == 0) {
                        diff = weight(current.count);
                        diff += collapseCount(items, i);
                    }
                    if (current.count() - j > 0) {
                        diff = weight(current.count) - weight(current.count - j);;
                    }
                    if (diff * 1.0/j > maxDiff) {
                        System.out.printf("New max diff %d on %d deleting %d%n", diff, i, j);
                        maxDiff = diff * 1.0/j;
                        indexToDelete = i;
                        countToDelete = j;
                    }
                }
            }
            if (maxDiff > 0) {
                delete(items, indexToDelete, countToDelete);
                k-=countToDelete;
            } else {
                return calculate(items);
            }
        }
        return calculate(items);
    }

    private int weight(int count) {
        if (count == 0) {
            return 0;
        }
        if (count == 1) {
            return 1;
        }
        if (count < 10) {
            return 2;
        }
        if (count < 100) {
            return 3;
        }
        return 4;
    }

    private int calculate(List<Item> items) {
        System.out.println(items);
        return items.stream().mapToInt(i -> weight(i.count)).sum();
    }

    private void delete(List<Item> items, int indexToDelete, int countToDelete) {
        Item current = items.get(indexToDelete);
        if (countToDelete < current.count) {
            items.set(indexToDelete, new Item(current.symbol, current.count - countToDelete));
        }
        items.remove(indexToDelete);
        if (indexToDelete == 0 || indexToDelete == items.size()) {
            return;
        }
        if (items.get(indexToDelete - 1).symbol == items.get(indexToDelete).symbol) {
            var toAdd = items.get(indexToDelete).symbol;
            items.remove(indexToDelete);
            var prev = items.get(indexToDelete - 1);
            items.set(indexToDelete -1, new Item(prev.symbol, prev.count + toAdd));
        }
    }

    private int collapseCount(List<Item> items, int i) {
        if (i == 0 || i == items.size() - 1) {
            return 0;
        }
        Item left = items.get(i - 1);
        Item right = items.get(i + 1);
        if (left.symbol() == right.symbol()) {
            if (left.count() == 1 && right.count() == 1) {
                return 0;
            }
            if (left.count() == 1 || right.count() == 1) {
                return 1;
            }
            return 2;
        }
        return 0;
    }

    private static List<Item> getItems(String s) {
        List<Item> items = new ArrayList<>();
        char prev = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current != prev) {
                if (count > 0) {
                    items.add(new Item(prev, count));
                }
                count = 1;
                prev = current;
            } else {
                count++;
            }
        }
        items.add(new Item(prev, count));
        return items;
    }

    public static void main(String[] args) {
        System.out.println(new RunLength().getLengthOfOptimalCompression("aaaaaaaaaaa", 0));
    }
}
