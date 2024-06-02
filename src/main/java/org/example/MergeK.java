package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

public class MergeK {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = null;
        PriorityQueue<ListNode> heads = new PriorityQueue<>(Comparator.comparing(n -> n.val));
        Arrays.stream(lists).filter(Objects::nonNull).forEach(heads::add);

        if (heads.isEmpty()) {
            return result;
        }

        result = heads.poll();
        ListNode tail = result;
        if (result.next != null) {
            heads.add(result.next);
        }
        while (!heads.isEmpty()) {

            ListNode current = heads.poll();
            tail.next = current;
            tail = tail.next;

            if (current.next != null) {
                heads.add(current.next);
            }
        }
        return result;
    }
}
